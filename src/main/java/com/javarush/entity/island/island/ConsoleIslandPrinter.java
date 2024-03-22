package com.javarush.entity.island.island;

import com.javarush.entity.island.location.Location;

/**
 *Класс имплементирующий интерфейс IslandPrinter для вывода острова на экран
 */

public class ConsoleIslandPrinter implements IslandPrinter {
  @Override
  public void printIsland(Location[][] locations) {
    for (int y = 0; y < locations[0].length; y++) {
      for (int x = 0; x < locations.length; x++) {
        System.out.print(locations[x][y]);
      }
      System.out.println();
    }
    System.out.println("============================================================================");
  }
}
