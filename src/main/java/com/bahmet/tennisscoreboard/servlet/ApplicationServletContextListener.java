package com.bahmet.tennisscoreboard.servlet;

import com.bahmet.tennisscoreboard.dao.MatchDAO;
import com.bahmet.tennisscoreboard.service.FinishedMatchService;
import com.bahmet.tennisscoreboard.service.MatchScoreCalculationService;
import com.bahmet.tennisscoreboard.service.NewMatchService;
import com.bahmet.tennisscoreboard.service.OnGoingMatchesService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        MatchDAO matchDAO = new MatchDAO();

        try {
            Class.forName("com.bahmet.tennisscoreboard.util.HibernateUtil");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        context.setAttribute("matchDao", matchDAO);
        context.setAttribute("newMatchService", new NewMatchService());
        context.setAttribute("onGoingMatchesService", new OnGoingMatchesService());
        context.setAttribute("matchScoreCalculationService", new MatchScoreCalculationService());
        context.setAttribute("finishedMatchService", new FinishedMatchService());
    }
}
