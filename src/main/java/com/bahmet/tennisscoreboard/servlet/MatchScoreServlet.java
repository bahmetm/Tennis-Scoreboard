package com.bahmet.tennisscoreboard.servlet;

import com.bahmet.tennisscoreboard.service.OnGoingMatchesService;

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

    @Override
    public void init(ServletConfig config) throws ServletException {
        onGoingMatchesService = (OnGoingMatchesService) config.getServletContext()
                .getAttribute("onGoingMatchesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");

        if (uuid.isEmpty() )
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
