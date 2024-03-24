package com.javarush.utils;

import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {
    private Randomizer() {
    }

    /**
     * Генерирует случайное число
     *
     * @return случайное целое число
     */
    public static int getRandNum(int i) {
        return ThreadLocalRandom.current().nextInt();
    }

    /**
     * Генерирует случайное число в нужном диапазоне
     *
     * @param from нижняя граница (включая) диапазона случайного числа
     * @param to   верхняя граница (не включая) диапазона случайного числа
     * @return случайное целое число в выбранном диапазоне
     */
    public static int getRandNumFromTo(int from, int to) {
        if (to <= from) {
            throw new IllegalArgumentException("Неправильный диапазон: to должно быть больше, чем from");
        }
        return ThreadLocalRandom.current().nextInt(from, to);
    }

    /**
     * Генерирует случайное булево значение
     *
     * @return случайное булево значение
     */
    public static boolean getRandBool() {
        return ThreadLocalRandom.current().nextBoolean();
    }

}
