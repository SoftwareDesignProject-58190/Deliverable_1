package ca.sheridancollege.project;

import java.util.List;

public abstract class InputOutput {
    // Prints the current player's turn and their hand
    public abstract void printPlayerTurn(String playerName, List<Card> hand);

    // Gets user input
    public abstract String getInput(String prompt);

    // Checks if the user is requesting the current scores
    public abstract boolean isGetScoreRequest(String input);

    // Checks if the user is requesting to quit the game
    public abstract boolean isQuitRequest(String input);

    // Prints a message when the player receives cards from another player
    public abstract void printPlayerReceivedCards(String playerName, int numCards, String otherPlayerName);

    // Prints a message when the player has to "Go Fish"
    public abstract void printGoFish();

    // Prints a message when the player draws a card from the deck
    public abstract void printPlayerDrewCard(String playerName, Card card);

    // Prints a message when there are no more cards in the deck
    public abstract void printNoCardsInDeck();

    // Prints a message when the player forms a book
    public abstract void printPlayerGotBook(String playerName, int rank);

    // Prints a message to indicate the start of the final score display
    public abstract void printFinalScore();

    // Prints a player's score
    public abstract void printPlayerScore(String playerName, int score);

    // Prints a message to indicate the start of the current scores display
    public abstract void printCurrentScores();

    // Prints a message when the user provides an invalid input
    public abstract void printInvalidInput();

    // Prints a message when the game is over
    public abstract void printGameOver();
}