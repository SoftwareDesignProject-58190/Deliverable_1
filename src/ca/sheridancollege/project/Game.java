package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private List<Player> players; // List of players in the game
    private DeckOfCards deck; // Deck of cards
    private Map<Integer, Book> books; // Map of books (sets of 4 cards of the same rank)
    private InputOutput inputOutput; // Input/output handler

    public Game(int numPlayers, InputOutput inputOutput) {
        this.inputOutput = inputOutput;
        players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            // Prompt the user for player names and create new players
            String playerName = inputOutput.getInput("Enter the name for Player " + (i + 1) + ": ");
            players.add(new Player(playerName));
        }
        deck = new DeckOfCards();
        deck.shuffle();
        books = new HashMap<>();
    }

    // Starts the game and runs the main game loop
    public void play() {
        dealInitialCards();

        while (true) {
            for (Player player : players) {
                if (takeTurn(player)) {
                    return;
                }
                if (isGameOver()) {
                    printScore();
                    return;
                }
            }
        }
    }

    // Deals the initial 7 cards to each player
    private void dealInitialCards() {
        for (int i = 0; i < 7; i++) {
            for (Player player : players) {
                player.addCard(deck.drawCard());
            }
        }
    }

    // Handles a player's turn, returns true if the player quits the game
    private boolean takeTurn(Player player) {
        inputOutput.printPlayerTurn(player.getName(), player.getHand());

        boolean gotCards = false;
        while (!gotCards) {
            String input = inputOutput.getInput("Enter the rank you would like to request (1-13), * to get current scores, or Q to quit: ");
            if (inputOutput.isGetScoreRequest(input)) {
                printCurrentScores();
                continue;
            } else if (inputOutput.isQuitRequest(input)) {
                inputOutput.printGameOver();
                return true;
            }

            int rank;
            try {
                rank = Integer.parseInt(input);
                if (rank < 1 || rank > 13) {
                    inputOutput.printInvalidInput();
                    continue;
                }
            } catch (NumberFormatException e) {
                inputOutput.printInvalidInput();
                continue;
            }

            Player playerToAsk = getPlayerWithRank(player, rank);
            if (playerToAsk != null) {
                List<Card> cards = playerToAsk.removeCards(rank);
                player.getHand().addAll(cards);
                gotCards = true;
                inputOutput.printPlayerReceivedCards(player.getName(), cards.size(), playerToAsk.getName());
            } else {
                inputOutput.printGoFish();
                Card card = deck.drawCard();
                if (card == null) {
                    inputOutput.printNoCardsInDeck();
                    break;
                }
                player.addCard(card);
                if (player.hasCard(card.getRank())) {
                    gotCards = true;
                } else {
                    inputOutput.printPlayerDrewCard(player.getName(), card);
                }
            }

            checkForBooks(player);
        }
        return false;
    }

    // Finds a player that has the requested rank
    private Player getPlayerWithRank(Player currentPlayer, int rank) {
        for (Player player : players) {
            if (player != currentPlayer && player.hasCard(rank)) {
                return player;
            }
        }
        return null;
    }

    // Checks if the player has formed any books (sets of 4 cards of the same rank)
    private void checkForBooks(Player player) {
        Map<Integer, List<Card>> playerCards = new HashMap<>();
        for (Card card : player.getHand()) {
            int rank = card.getRank();
            playerCards.computeIfAbsent(rank, k -> new ArrayList<>()).add(card);
        }

        for (Map.Entry<Integer, List<Card>> entry : playerCards.entrySet()) {
            int rank = entry.getKey();
            List<Card> cards = entry.getValue();
            if (cards.size() == 4) {
                Book book = books.computeIfAbsent(rank, Book::new);
                book.getCards().addAll(cards);
                player.getHand().removeAll(cards);
                inputOutput.printPlayerGotBook(player.getName(), rank);
            }
        }
    }

    // Checks if the game is over (if any player has no cards or the deck is empty)
    private boolean isGameOver() {
        return players.stream().anyMatch(Player::hasNoCards) || deck.getCardsRemaining() == 0;
    }

    // Prints the final score for each player
    private void printScore() {
        inputOutput.printFinalScore();
        for (Player player : players) {
            int score = getPlayerScore(player);
            inputOutput.printPlayerScore(player.getName(), score);
        }
    }

    // Calculates the score for a player based on the books they have
    private int getPlayerScore(Player player) {
        return (int) books.values().stream()
                .filter(book -> book.getCards().get(0).getRank() == player.getHand().get(0).getRank())
                .count();
    }

    // Prints the current scores for all players
    private void printCurrentScores() {
        inputOutput.printCurrentScores();
        for (Player player : players) {
            int score = getPlayerScore(player);
            inputOutput.printPlayerScore(player.getName(), score);
        }
    }
}