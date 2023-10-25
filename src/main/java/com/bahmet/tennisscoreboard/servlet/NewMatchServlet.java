package com.bahmet.tennisscoreboard.servlet;

import com.bahmet.tennisscoreboard.model.Match;
import com.bahmet.tennisscoreboard.service.NewMatchService;
import com.bahmet.tennisscoreboard.service.OnGoingMatchesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "NewMatchServlet", urlPatterns = "/new-match")
public class NewMatchServlet extends HttpServlet {
    NewMatchService newMatchService = new NewMatchService();
    OnGoingMatchesService onGoingMatchesService = new OnGoingMatchesService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> errors = new HashMap<>();

        String playerOneName = req.getParameter("playerOneName");
        String playerTwoName = req.getParameter("playerTwoName");

        if (playerOneName == null || !isPlayerNameValid(playerOneName)) {
            errors.put("playerOneNameNotValid", "Name should contain only letters and digits!");
        }

        if (playerTwoName == null || !isPlayerNameValid(playerTwoName)) {
            errors.put("playerTwoNameNotValid", "Name should contain only letters and digits!");
        }

        if (playerOneName != null && playerOneName.equals(playerTwoName)) {
            errors.put("playerNamesAreSame", "Players names can't be same");
        }

        if (errors.isEmpty()) {
            Match match = newMatchService.createNewMatch(playerOneName, playerTwoName);
            String uuid = onGoingMatchesService.add(match);
            resp.sendRedirect("match-score?uuid=" + uuid);
        } else {
            req.setAttribute("playerOneName", playerOneName);
            req.setAttribute("playerTwoName", playerTwoName);
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/new-match.jsp").forward(req, resp);
        }

    }

    private boolean isPlayerNameValid(String playerName) {
        return !playerName.isEmpty() && playerName.matches("^[\\w\\s]*$");
    }
}
