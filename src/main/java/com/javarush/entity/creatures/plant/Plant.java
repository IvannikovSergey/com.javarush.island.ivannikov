package com.javarush.entity.creatures.plant;

import com.javarush.entity.island.Location;

public class Plant {
  private final Location location;

  public Plant(Location location) {
    this.location = location;
  }

  public void growUp() {
    location.plantGrowth();
  }
}
