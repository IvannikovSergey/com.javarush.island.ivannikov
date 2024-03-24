package com.javarush;

import com.javarush.entity.island.IslandModel;

public class Main {
  public static void main(String[] args) {
    IslandModel island = new IslandModel();
    island.simulateDay();
    island.printStatistics();
  }
}
