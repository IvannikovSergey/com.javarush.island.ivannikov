package com.javarush.entity.island.island;

import com.javarush.entity.island.location.Location;
import com.javarush.entity.island.location.LocationManager;

/*
 * Класс Island, использующий стратегии для получения локаций и вывода острова на экран
 */
public class Island {
  private final LocationManager locationManager;
  private final ConsoleIslandPrinter consoleIslandPrinter;
  private int xPosition;
  private int yPosition;

  public Island(LocationManager locationManager, ConsoleIslandPrinter consoleIslandPrinter) {
    this.locationManager = locationManager;
    this.consoleIslandPrinter = consoleIslandPrinter;
  }

  public Location[][] getlocation() {
    return locationManager.getLocations(xPosition, yPosition);
  }

  public void print() {
    consoleIslandPrinter.printIsland(locationManager.getLocations(xPosition, yPosition));
  }
}
