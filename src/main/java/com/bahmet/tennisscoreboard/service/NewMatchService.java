package com.bahmet.tennisscoreboard.service;

import com.bahmet.tennisscoreboard.dao.PlayerDAO;
import com.bahmet.tennisscoreboard.model.Match;
import com.bahmet.tennisscoreboard.model.Player;

public class NewMatchService {
    private final PlayerDAO playerDAO = new PlayerDAO();

    public Match createNewMatch(String playerOneName, String playerTwoName) {
        Player playerOne = playerDAO.findByName(playerOneName);
        Player playerTwo = playerDAO.findByName(playerTwoName);

        if (playerOne == null) {
            playerOne = new Player(playerOneName);
        }

        if (playerTwo == null) {
            playerTwo = new Player(playerTwoName);
        }

        return new Match(playerOne, playerTwo);
    }
}
