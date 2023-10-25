package com.bahmet.tennisscoreboard.servlet;

import com.bahmet.tennisscoreboard.dao.MatchDAO;
import com.bahmet.tennisscoreboard.dao.PlayerDAO;
import com.bahmet.tennisscoreboard.service.NewMatchService;
import com.bahmet.tennisscoreboard.service.OnGoingMatchesService;
import com.bahmet.tennisscoreboard.util.HibernateUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        PlayerDAO playerDAO = new PlayerDAO();
        MatchDAO matchDAO = new MatchDAO();

        context.setAttribute("playerDAO", playerDAO);
        context.setAttribute("matchDao", matchDAO);
        context.setAttribute("newMatchService", new NewMatchService());
        context.setAttribute("onGoingMatchesService", new OnGoingMatchesService());
    }
}
