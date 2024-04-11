package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckOfCards {
    private List<Card> cards; // The cards in the deck

    public DeckOfCards() {
        cards = new ArrayList<>();
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        for (String suit : suits) {
            for (int rank = 1; rank <= 13; rank++) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    // Shuffles the cards in the deck
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // Draws a card from the deck, or returns null if the deck is empty
    public Card drawCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }

    // Returns the number of cards remaining in the deck
    public int getCardsRemaining() {
        return cards.size();
    }
}