package com.bahmet.tennisscoreboard.servlet;

import com.bahmet.tennisscoreboard.dao.MatchDAO;
import com.bahmet.tennisscoreboard.model.EPlayer;
import com.bahmet.tennisscoreboard.model.Match;
import com.bahmet.tennisscoreboard.service.FinishedMatchService;
import com.bahmet.tennisscoreboard.service.MatchScoreCalculationService;
import com.bahmet.tennisscoreboard.service.OnGoingMatchesService;
import com.bahmet.tennisscoreboard.util.ErrorHandler;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {
    private OnGoingMatchesService onGoingMatchesService;
    private MatchScoreCalculationService matchScoreCalculationService;
    private FinishedMatchService finishedMatchService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        onGoingMatchesService = (OnGoingMatchesService) config.getServletContext()
                .getAttribute("onGoingMatchesService");
        matchScoreCalculationService = (MatchScoreCalculationService) config.getServletContext()
                .getAttribute("matchScoreCalculationService");
        finishedMatchService = (FinishedMatchService) config.getServletContext().getAttribute("finishedMatchService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");

        if (uuid == null || uuid.isEmpty()) {
            ErrorHandler.sendErrorResponse(HttpServletResponse.SC_BAD_REQUEST,
                    "UUID not provided.", req);
        }

        req.setAttribute("uuid", uuid);

        Match match = onGoingMatchesService.getMatchByUUID(uuid);

        if (match == null) {
            ErrorHandler.sendErrorResponse(HttpServletResponse.SC_NOT_FOUND,
                    "Match not found.", req);
        }

        req.setAttribute("match", match);
        req.setAttribute("isTieBreak", matchScoreCalculationService.isTieBreak(match.getMatchScore()));

        req.getRequestDispatcher("/on-going-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");

        if (uuid == null || uuid.isEmpty()) {
            ErrorHandler.sendErrorResponse(HttpServletResponse.SC_BAD_REQUEST,
                    "UUID not provided.", req);
        }

        Match match = onGoingMatchesService.getMatchByUUID(uuid);

        if (match == null) {
            ErrorHandler.sendErrorResponse(HttpServletResponse.SC_NOT_FOUND,
                    "Match not found.", req);
        }

        String playerNumberParameter = req.getParameter("playerNumberParameter");

        if (playerNumberParameter.isEmpty()) {
            ErrorHandler.sendErrorResponse(HttpServletResponse.SC_BAD_REQUEST,
                    "Player number not provided.", req);
        }

        EPlayer playerNumber = Integer.parseInt(playerNumberParameter) == 0 ? EPlayer.PLAYER_ONE : EPlayer.PLAYER_TWO;

        boolean matchFinished = false;

        try {

            matchFinished = matchScoreCalculationService.playerWinsPoint(playerNumber, match.getMatchScore());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (matchFinished) {
            match.setWinner(matchScoreCalculationService.getWinner(match.getMatchScore()).equals(EPlayer.PLAYER_ONE) ? match.getPlayerOne() : match.getPlayerTwo());

            finishedMatchService.save(match);

            onGoingMatchesService.remove(uuid);

            req.setAttribute("uuid", uuid);
            req.setAttribute("match", match);

            System.out.println(new MatchDAO().findAll().toString());

            req.getRequestDispatcher("/final-score.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect("/match-score?uuid=" + uuid);
    }
}
