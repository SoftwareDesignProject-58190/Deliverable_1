package ca.sheridancollege.project;

public class Card {
    private int rank; // The rank of the card (1-13)
    private String suit; // The suit of the card

    public Card(int rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}