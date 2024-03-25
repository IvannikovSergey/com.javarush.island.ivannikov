package com.javarush.entity.creatures.animal.herbivores;

import com.javarush.entity.creatures.animal.Animal;
import com.javarush.entity.creatures.plant.Plant;
import com.javarush.entity.island.Location;
import com.javarush.utils.Settings;

public class Herbivore extends Animal {

  public Herbivore(Location location, int weight, int maxPopulationOnLocation, int maxMoveSpeed,
      double foodRequiredForFullness) {
    super(location, weight, maxPopulationOnLocation, maxMoveSpeed, foodRequiredForFullness);
  }

  @Override
  public boolean canEat(Animal animal) {
    return false;
  }

  @Override
  public boolean canEat(Plant plant) {
    // Реализация логики, позволяющей травоядному животному съесть растение
    // Например, можно проверить тип растения и прочие характеристики животного
    return true; // Здесь нужно прописать свою логику
  }

  @Override
  // Метод, который позволяет травоядным животным поедать траву на локации
  public void eat(Plant plant) {
    // Получаем количество травы на локации
    int plantAmount = (int) location.getPlantAmount();

    // Получаем количество травы, необходимое для насыщения животного
    double foodRequiredForFullness = Settings.BASIC_PARAMETERS_OF_ANIMALS.get(this.getClass())[3];

    // Определяем, сколько травы съест животное
    int plantToEat = (int) Math.min(foodRequiredForFullness, plantAmount);

    // Проверяем, достаточно ли травы для насыщения животного
    if (plantToEat > 0) {
      // Уменьшаем количество травы на локации
      location.reducePlant(plantToEat);

      // Увеличиваем сытость животного
      fullness += plantToEat;
    }
  }

  @Override
  public void eat(Animal animal) {

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
