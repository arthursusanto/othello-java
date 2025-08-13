# Othello (reversi) - Java

## A fully playable Othello (Reversi) game written in Java, supporting human players and multiple AI strategies (Random, Greedy).

This project features the following:

- Complete game logic (legal moves, flips, scoring, game-over)
- Multiple play modes via controllers (Human vs Human, Human vs AI, AI vs AI)
- Differing AI player strategies (`Random`, `Greedy`)
- Large simulations for AI vs AI games to determine optimal strategies
- Text/CLI board rendering
- A comprehensive test suite using JUnit 5

## Getting Started

### Prerequisites

- Java 17+ (JDK)
- (Optional) Apache Maven, if you want to build/run using Maven, and run the test suite

### Running the project

1. Clone the repo

```
git clone
```

2a. Run with Maven

```
mvn compile
mvn exec:java
```

2b. Run without Maven

From the project root:

```
javac -d bin src/main/java/othello/*.java
java -cp bin othello.Main
```

3. Run the tests (only with Maven)

```
mvn test
```
