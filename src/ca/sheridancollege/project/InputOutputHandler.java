package ca.sheridancollege.project;

import java.util.List;
import java.util.Scanner;

public class InputOutputHandler extends InputOutput {
    private Scanner scanner;

    public InputOutputHandler() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void printPlayerTurn(String playerName, List<Card> hand) {
        System.out.println(playerName + "'s turn. Hand: " + handToString(hand));
    }

    @Override
    public String getInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        while (input.isEmpty()) {
            System.out.print("Invalid input. Please enter a value: ");
            input = scanner.nextLine().trim();
        }
        return input;
    }

    @Override
    public boolean isGetScoreRequest(String input) {
        return input.equals("*");
    }

    @Override
    public boolean isQuitRequest(String input) {
        return input.equalsIgnoreCase("q");
    }

    @Override
    public void printPlayerReceivedCards(String playerName, int numCards, String otherPlayerName) {
        System.out.println(playerName + " received " + numCards + " card(s) from " + otherPlayerName);
    }

    @Override
    public void printGoFish() {
        System.out.println("Go Fish! Drawing a card from the deck...");
    }

    @Override
    public void printPlayerDrewCard(String playerName, Card card) {
        System.out.println(playerName + " drew a card: " + card);
    }

    @Override
    public void printNoCardsInDeck() {
        System.out.println("No cards left in the deck!");
    }

    @Override
    public void printPlayerGotBook(String playerName, int rank) {
        System.out.println(playerName + " formed a book of rank " + rank);
    }

    @Override
    public void printFinalScore() {
        System.out.println("Game over! Final scores:");
    }

    @Override
    public void printPlayerScore(String playerName, int score) {
        System.out.println(playerName + ": " + score + " points");
    }

    @Override
    public void printCurrentScores() {
        System.out.println("Current scores:");
    }

    @Override
    public void printInvalidInput() {
        System.out.println("Invalid input. Please try again.");
    }

    @Override
    public void printGameOver() {
        System.out.println("Game over.");
    }

    private String handToString(List<Card> hand) {
        StringBuilder sb = new StringBuilder();
        for (Card card : hand) {
            sb.append(card).append(", ");
        }
        return sb.toString();
    }
}