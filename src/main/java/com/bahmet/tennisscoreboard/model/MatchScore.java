package com.bahmet.tennisscoreboard.model;

public class MatchScore {
    private int[] points;
    private int[] games;
    private int[] sets;
    private int[] ads;
    private int[] tieBreakPoints;
    private boolean tieBreak;
    private boolean matchFinished;
    private EPlayer winner;

    public MatchScore() {
        this.points = new int[]{0, 0};
        this.games = new int[]{0, 0};
        this.sets = new int[]{0, 0};
        this.ads = new int[]{0, 0};
        this.tieBreakPoints = new int[]{0, 0};
        this.tieBreak = false;
        this.matchFinished = false;
        this.winner = null;
    }

    public int getPlayerPoints(EPlayer player) {
        return points[player.ordinal()];
    }

    public void increasePlayerPoints(EPlayer player, int points) {
        this.points[player.ordinal()] += points;
    }

    public void clearPoints() {
        this.points[0] = 0;
        this.points[1] = 0;
    }

    public int[] getPoints() {
        return points;
    }

    public void setPoints(int[] points) {
        this.points = points;
    }

    public int getPlayerGames(EPlayer player) {
        return games[player.ordinal()];
    }

    public void increasePlayerGames(EPlayer player) {
        games[player.ordinal()]++;
    }

    public void clearGames() {
        games[0] = 0;
        games[1] = 0;
        clearPoints();
    }

    public int[] getGames() {
        return games;
    }

    public void setGames(int[] games) {
        this.games = games;
    }

    public int getPlayerSets(EPlayer player) {
        return sets[player.ordinal()];
    }

    public void increasePlayerSets(EPlayer player) {
        sets[player.ordinal()]++;
    }

    public int[] getSets() {
        return sets;
    }

    public void setSets(int[] sets) {
        this.sets = sets;
    }

    public int getPlayerAds(EPlayer player) {
        return ads[player.ordinal()];
    }

    public void increasePlayerAds(EPlayer player) {
        this.ads[player.ordinal()]++;
    }

    public void clearAds() {
        this.ads[0] = 0;
        this.ads[1] = 0;
    }

    public int[] getAds() {
        return ads;
    }

    public void setAds(int[] ads) {
        this.ads = ads;
    }

    public int getPlayerTieBreakPoints(EPlayer player) {
        return tieBreakPoints[player.ordinal()];
    }

    public void increaseTieBreakPoints(EPlayer player) {
        tieBreakPoints[player.ordinal()]++;
    }

    public void clearTieBreakPoints() {
        tieBreakPoints[0] = 0;
        tieBreakPoints[1] = 0;
    }

    public int[] getTieBreakPoints() {
        return tieBreakPoints;
    }

    public void setTieBreakPoints(int[] tieBreakPoints) {
        this.tieBreakPoints = tieBreakPoints;
    }

    public boolean isTieBreak() {
        return this.tieBreak;
    }

    public void setTieBreak(boolean tieBreak) {
        this.tieBreak = tieBreak;
    }

    public boolean isMatchFinished() {
        return this.matchFinished;
    }

    public void setMatchFinished() {
        this.matchFinished = true;
    }

    public EPlayer getWinner() {
        return this.winner;
    }

    public void setWinner(EPlayer player) {
        this.winner = player;
    }


}
