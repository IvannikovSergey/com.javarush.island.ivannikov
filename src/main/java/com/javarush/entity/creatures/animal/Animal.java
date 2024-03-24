package com.javarush.entity.creatures.animal;

import com.javarush.entity.island.Location;

public abstract class Animal {

  public Location location;
  protected boolean isAlive;
  protected int weight;
  protected int maxPopulationOnLocation;
  protected int maxMoveSpeed;
  protected double foodRequiredForFullness;

  public Animal(Location location, int weight, int maxPopulationOnLocation, int maxMoveSpeed,
      double foodRequiredForFullness) {
    this.location = location;
    this.weight = weight;
    this.maxPopulationOnLocation = maxPopulationOnLocation;
    this.maxMoveSpeed = maxMoveSpeed;
    this.foodRequiredForFullness = foodRequiredForFullness;
    isAlive = true;
  }

  public void move(Location newLocation) {
    location.removeAnimal(this);
    newLocation.addAnimal(this);
    location = newLocation;
  }

  public abstract void eat();

  /*
   * Этот метод проверяет, что оба родителя живы,
   * находятся на одной локации и имеют одинаковый тип животного.
   * Затем он проверяет, что на локации не превышено максимальное количество животных этого типа.
   * Если все условия выполнены,
   * создается новый экземпляр потомка того же типа животного и добавляется на локацию.
   *
   */
  public void reproduce(Animal partner) {
    // Проверяем, что оба животных живы и находятся на одной локации
    if (isAlive() && partner != null && partner.isAlive() && location == partner.location) {
      // Проверяем, что типы животных совместимы для размножения
      if (getClass() == partner.getClass()) {
        // Проверяем, что на локации не превышено максимальное количество животных
        if (location.getAnimalCount(getClass()) < maxPopulationOnLocation) {
          // Создаем нового потомка того же типа животного и добавляем его на локацию
          try {
            Animal offspring = getClass().getDeclaredConstructor(Location.class)
                .newInstance(location);
            location.addAnimal(offspring);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

  public void die() {
    isAlive = false;
    location.removeAnimal(this);
  }

  public Location getLocation() {
    return location;
  }

  public boolean isAlive() {
    return isAlive;
  }

  public int getWeight() {
    return weight;
  }

  public int getMaxPopulationOnLocation() {
    return maxPopulationOnLocation;
  }

  public int getMaxMoveSpeed() {
    return maxMoveSpeed;
  }

  public double getFoodRequiredForFullness() {
    return foodRequiredForFullness;
  }
}
