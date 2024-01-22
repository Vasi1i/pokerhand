package com.example.poker.handComparer;

public enum Combination implements Comparable<Combination> {

    HIGH_CARD,
    ONE_PAIR,
    TWO_PAIRS,
    THREE_OF_A_KIND,
    STRAIGHT,
    FLUSH,
    FULL_HOUSE,
    QUADS,
    STRAIGHT_FLUSH;

}