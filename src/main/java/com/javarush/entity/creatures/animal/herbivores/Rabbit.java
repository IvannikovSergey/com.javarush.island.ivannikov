package com.javarush.entity.creatures.animal.herbivores;

import com.javarush.entity.island.Location;

public class Rabbit extends Herbivore {

  public Rabbit(Location location, int weight, int maxPopulationOnLocation,
      int maxMoveSpeed, double foodRequiredForFullness) {
    super(location, weight, maxPopulationOnLocation, maxMoveSpeed, foodRequiredForFullness);
  }
}
