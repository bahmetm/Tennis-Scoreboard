package com.bahmet.tennisscoreboard.service;

import com.bahmet.tennisscoreboard.dao.MatchDAO;
import com.bahmet.tennisscoreboard.model.Match;
import com.bahmet.tennisscoreboard.model.Page;

public class FinishedMatchService {
    private final MatchDAO matchDAO = new MatchDAO();

    public void save(Match match) {
        System.out.println(matchDAO.save(match));
    }

    public Page<Match> getFinishedMatches(int pageNumber, int pageSize, String playerName) {
        if (playerName == null) {
            return matchDAO.findAllPaginated(pageNumber, pageSize);
        } else {
            return matchDAO.findByPlayerNamePaginated(pageNumber, pageSize, playerName);
        }
    }

}
