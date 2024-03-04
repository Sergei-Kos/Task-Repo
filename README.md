# Scoreboard Library

A Java library designed to manage and track scores for football games, providing functionalities such as starting a game, updating scores, finishing a game, and retrieving a summary of games by total score and recency.

## Assumptions

- Each game involves two teams: a home team and an away team.
- Scores are non-negative integers and reflect the current state of the game.
- The library is intended for single-instance use in a synchronous environment.

## Description

This library offers a simple yet flexible way to manage football game scores. It allows users to:

- Start a new game, capturing initial team names with scores starting at 0-0.
- Update the score of a game at any point.
- Finish a game, removing it from the active scoreboard.
- Get a summary of games, sorted by total score and, for equal scores, by the most recently added game.

## Technologies Used

- Java 11
- Maven for dependency management and building the project
- JUnit 5 for unit testing

## Suggestions for Further Optimizations

- Concurrency Support: For applications requiring concurrent access, consider enhancing thread safety, possibly by using thread-safe collections or synchronization mechanisms. 
- Persistence Layer: Integrate a database to persist game states, allowing for recovery of game states between application restarts. 
- Scalability: For handling a large number of games, consider optimizing data structures and algorithms for performance, especially for the summary generation feature. 
- REST API Integration: Extend the library with a RESTful service layer to allow remote management and querying of game scores, making it suitable for web applications. 
- Customizable Sorting: Provide options for customized sorting criteria in the game summary to accommodate different use cases or preferences.
- Add integration tests

## Instructions to Run

Ensure you have Java and Maven installed on your machine. Then, clone this repository and navigate to the project directory.

1. **Build the project:**

   ```shell
   mvn clean install
