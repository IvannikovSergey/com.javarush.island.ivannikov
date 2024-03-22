package com.javarush.entity.island.location;

import com.javarush.entity.island.location.Location;

/*
 * Интерфейс для получения локаций
 */
public interface LocationProvider {
  Location[][] getLocations(int xPosition, int yPosition);
}
