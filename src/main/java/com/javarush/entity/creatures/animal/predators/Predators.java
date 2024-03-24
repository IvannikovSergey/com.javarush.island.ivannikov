package com.javarush.entity.creatures.animal.predators;

import com.javarush.entity.creatures.animal.Animal;
import com.javarush.entity.island.Location;
import com.javarush.utils.Randomizer;
import com.javarush.utils.Settings;
import java.util.Map;

public class Predators extends Animal {

  public Predators(Location location, int weight, int maxPopulationOnLocation, int maxMoveSpeed,
      double foodRequiredForFullness) {
    super(location, weight, maxPopulationOnLocation, maxMoveSpeed, foodRequiredForFullness);
  }

  @Override
  public void eat() {
//    Location currentLocation = getLocation();
//    List<Animal> animals = currentLocation.getAnimals();
//    for (Animal animal : new ArrayList<>(animals)) {
//      if (animal instanceof Predators && animal != this && animal.isAlive()) {
//        attack(animal);
//      }
//    }
  }

  public void attack(Animal prey) {
    int chance = attackChance(prey);
    if (Randomizer.getRandNum(100) < chance) {
      prey.die();
      System.out.println(getClass().getSimpleName() + " attacked and killed " + prey.getClass().getSimpleName());
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
