package org.football.model;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Represents a single game between two teams, tracking their scores.
 * <p>
 * This class provides methods to update the scores of the home and away teams and retrieve the total score.
 */
public class Game {
    private static final AtomicLong nextId = new AtomicLong(0);
    private final long id;
    private final String homeTeam;
    private final String awayTeam;
    private int homeScore;
    private int awayScore;

    /**
     * Constructs a new Game instance.
     *
     * @param homeTeam the name of the home team
     * @param awayTeam the name of the away team
     */
    public Game(String homeTeam, String awayTeam) {
        this.id = nextId.getAndIncrement();
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
    }

    /**
     * Updates the score for both teams.
     *
     * @param homeScore the new score of the home team
     * @param awayScore the new score of the away team
     * @throws IllegalArgumentException if either score is negative
     */
    public void updateScore(int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Score can not be negative");
        }
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    /**
     * Returns the total score of the game.
     *
     * @return the sum of the home team's score and the away team's score
     */
    public int getTotalScore() {
        return this.homeScore + this.awayScore;
    }

    /**
     * Returns id of the game.
     *
     * @return id of the game
     */
    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return homeTeam + " " + homeScore + " - " + awayTeam + " " + awayScore;
    }
}