package com.javarush.entity.island.location;

/**
 * Класс имплементирующий интерфейс LocationProvider для управления локациями
 */
public class LocationManager implements LocationProvider {
  @Override
  public Location[][] getLocations(int xPosition, int yPosition) {
    return new Location[xPosition][yPosition];
  }
}
