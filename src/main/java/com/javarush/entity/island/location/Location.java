package com.javarush.entity.island.location;

import com.javarush.entity.creatures.animal.Animal;
import com.javarush.Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Location {

  private final int xPosition;
  private final int yPosition;
  private double plant;
  private final Map<Class<? extends Animal>, Set<Animal>> animals = new ConcurrentHashMap<>();

  public Location(int y, int x) {
    this.yPosition = y;
    this.xPosition = x;
  }

  public void start() {
    for (Class<? extends Animal> animalClass : animals.keySet()) {
      for (Animal animal : animals.get(animalClass)) {
        Runnable animalTask = () -> {
          // Реализация задачи для животного
          animal.eat();
          animal.move();
          animal.multiply();
          animal.die();
        };
        animalTask.run();
      }
    }
    Runnable plantTask = () -> {
      // Реализация задачи для растения
      plantGrowth();
    };
    plantTask.run();
  }

  public void plantGrowth() {
    double plantGrowth = Settings.GROWTH_OF_PLANT;
    if (this.plant + plantGrowth < Settings.MAX_AMOUNT_OF_PLANT_ON_ONE_LOCATION) {
      this.plant += plantGrowth;
    } else {
      this.plant = Settings.MAX_AMOUNT_OF_PLANT_ON_ONE_LOCATION;
    }
  }

  public Map<Class<? extends Animal>, Set<Animal>> getAnimals() {
    return animals;
  }

}

