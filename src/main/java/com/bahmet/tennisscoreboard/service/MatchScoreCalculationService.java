package com.bahmet.tennisscoreboard.service;

import com.bahmet.tennisscoreboard.model.EPlayer;
import com.bahmet.tennisscoreboard.model.MatchScore;

public class MatchScoreCalculationService {
    public boolean playerWinsPoint(EPlayer player, MatchScore matchScore) {
        if (!matchScore.isTieBreak()) calculatePlayerPointsInRegularMode(player, matchScore);
        else calculatePlayerPointsInTieBreakMode(player, matchScore);

        return isMatchFinished(matchScore);
    }

    public void calculatePlayerPointsInRegularMode(EPlayer player, MatchScore matchScore) {
        EPlayer otherPlayer = player.equals(EPlayer.PLAYER_ONE) ? EPlayer.PLAYER_TWO : EPlayer.PLAYER_ONE;

        if (playerPointsLessThen30(player, matchScore)) add15PointsToPlayer(player, matchScore);
        else if (playerPointsLessThen40(player, matchScore)) add10PointsToPlayer(player, matchScore);
        else if (playerPointsEqualsTo40(player, matchScore)) {
            if (playerPointsLessThen40(otherPlayer, matchScore)) {
                playerWinsGame(player, matchScore);
                matchScore.clearPoints();
            } else if (playerPointsEqualsTo40(otherPlayer, matchScore)) {
                matchScore.increasePlayerAds(player);
                if (playerWinsPointWithAdvantage(player, matchScore)) {
                    playerWinsGame(player, matchScore);
                    matchScore.clearPoints();
                    matchScore.clearAds();
                } else if (playerLostPointWithAdvantage(player, matchScore)) matchScore.clearAds();
            }
        }
    }

    public void calculatePlayerPointsInTieBreakMode(EPlayer player, MatchScore matchScore) {
        EPlayer otherPlayer = player.equals(EPlayer.PLAYER_ONE) ? EPlayer.PLAYER_TWO : EPlayer.PLAYER_ONE;

        if (playerTieBreakPointsLessThen6(player, matchScore)) matchScore.increaseTieBreakPoints(player);
        else if (playerTieBreakPointsEqualsTo6(player, matchScore)) {
            if (playerTieBreakPointsLessThen6(otherPlayer, matchScore)) {
                playerWinsSet(player, matchScore);
                matchScore.clearTieBreakPoints();
                setRegularMode(matchScore);
            } else matchScore.increaseTieBreakPoints(player);
        } else {
            matchScore.increaseTieBreakPoints(player);
            if (playerWinsTieBreakPointWithAdvantage(player, matchScore)) {
                playerWinsSet(player, matchScore);
                matchScore.clearTieBreakPoints();
                setRegularMode(matchScore);
            }
        }
    }

    public void playerWinsGame(EPlayer player, MatchScore matchScore) {
        EPlayer otherPlayer = player.equals(EPlayer.PLAYER_ONE) ? EPlayer.PLAYER_TWO : EPlayer.PLAYER_ONE;

        if (playerGamesLessThen5(player, matchScore)) matchScore.increasePlayerGames(player);
        else if (playerGamesEqualsTo5(player, matchScore)) {
            if (playerGamesLessThen5(otherPlayer, matchScore)) {
                playerWinsSet(player, matchScore);
                matchScore.clearGames();
            } else if (playerGamesEqualsTo6(otherPlayer, matchScore)) {
                matchScore.increasePlayerGames(player);
                setTieBreakMode(matchScore);
            } else matchScore.increasePlayerGames(player);
        } else if (playerGamesEqualsTo6(player, matchScore) && playerGamesEqualsTo5(otherPlayer, matchScore)) {
            playerWinsSet(player, matchScore);
            matchScore.clearGames();
        }
    }

    public boolean playerPointsLessThen30(EPlayer player, MatchScore matchScore) {
        return matchScore.getPlayerPoints(player) < 30;
    }

