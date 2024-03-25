package com.javarush.entity.creatures.animal.predators;

import com.javarush.entity.creatures.animal.Animal;
import com.javarush.entity.creatures.plant.Plant;
import com.javarush.entity.island.Location;
import com.javarush.utils.Randomizer;
import com.javarush.utils.Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Predators extends Animal {

  public Predators(Location location, int weight, int maxPopulationOnLocation, int maxMoveSpeed,
      double foodRequiredForFullness) {
    super(location, weight, maxPopulationOnLocation, maxMoveSpeed, foodRequiredForFullness);
  }

  @Override
  public boolean canEat(Animal animal) {
    return true;
  }

  @Override
  public boolean canEat(Plant plant) {
    return false;
  }

  @Override
  public void eat(Plant plant) {

  }

  @Override
  public void eat(Animal animalToAttack) {
    List<Animal> animals = location.getAnimals();

    // Перебираем каждое животное на локации
    for (Animal animal : animals) {
      // Проверяем, можно ли атаковать это животное
      if (canAttack(animal)) {
        // Получаем вероятность атаки
        int attackChance = getAttackChance(animal.getClass());
        // Генерируем случайное число от 1 до 100
        int randomChance = (int) (Math.random() * 100) + 1;
        // Если случайное число меньше или равно вероятности атаки, атакуем животное
        if (randomChance <= attackChance) {
          attack(animal);
        }
      }
    }
  }

  // Метод для получения вероятности атаки для заданного класса животного
  private int getAttackChance(Class<? extends Animal> preyClass) {
    Map<Class<? extends Animal>, Integer> chanceToEat = Settings.CHANCE_TO_EAT.get(getClass());
    // Если для данного класса хищника нет информации о вероятности атаки на этот вид жертвы, возвращаем 0
    if (chanceToEat == null || !chanceToEat.containsKey(preyClass)) {
      return 0;
    }
    // Возвращаем вероятность атаки
    return chanceToEat.get(preyClass);
  }

  public void attack(Animal prey) {
    int chance = attackChance(prey);
    if (Randomizer.getRandNum(100) < chance) {
      prey.die();
//      System.out.println(getClass().getSimpleName() + " attacked and killed " + prey.getClass().getSimpleName());
    }
  }

  private int attackChance(Animal prey) {
    // Получаем классы хищников и соответствующие вероятности атаки для данного вида добычи
    Map<Class<? extends Animal>, Integer> preyChanceToBeEaten = Settings.CHANCE_TO_EAT.get(prey.getClass());

    // Если для данного вида добычи нет вероятностей атаки, возвращаем нулевую вероятность
    if (preyChanceToBeEaten == null) {
      return 0;
    }

    // Получаем вероятность атаки для данного класса хищника
    Integer chance = preyChanceToBeEaten.get(getClass());

    // Если вероятность атаки не определена для данного хищника, возвращаем нулевую вероятность
    if (chance == null) {
      return 0;
    }

    return chance;
  }
}
