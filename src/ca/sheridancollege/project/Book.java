package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private int rank; // The rank of the book (1-13)
    private List<Card> cards; // The cards that make up the book

    public Book(int rank) {
        this.rank = rank;
        cards = new ArrayList<>();
    }

    public int getRank() {
        return rank;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isComplete() {
        return cards.size() == 4;
    }

    public List<Card> getCards() {
        return cards;
    }
}