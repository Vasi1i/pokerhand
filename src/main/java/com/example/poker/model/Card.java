package com.example.poker.model;

public record Card(int rank, Suit suit) implements Comparable<Card> {

    @Override
    public int compareTo(Card other) {
        return Integer.compare(this.rank, other.rank);
    }

}
