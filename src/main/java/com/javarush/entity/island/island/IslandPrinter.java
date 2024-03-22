package com.javarush.entity.island.island;

import com.javarush.entity.island.location.Location;

/*
 * Интерфейс для вывода острова на экран
 */
public interface IslandPrinter {

  void printIsland(Location[][] locations);
}
