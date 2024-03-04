package org.football;

import org.football.model.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ScoreboardUnitTests {
    private Scoreboard scoreBoard;

    @BeforeEach
    public void setUp() {
        scoreBoard = new Scoreboard();
    }

    @Test
    public void testStartGame() {
        scoreBoard.startGame("Home", "Away");
        Assertions.assertEquals(1, scoreBoard.getSummary().size(), "Game should be added to the score board");
    }

    @Test
    public void testUpdateScore() {
        scoreBoard.startGame("Home", "Away");
        Game game = scoreBoard.getSummary().getFirst();
        long gameId = game.getId();
        scoreBoard.updateScore(gameId, 1, 2);
        Assertions.assertEquals("Home 1 - Away 2", game.toString(), "Score should be updated");
    }

    @Test
    public void testFinishGame() {
        scoreBoard.startGame("Home", "Away");
        Game game = scoreBoard.getSummary().getFirst();
        long gameId = game.getId(); // Fetch the game ID
        scoreBoard.finishGame(gameId);
        Assertions.assertTrue(scoreBoard.getSummary().isEmpty(), "Game should be removed from the score board");
    }

    @Test
    public void testGetSummaryOrdering() {
        long gameId;
        // Given several games with varying scores
        gameId = scoreBoard.startGame("Team A", "Team B");
        scoreBoard.updateScore(gameId, 1, 1);

        gameId = scoreBoard.startGame("Team C", "Team D");
        scoreBoard.updateScore(gameId, 0, 3);

        gameId = scoreBoard.startGame("Team E", "Team F");
        scoreBoard.updateScore(gameId, 2, 2);

        gameId = scoreBoard.startGame("Team H", "Team I");
        scoreBoard.updateScore(gameId, 1, 1);

        // Then the summary should order games by total score and then by recency (most recent first) for equal scores
        List<Game> summary = scoreBoard.getSummary();

        Assertions.assertEquals("Team E 2 - Team F 2", summary.get(0).toString());
        Assertions.assertEquals("Team C 0 - Team D 3", summary.get(1).toString());
        Assertions.assertEquals("Team H 1 - Team I 1", summary.get(2).toString());
        Assertions.assertEquals("Team A 1 - Team B 1", summary.get(3).toString());
    }

    @Test
    public void testFinishNonExistentGame() {
        long gameId = scoreBoard.startGame("Home", "Away");
        scoreBoard.finishGame(gameId); // Finish the game
        scoreBoard.finishGame(gameId); // Attempt to finish again

        Assertions.assertEquals(0, scoreBoard.getSummary().size(),
                "Scoreboard should remain empty after trying to remove a non-existent game.");
    }

    @Test
    public void testUpdateScoreWithNegativeValues() {
        long gameId = scoreBoard.startGame("Home", "Away");
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                scoreBoard.updateScore(gameId, -1, -2));

        String expectedMessage = "Score can not be negative";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}
