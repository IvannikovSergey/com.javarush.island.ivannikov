package com.javarush;

import com.javarush.entity.island.IslandModel;
import com.javarush.entity.island.IslandSimulator;

public class Main {
  public static void main(String[] args) {
    IslandSimulator islandSimulator = new IslandSimulator();
    islandSimulator.simulateDay();
    islandSimulator.printStatistics();
  }
}
