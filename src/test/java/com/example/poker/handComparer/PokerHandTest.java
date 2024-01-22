package com.example.poker.handComparer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class PokerHandTest {

    @Test
    @DisplayName("Comparison of non-equivalent combinations")
    void compareNonEqualCombinations() {
        PokerHand pokerHand1 = new PokerHand("2S 2H 4C 4S 8D");
        PokerHand pokerHand2 = new PokerHand("2S 3H 4C 5C 9D");
        List<PokerHand> hands = new ArrayList<>();
        hands.add(pokerHand1);
        hands.add(pokerHand2);
        Collections.sort(hands);
        List<PokerHand> expected = List.of(pokerHand1, pokerHand2);
        assertThat(hands, equalTo(expected));
    }

    @Test
    @DisplayName("Comparison of equivalent combinations")
    void compareEqualCombinations() {
        PokerHand pokerHand1 = new PokerHand("2S 3H 4C 5C 8D");
        PokerHand pokerHand2 = new PokerHand("2S 3H 4C 5C 9D");
        List<PokerHand> hands = new ArrayList<>();
        hands.add(pokerHand1);
        hands.add(pokerHand2);
        Collections.sort(hands);
        List<PokerHand> expected = List.of(pokerHand2, pokerHand1);
        assertThat(hands, equalTo(expected));
    }

    @Test
    @DisplayName("Definition of combinations")
    void combinationDefinition() {
        assertThat(new PokerHand("2S 3H 4C 5C 8D").getCombination(), equalTo(Combination.HIGH_CARD));
        assertThat(new PokerHand("2S 2H 4C 5C 8D").getCombination(), equalTo(Combination.ONE_PAIR));
        assertThat(new PokerHand("2S 2H 4C 4S 8D").getCombination(), equalTo(Combination.TWO_PAIRS));
        assertThat(new PokerHand("2S 2H 2C 5C 8D").getCombination(), equalTo(Combination.THREE_OF_A_KIND));
        assertThat(new PokerHand("2S 3H 4C 5C 6D").getCombination(), equalTo(Combination.STRAIGHT));
        assertThat(new PokerHand("2S 3S 4S 5S 8S").getCombination(), equalTo(Combination.FLUSH));
        assertThat(new PokerHand("2S 2H 2C 2D 8D").getCombination(), equalTo(Combination.QUADS));
        assertThat(new PokerHand("2S 3S 4S 5S 6S").getCombination(), equalTo(Combination.STRAIGHT_FLUSH));
    }

}