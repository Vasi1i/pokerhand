package com.example.poker.handComparer.util;

import com.example.poker.model.Card;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.example.poker.handComparer.util.Mapper.CARD_RANK;
import static com.example.poker.handComparer.util.Mapper.CARD_SUIT;

public class Converter {

    public static List<Card> convertStringToCards(String cardString) {
        List<Card> cardList = new ArrayList<>();
        String[] cardTokens = cardString.split(" ");
        for (String cardToken : cardTokens) {
            int rank = convertCardRankToInteger(cardToken.charAt(0));
            char suit = cardToken.charAt(1);
            Card card = new Card(rank, CARD_SUIT.get(suit));
            cardList.add(card);
        }
        cardList.sort(Comparator.comparingInt(Card::rank).reversed());
        return cardList;
    }

    private static int convertCardRankToInteger(char rankChar) {
        if (CARD_RANK.containsKey(rankChar)) {
            return CARD_RANK.get(rankChar);
        } else {
            throw new IllegalArgumentException("Invalid rank character: " + rankChar);
        }
    }

}