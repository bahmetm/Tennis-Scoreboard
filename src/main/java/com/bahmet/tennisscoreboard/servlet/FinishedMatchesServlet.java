package com.bahmet.tennisscoreboard.servlet;

import com.bahmet.tennisscoreboard.model.Match;
import com.bahmet.tennisscoreboard.model.Page;
import com.bahmet.tennisscoreboard.service.FinishedMatchService;
import com.bahmet.tennisscoreboard.util.ErrorHandler;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/matches")
public class FinishedMatchesServlet extends HttpServlet {
    private FinishedMatchService finishedMatchService;
    private static final int PAGE_SIZE = 5;

    @Override
    public void init(ServletConfig config) throws ServletException {
        finishedMatchService = (FinishedMatchService) config.getServletContext().getAttribute("finishedMatchService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageParameter = req.getParameter("page");
        int pageNumber = 1;

        if (pageParameter != null && pageParameter.matches("\\d+")) {
            try {
                pageNumber = Integer.parseInt(pageParameter);
            } catch (NumberFormatException e) {
                ErrorHandler.sendErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "Invalid page number.", req);
            }
        }

        String playerName = req.getParameter("playerName");
        req.setAttribute("playerName", playerName);

        if (playerName != null && !playerName.matches("^[\\w\\s]*$")) {
            req.setAttribute("error", "Player name not provided.");
            req.getRequestDispatcher("/finished-matches.jsp").forward(req, resp);
            return;
        }

        Page<Match> page = finishedMatchService.getFinishedMatches(pageNumber, PAGE_SIZE, playerName);

        req.setAttribute("page", page);

        req.getRequestDispatcher("/finished-matches.jsp").forward(req, resp);
    }
}
