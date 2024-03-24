package com.javarush.entity.creatures.animal.predators;

import com.javarush.entity.island.Location;

public class Eagle extends Predators {

  public Eagle(Location location, int weight, int maxPopulationOnLocation,
      int maxMoveSpeed, double foodRequiredForFullness) {
    super(location, weight, maxPopulationOnLocation, maxMoveSpeed, foodRequiredForFullness);
  }
}
