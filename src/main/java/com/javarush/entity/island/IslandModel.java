package com.javarush.entity.island;

import com.javarush.SimulationStatistics;
import com.javarush.entity.creatures.animal.Animal;
import com.javarush.entity.creatures.plant.Plant;
import com.javarush.utils.Randomizer;
import com.javarush.utils.Settings;
import java.util.ArrayList;
import java.util.List;

public class IslandModel {

  private final int sizeOfGameFieldX = Settings.SIZE_OF_GAME_FIELD_X;
  private final int sizeOfGameFieldY = Settings.SIZE_OF_GAME_FIELD_Y;
  private final Location[][] grid;
  private final SimulationStatistics statistics;

  public IslandModel() {
    grid = new Location[sizeOfGameFieldX][sizeOfGameFieldY];
    initializeIsland();
    statistics = new SimulationStatistics();
  }

  public void initializeIsland() {
    for (int x = 0; x < sizeOfGameFieldX; x++) {
      for (int y = 0; y < sizeOfGameFieldY; y++) {
        Location location = new Location(x, y);
        grid[x][y] = location; // Добавляем созданную локацию в массив grid

        // Добавляем животных на локацию
        addAnimalsToLocation(location);

        // Добавляем растения на локацию
        addPlantsToLocation(location);
      }
    }
  }

  private void addAnimalsToLocation(Location location) {
    // Перебираем все виды животных из настроек
    for (Class<? extends Animal> animalClass : Settings.ANIMAL_CLASSES) {
      // Получаем параметры для создания экземпляра животного из настроек
      double[] parameters = Settings.BASIC_PARAMETERS_OF_ANIMALS.get(animalClass);
      int maxPopulationOnLocation = (int) parameters[1];

      // Вычисляем количество животных данного вида на локации (случайным образом от 0 до максимального количества)
      int animalCount = Randomizer.getRandNumFromTo(0, maxPopulationOnLocation);

      // Создаем указанное количество экземпляров животных и добавляем их на локацию
      for (int i = 0; i < animalCount; i++) {
        Animal animal = createRandomAnimal(location);
        location.addAnimal(animal);
      }
    }
  }

  private void addPlantsToLocation(Location location) {
    // Рандомно решаем, будут ли на этой локации растения
    if (Randomizer.getRandBool()) {
      // Создаем растение и добавляем его на локацию
      int plantCount = Randomizer.getRandNumFromTo(0,
          Settings.MAX_AMOUNT_OF_PLANT_ON_ONE_LOCATION);
      // Добавляем сгенерированное количество растений на локацию
      for (int i = 0; i < plantCount; i++) {
        Plant plant = new Plant(location);
//        location.addPlant(plant);
        location.addPlant(plant);
      }
    }
  }

  private Animal createRandomAnimal(Location location) {
    // Получаем классы животных из Settings
    Class<? extends Animal>[] animalTypes = Settings.ANIMAL_CLASSES.toArray(new Class[0]);

    // Рандомно выбираем вид животного из доступных
    int randomIndex = Randomizer.getRandNumFromTo(0, animalTypes.length);
    Class<? extends Animal> randomAnimalType = animalTypes[randomIndex];

    // Получаем параметры для создания экземпляра животного из Settings
    double[] parameters = Settings.BASIC_PARAMETERS_OF_ANIMALS.get(randomAnimalType);
    double weight = parameters[0];
    int maxPopulationOnLocation = (int) parameters[1];
    int maxMoveSpeed = (int) parameters[2];
    double foodRequiredForFullness = parameters[3];

    try {
      // Получаем конструктор класса
      java.lang.reflect.Constructor<? extends Animal> constructor = randomAnimalType
          .getDeclaredConstructor(Location.class, int.class, int.class, int.class, double.class);

      // Используем конструктор для создания экземпляра животного
      return constructor.newInstance(location, (int) weight, maxPopulationOnLocation, maxMoveSpeed,
          foodRequiredForFullness);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public void simulateDay() {
    for (int i = 0; i < sizeOfGameFieldX; i++) {
      for (int j = 0; j < sizeOfGameFieldY; j++) {
        Location location = grid[i][j];
        List<Animal> animals = location.getAnimals();
        for (Animal animal : new ArrayList<>(animals)) {
          animal.eat();
//          animal.reproduce(getPartner(animal));
//          if (animal instanceof Predators) {
//            for (Animal potentialPrey : location.getAnimals()) {
//              if (potentialPrey != animal && potentialPrey.isAlive()) {
//                ((Predators) animal).attack(potentialPrey);
//              }
//            }
//          }
        }
      }
    }
    updateStatistics();
  }

  public void updateStatistics() {
    // Обновляем количество животных и растений в соответствии с текущим состоянием острова
    // перебираем все локации и считаем количество животных и растений на каждой локации

    for (int x = 0; x < sizeOfGameFieldX; x++) {
      for (int y = 0; y < sizeOfGameFieldY; y++) {
        Location location = grid[x][y];
        for (Class<? extends Animal> animalClass : Settings.ANIMAL_CLASSES) {
          int animalCount = location.getAnimalCount(animalClass);
          statistics.updateAnimalCountPerLocation(location, animalClass, animalCount);
        }
        double plantAmount = Randomizer.getRandNumFromTo(0,
            Settings.MAX_AMOUNT_OF_PLANT_ON_ONE_LOCATION);
        statistics.updatePlantCount(location, (int) plantAmount);
        statistics.updatePlantCount(location, (int) plantAmount);
      }
    }
  }

  public void printStatistics() {
    statistics.printStatistics();
  }

  private Animal getPartner(Animal animal) {
    Location currentLocation = animal.location;
    List<Animal> animals = currentLocation.getAnimals();
    for (Animal other : animals) {
      if (other != animal && other.isAlive()) {
        return other;
      }
    }
    return null;
  }
}
