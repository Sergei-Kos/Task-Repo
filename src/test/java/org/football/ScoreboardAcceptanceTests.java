package org.football;

import org.football.model.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ScoreboardAcceptanceTests {
    private Scoreboard scoreBoard;

    @BeforeEach
    public void setUp() {
        scoreBoard = new Scoreboard();
    }

    @Test
    public void testScoreBoardSummaryOrder() {
        long gameId;

        // Setup scenario with multiple games according to the given example
        gameId = scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.updateScore(gameId, 0, 5);

        gameId = scoreBoard.startGame("Spain", "Brazil");
        scoreBoard.updateScore(gameId, 10, 2);

        gameId = scoreBoard.startGame("Germany", "France");
        scoreBoard.updateScore(gameId, 2, 2);

        gameId = scoreBoard.startGame("Uruguay", "Italy");
        scoreBoard.updateScore(gameId, 6, 6);

        gameId = scoreBoard.startGame("Argentina", "Australia");
        scoreBoard.updateScore(gameId, 3, 1);

        List<Game> summary = scoreBoard.getSummary();

        Assertions.assertEquals("Uruguay 6 - Italy 6", summary.get(0).toString(), "Uruguay vs Italy should have the highest total score and be first");
        Assertions.assertEquals("Spain 10 - Brazil 2", summary.get(1).toString(), "Spain vs Brazil should be second based on total score");
        Assertions.assertEquals("Mexico 0 - Canada 5", summary.get(2).toString(), "Mexico vs Canada should be third");
        Assertions.assertEquals("Argentina 3 - Australia 1", summary.get(3).toString(), "Argentina vs Australia should be fourth");
        Assertions.assertEquals("Germany 2 - France 2", summary.get(4).toString(), "Germany vs France should have the lowest total score and be last");
    }
}
