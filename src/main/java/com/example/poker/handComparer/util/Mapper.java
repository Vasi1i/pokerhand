package com.example.poker.handComparer.util;

import com.example.poker.model.Suit;

import java.util.Map;

public class Mapper {

    private Mapper() {
        throw new IllegalStateException("Utility class");
    }

    public static final Map<Character, Suit> CARD_SUIT;
    public static final Map<Character, Integer> CARD_RANK;

    static {
        CARD_SUIT = Map.ofEntries(
                Map.entry('C', Suit.HEARTS),
                Map.entry('D', Suit.DIAMONDS),
                Map.entry('H', Suit.CLUBS),
                Map.entry('S', Suit.SPADES));
    }

    static {
        CARD_RANK = Map.ofEntries(
                Map.entry('2', 2),
                Map.entry('3', 3),
                Map.entry('4', 4),
                Map.entry('5', 5),
                Map.entry('6', 6),
                Map.entry('7', 7),
                Map.entry('8', 8),
                Map.entry('9', 9),
                Map.entry('T', 10),
                Map.entry('J', 11),
                Map.entry('Q', 12),
                Map.entry('K', 13),
                Map.entry('A', 14));
    }

}