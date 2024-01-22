package com.example.poker.handComparer.util;

import com.example.poker.handComparer.Combination;
import com.example.poker.model.Card;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Solver {

    private final List<Card> hand;
    private final Map<Integer, Integer> histogram;

    public Solver(List<Card> hand) {
        this.hand = hand;
        this.histogram = getCardsHistogram();
    }

    public Combination resolveCombination() {
        int size = histogram.size();
        if (size == 5) {
            boolean straight = isStraight();
            boolean flash = isFlush();
            if (straight && flash) {
                return Combination.STRAIGHT_FLUSH;
            } else if (straight) {
                return Combination.STRAIGHT;
            } else if (flash) {
                return Combination.FLUSH;
            } else {
                return Combination.HIGH_CARD;
            }
        } else if (size == 4) {
            return Combination.ONE_PAIR;
        } else if (histogram.values().stream().filter(value -> value.equals(2)).count() == 2) {
            return Combination.TWO_PAIRS;
        } else if (histogram.containsValue(3) && histogram.containsValue(1)) {
            return Combination.THREE_OF_A_KIND;
        } else if (histogram.containsValue(3) && histogram.containsValue(2)) {
            return Combination.FULL_HOUSE;
        }
        return Combination.QUADS;
    }

    private boolean isStraight() {
        return IntStream.range(0, hand.size() - 2)
                .allMatch(i -> hand.get(i).rank() - hand.get(i + 1).rank() == 1);
    }

    private boolean isFlush() {
        return hand.stream()
                .map(Card::suit)
                .distinct()
                .count() == 1;
    }

    public Map<Integer, Integer> getCardsHistogram() {
        Map<Integer, Integer> rankHistogram = new HashMap<>();
        for (Card card : hand) {
            int rank = card.rank();
            rankHistogram.put(rank, rankHistogram.getOrDefault(rank, 0) + 1);
        }
        return rankHistogram;
    }

}