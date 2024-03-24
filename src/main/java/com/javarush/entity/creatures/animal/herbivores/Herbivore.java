package com.javarush.entity.creatures.animal.herbivores;

import com.javarush.entity.creatures.animal.Animal;
import com.javarush.entity.island.Location;

class Herbivore extends Animal {

  public Herbivore(Location location, int weight, int maxPopulationOnLocation, int maxMoveSpeed,
      double foodRequiredForFullness) {
    super(location, weight, maxPopulationOnLocation, maxMoveSpeed, foodRequiredForFullness);
  }

  @Override
  public void eat() {

  }

//  @Override
//  public void eat() {
//    if (location.hasPlants()) {
//      // Потребляем растения
//      double foodWeight = location.removePlants(foodRequiredForFullness);
////      System.out.println(getClass().getSimpleName() + " consumed " + foodWeight + " kg of plants.");
//      weight += foodWeight;
//    } else {
////      System.out.println(getClass().getSimpleName() + " couldn't find any plants to eat on its location.");
//    }
//  }
}
