package com.example.poker.handComparer;

import com.example.poker.model.Card;
import com.example.poker.handComparer.util.Converter;
import com.example.poker.handComparer.util.Solver;
import lombok.Getter;
import lombok.ToString;

import java.util.*;
import java.util.stream.IntStream;

@Getter
@ToString
public class PokerHand implements Comparable<PokerHand> {

    private final List<Card> hand;
    private final Combination combination;
    private final Map<Integer, Integer> histogram;

    public PokerHand(String hand) {
        this.hand = Converter.convertStringToCards(hand);
        final Solver solver = new Solver(this.hand);
        this.combination = solver.resolveCombination();
        this.histogram = solver.getCardsHistogram();
    }

    @Override
    public int compareTo(PokerHand other) {
        int compare = Integer.compare(other.combination.ordinal(), this.combination.ordinal());
        if (compare == 0) {
            return compareEqualCombinations(this, other);
        }
            return compare;
    }

    private int compareEqualCombinations(PokerHand thisHand, PokerHand otherHand) {
        List<Integer> thisHandSortedHistogram = sortHistogramHighToLow(thisHand.getHistogram());
        List<Integer> otherHandSortedHistogram = sortHistogramHighToLow(otherHand.getHistogram());
        return IntStream.range(0, thisHandSortedHistogram.size())
                .map(i -> Integer.compare(otherHandSortedHistogram.get(i), thisHandSortedHistogram.get(i)))
                .filter(rankComparison -> rankComparison != 0)
                .findFirst()
                .orElse(0);
    }

    private List<Integer> sortHistogramHighToLow(Map<Integer, Integer> histogram) {
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(histogram.entrySet());
        entryList.sort((entry1, entry2) -> {
            if (entry1.getValue().equals(entry2.getValue())) {
                return entry2.getKey().compareTo(entry1.getKey());
            }
            return entry2.getValue().compareTo(entry1.getValue());
        });
        List<Integer> sortedHistogram = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : entryList) {
            sortedHistogram.add(entry.getKey());
        }
        return sortedHistogram;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokerHand pokerHand = (PokerHand) o;
        return Objects.equals(hand, pokerHand.hand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hand);
    }

}