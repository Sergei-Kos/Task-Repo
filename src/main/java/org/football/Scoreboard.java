package org.football;

import org.football.model.Game;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Represents a single scoreboard which contains information about games.
 * <p>
 * This class provides methods to start game, finish game, update score and retrieve summary of games.
 */
public class Scoreboard {
    private final List<Game> games; // ordered retrieval
    private final HashMap<Long, Game> gameMap; // Lookup instead of DB

    /**
     * Constructs a new Scoreboard instance.
     */
    public Scoreboard() {
        games = new ArrayList<>();
        gameMap = new HashMap<>();
    }

    /**
     * Adds a new game to the scoreboard.
     *
     * @param homeTeam the name of the home team
     * @param awayTeam the name of the away team
     * @return the ID of the newly added game
     */
    public long startGame(String homeTeam, String awayTeam) {
        Game game = new Game(homeTeam, awayTeam);
        long gameId = game.getId();
        games.add(game);
        gameMap.put(gameId, game); // Add game to map for quick lookup
        return gameId;
    }

    /**
     * Removes a game from the scoreboard based on its ID.
     *
     * @param gameId the ID of the game to be removed
     */
    public void finishGame(long gameId) {
        Game game = gameMap.remove(gameId); // Remove from map and capture removed game
        if (game != null) {
            games.remove(game); // Remove game from list
        }
    }

    /**
     * Updates the score of a specific game identified by its ID.
     *
     * @param gameId    the ID of the game to update
     * @param homeScore the new score of the home team
     * @param awayScore the new score of the away team
     */
    public void updateScore(long gameId, int homeScore, int awayScore) {
        Game game = gameMap.get(gameId); // Lookup game by ID
        if (game != null) {
            game.updateScore(homeScore, awayScore);
        }
    }

    /**
     * Retrieves a summary of all games, sorted by their total score and recency.
     *
     * @return a list of games, sorted by total score and recency
     */
    public List<Game> getSummary() {
        return games.stream()
                .sorted(Comparator.comparing(Game::getTotalScore).reversed()
                        .thenComparing(Comparator.comparing(Game::getId).reversed()))
                .collect(Collectors.toList());
    }
}
