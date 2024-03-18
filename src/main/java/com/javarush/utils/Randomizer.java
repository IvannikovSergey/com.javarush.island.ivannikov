package com.javarush.utils;

import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {
    private Randomizer() {
    }

    public int getRandNum() {
        return ThreadLocalRandom.current().nextInt();
    }

    public int getRandNumFromTo(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from, to);
    }

    public boolean getRandBool() {
        return ThreadLocalRandom.current().nextBoolean();
    }

}