    public boolean playerPointsLessThen40(EPlayer player, MatchScore matchScore) {
        return matchScore.getPlayerPoints(player) < 40;
    }

    public boolean playerPointsEqualsTo40(EPlayer player, MatchScore matchScore) {
        return matchScore.getPlayerPoints(player) == 40;
    }

    public boolean playerTieBreakPointsLessThen6(EPlayer player, MatchScore matchScore) {
        return matchScore.getPlayerTieBreakPoints(player) < 6;
    }

    public boolean playerTieBreakPointsEqualsTo6(EPlayer player, MatchScore matchScore) {
        return matchScore.getPlayerTieBreakPoints(player) == 6;
    }

    public boolean playerWinsTieBreakPointWithAdvantage(EPlayer player, MatchScore matchScore) {
        EPlayer otherPlayer = player.equals(EPlayer.PLAYER_ONE) ? EPlayer.PLAYER_TWO : EPlayer.PLAYER_ONE;
        return matchScore.getPlayerTieBreakPoints(player) - matchScore.getPlayerTieBreakPoints(otherPlayer) == 2;
    }

    public void add15PointsToPlayer(EPlayer player, MatchScore matchScore) {
        matchScore.increasePlayerPoints(player, 15);
    }

    public void add10PointsToPlayer(EPlayer player, MatchScore matchScore) {
        matchScore.increasePlayerPoints(player, 10);
    }

    public boolean playerWinsPointWithAdvantage(EPlayer player, MatchScore matchScore) {
        EPlayer otherPlayer = player.equals(EPlayer.PLAYER_ONE) ? EPlayer.PLAYER_TWO : EPlayer.PLAYER_ONE;
        return matchScore.getPlayerAds(player) - matchScore.getPlayerAds(otherPlayer) == 2;
    }

    public boolean playerLostPointWithAdvantage(EPlayer player, MatchScore matchScore) {
        EPlayer otherPlayer = player.equals(EPlayer.PLAYER_ONE) ? EPlayer.PLAYER_TWO : EPlayer.PLAYER_ONE;
        return matchScore.getPlayerAds(player) == 1 && matchScore.getPlayerAds(otherPlayer) == 1;
    }

    public boolean playerGamesLessThen5(EPlayer player, MatchScore matchScore) {
        return matchScore.getPlayerGames(player) < 5;
    }

    public boolean playerGamesEqualsTo5(EPlayer player, MatchScore matchScore) {
        return matchScore.getPlayerGames(player) == 5;
    }

    public boolean playerGamesEqualsTo6(EPlayer player, MatchScore matchScore) {
        return matchScore.getPlayerGames(player) == 6;
    }

    public void playerWinsSet(EPlayer player, MatchScore matchScore) {
        matchScore.increasePlayerSets(player);
        matchScore.clearGames();
        if (playerWinsMatch(player, matchScore)) {
            setWinner(player, matchScore);
            setMatchFinished(matchScore);
        }
    }

    public boolean playerWinsMatch(EPlayer player, MatchScore matchScore) {
        return matchScore.getPlayerSets(player) == 2;
    }

    public boolean isTieBreak(MatchScore matchScore) {
        return matchScore.isTieBreak();
    }

    public void setTieBreakMode(MatchScore matchScore) {
        matchScore.setTieBreak(true);
    }

    public void setRegularMode(MatchScore matchScore) {
        matchScore.setTieBreak(false);
    }

    private void setWinner(EPlayer player, MatchScore matchScore) {
        matchScore.setWinner(player);
    }

    public EPlayer getWinner(MatchScore matchScore) {
        return matchScore.getWinner();
    }

    public boolean isMatchFinished(MatchScore matchScore) {
        return matchScore.isMatchFinished();
    }

    public void setMatchFinished(MatchScore matchFinished) {
        matchFinished.setMatchFinished();
    }
}
