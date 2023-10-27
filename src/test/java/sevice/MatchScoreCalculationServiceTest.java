package sevice;

import com.bahmet.tennisscoreboard.model.EPlayer;
import com.bahmet.tennisscoreboard.model.MatchScore;
import com.bahmet.tennisscoreboard.service.MatchScoreCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatchScoreCalculationServiceTest {
    private static final int ADVANTAGE = 1;
    private static final int DEUCE = 40;

    private MatchScoreCalculationService matchScoreCalculationService;
    private MatchScore matchScore;

    private final EPlayer playerOne = EPlayer.PLAYER_ONE;
    private final EPlayer playerTwo = EPlayer.PLAYER_TWO;

    @BeforeEach
    public void setUp() {
        matchScore = new MatchScore();
        matchScoreCalculationService = new MatchScoreCalculationService();
    }

    private void initializeScores(int[] points, int[] games, int[] sets, int[] ads, int[] tieBreakPoints) {
        if (points != null) matchScore.setPoints(points);
        if (games != null) matchScore.setGames(games);
        if (sets != null) matchScore.setSets(sets);
        if (ads != null) matchScore.setAds(ads);
        if (tieBreakPoints != null) matchScore.setTieBreakPoints(tieBreakPoints);
    }

    @Test
    @DisplayName("Player gaining advantage from deuce")
    public void advantagePoint() {
        initializeScores(new int[]{DEUCE, DEUCE}, null, null, null, null);
        matchScoreCalculationService.playerWinsPoint(playerOne, matchScore);
        assertEquals(ADVANTAGE, matchScore.getPlayerAds(playerOne));
        assertEquals(0, matchScore.getPlayerAds(playerTwo));
    }

    @Test
    @DisplayName("When player 1 wins point with advantage then player 1 wins game")
    public void winPointWithAdvantage() {
        matchScore.setPoints(new int[]{40, 40});
        matchScore.setAds(new int[]{1, 0});

        matchScoreCalculationService.playerWinsPoint(playerOne, matchScore);

        assertEquals(matchScore.getPlayerGames(playerOne), 1);
        assertEquals(matchScore.getPlayerGames(playerTwo), 0);
    }

    @Test
    @DisplayName("When player 1 loose point with advantage then points are 40-40")
    public void loosePointWithAdvantage() {
        matchScore.setPoints(new int[]{40, 40});
        matchScore.setAds(new int[]{1, 0});

        matchScoreCalculationService.playerWinsPoint(playerTwo, matchScore);

        assertEquals(matchScore.getPlayerPoints(playerOne), 40);
        assertEquals(matchScore.getPlayerPoints(playerTwo), 40);
        assertEquals(matchScore.getPlayerAds(playerOne), 0);
        assertEquals(matchScore.getPlayerAds(playerTwo), 0);
    }

    @Test
    @DisplayName("When player 1 wins point and points are 40-0 then player 1 wins game")
    public void playerWinsGame() {
        matchScore.setPoints(new int[]{40, 0});

        matchScoreCalculationService.playerWinsPoint(playerOne, matchScore);

        assertEquals(matchScore.getPlayerGames(playerOne), 1);
        assertEquals(matchScore.getPlayerGames(playerTwo), 0);
    }

    @Test
    @DisplayName("When player 1 wins game and games are 5-0 then player 1 wins set")
    public void playerWinsSet() {
        matchScore.setGames(new int[]{5, 0});

        matchScoreCalculationService.playerWinsGame(playerOne, matchScore);

        assertEquals(matchScore.getPlayerSets(playerOne), 1);
        assertEquals(matchScore.getPlayerSets(playerTwo), 0);
    }

    @Test
    @DisplayName("When player 1 wins game and games are 5-5 then match not finished")
    public void matchNotFinished() {
        matchScore.setGames(new int[]{5, 5});

        matchScoreCalculationService.playerWinsGame(playerOne, matchScore);

        assertFalse(matchScoreCalculationService.isMatchFinished(matchScore));
    }

    @Test
    @DisplayName("When player 1 wins game and games are 6-5 then player 1 wins set")
    public void playerWinsSetWhenGames6_5() {
        matchScore.setGames(new int[]{6, 5});

        matchScoreCalculationService.playerWinsGame(playerOne, matchScore);

        assertEquals(matchScore.getPlayerSets(playerOne), 1);
        assertEquals(matchScore.getPlayerSets(playerTwo), 0);
    }

    @Test
    @DisplayName("When player 1 wins game and games are 5-6 then tie-break")
    public void tieBreak() {
        matchScore.setGames(new int[]{5, 6});

        matchScoreCalculationService.playerWinsGame(playerOne, matchScore);

        assertTrue(matchScoreCalculationService.isTieBreak(matchScore));
    }

    @Test
    @DisplayName("When tie-break and player 1 wins point and points are 5-6 then points are 6-6")
    public void clearTieBreakPoints() {
        matchScoreCalculationService.setTieBreakMode(matchScore);
        matchScore.setTieBreakPoints(new int[]{5, 6});

        matchScoreCalculationService.playerWinsPoint(playerOne, matchScore);

        assertEquals(matchScore.getPlayerTieBreakPoints(playerOne), 6);
        assertEquals(matchScore.getPlayerTieBreakPoints(playerTwo), 6);
    }

    @Test
    @DisplayName("When tie-break and player 1 wins point and points are 6-5 then player 1 wins set")
    public void winTieBreak() {
        matchScoreCalculationService.setTieBreakMode(matchScore);
        matchScore.setTieBreakPoints(new int[]{6, 5});

        matchScoreCalculationService.playerWinsPoint(playerOne, matchScore);

        assertEquals(matchScore.getPlayerSets(playerOne), 1);
        assertEquals(matchScore.getPlayerSets(playerTwo), 0);
    }

    @Test
    @DisplayName("When tie-break and player 1 wins point and points are 7-6 then player 1 wins set")
    public void winTieBreakPointWithAdvantage() {
        matchScoreCalculationService.setTieBreakMode(matchScore);
        matchScore.setTieBreakPoints(new int[]{7, 6});

        matchScoreCalculationService.playerWinsPoint(playerOne, matchScore);

        assertEquals(matchScore.getPlayerSets(playerOne), 1);
        assertEquals(matchScore.getPlayerSets(playerTwo), 0);
    }

    @Test
    @DisplayName("When player 1 wins set and sets are 1-0 then player 1 wins match")
    public void winMatch() {
        matchScore.setSets(new int[]{1, 0});

        matchScoreCalculationService.playerWinsSet(playerOne, matchScore);

        assertTrue(matchScoreCalculationService.isMatchFinished(matchScore));
        assertEquals(matchScoreCalculationService.getWinner(matchScore), playerOne);
    }
}
