package com.javarush.entity.island;

import com.javarush.entity.creatures.animal.Animal;
import com.javarush.entity.creatures.plant.Plant;
import com.javarush.utils.Randomizer;
import com.javarush.utils.Settings;
import java.util.ArrayList;
import java.util.List;

public class Location {

  private int xPos;
  private int yPos;
  private int maxAmountPlants = Settings.MAX_AMOUNT_OF_PLANT_ON_ONE_LOCATION;
  private List<Animal> animals;
  private double plantAmount;

  public Location(int x, int y) {
    this.xPos = x;
    this.yPos = y;
    this.animals = new ArrayList<>();
  }

  public void plantGrowth() {
    double plantGrowth = Randomizer.getRandNumFromTo(0, maxAmountPlants);
    if (this.plantAmount + plantGrowth < maxAmountPlants) {
      this.plantAmount += plantGrowth;
    } else {
      this.plantAmount = maxAmountPlants;
    }
  }

  public void addAnimal(Animal animal) {
    animals.add(animal);
  }

  public void removeAnimal(Animal animal) {
    animals.remove(animal);
  }

  public int getAnimalCount(Class<?> animalClass) {
    int count = 0;
    for (Animal animal : animals) {
      if (animalClass.isInstance(animal)) {
        count++;
      }
    }
    return count;
  }

  public List<Animal> getAnimals() {
    return animals;
  }

  public void addPlant(Plant plant) {
    // Проверяем, чтобы количество растений на локации не превысило максимальное значение
    if (this.plantAmount < maxAmountPlants) {
      this.plantAmount++;
    }
  }

  public void removePlant(Plant plant) {
    // Удаляем одно растение с локации
    if (this.plantAmount > 0) {
      this.plantAmount--;
    }
  }

  public int getxPos() {
    return xPos;
  }

  public int getyPos() {
    return yPos;
  }
}
