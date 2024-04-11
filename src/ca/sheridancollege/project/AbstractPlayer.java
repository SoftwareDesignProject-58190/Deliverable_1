package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPlayer {
    protected List<Card> hand; // The cards in the player's hand
    protected String name; // The name of the player

    public AbstractPlayer(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }

    // Adds a card to the player's hand
    public void addCard(Card card) {
        hand.add(card);
    }

    // Checks if the player has a card with the given rank
    public boolean hasCard(int rank) {
        return hand.stream().anyMatch(card -> card.getRank() == rank);
    }

    // Removes and returns all cards with the given rank from the player's hand
    public List<Card> removeCards(int rank) {
        List<Card> cards = new ArrayList<>();
        hand.removeIf(card -> {
            if (card.getRank() == rank) {
                cards.add(card);
                return true;
            }
            return false;
        });
        return cards;
    }

    // Returns the player's name
    public String getName() {
        return name;
    }

    // Returns the player's hand
    public List<Card> getHand() {
        return hand;
    }

    // Checks if the player has no cards
    public boolean hasNoCards() {
        return hand.isEmpty();
    }
}