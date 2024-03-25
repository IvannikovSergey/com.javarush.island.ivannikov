package com.javarush.entity.island;

import com.javarush.SimulationStatistics;
import com.javarush.entity.creatures.animal.Animal;
import com.javarush.entity.creatures.animal.herbivores.Herbivore;
import com.javarush.entity.creatures.plant.Plant;
import com.javarush.utils.Randomizer;
import com.javarush.utils.Settings;

import java.util.ArrayList;
import java.util.List;

public class IslandSimulator {
    private final IslandModel islandModel;
    private final SimulationStatistics statistics = new SimulationStatistics();
    private final int sizeOfGameFieldX = Settings.SIZE_OF_GAME_FIELD_X;
    private final int sizeOfGameFieldY = Settings.SIZE_OF_GAME_FIELD_Y;
    private final Location[][] grid = new Location[sizeOfGameFieldX][sizeOfGameFieldY];

    public IslandSimulator() {
        this.islandModel = new IslandModel();
        initializeIsland();
    }

    private void initializeIsland() {
        Location[][] grid = islandModel.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Location location = new Location(i, j);
                grid[i][j] = location;
                addAnimalsToLocation(location);
                addPlantsToLocation(location);
            }
        }
    }

    public void simulateDay() {
        Location[][] grid = islandModel.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Location location = grid[i][j];
                if (location != null) { // Проверяем, что location не null
                    List<Animal> animals = location.getAnimals();
                    List<Plant> plants = location.getPlants();


                    // Поедание растений
                    for (Plant plant : new ArrayList<>(plants)) {
                        for (Animal animal : new ArrayList<>(animals)) {
                            if (animal instanceof Herbivore) {
                                // Проверяем, что животное травоядное
                                // Некоторая логика выбора и поедания растения животным
                                if (animal.canEat(plant)) {
                                    animal.eat(plant);
                                    plants.remove(plant);
                                }
                            }
                        }
                    }

                    // Поедание других животных
                    for (Animal animal : new ArrayList<>(animals)) {
                        for (Animal anotherAnimal : new ArrayList<>(animals)) {
                            if (animal != anotherAnimal && animal.canAttack(anotherAnimal)) {
                                animal.attack(anotherAnimal);
                            }
                        }
                    }

                    // Обновление статистики
//                    updateStatistics(location);
                }
            }
        }
    }

    public void updateStatistics(Location location) {
        // Обновляем количество животных и растений в соответствии с текущим состоянием острова
        // для переданной локации

        // Перебираем все виды животных
        for (Class<? extends Animal> animalClass : Settings.ANIMAL_CLASSES) {
            int animalCount = location.getAnimalCount(animalClass);
            statistics.updateAnimalCountPerLocation(location, animalClass, animalCount);
        }

        // Генерируем случайное количество растений
        double plantAmount = Randomizer.getRandNumFromTo(0, Settings.MAX_AMOUNT_OF_PLANT_ON_ONE_LOCATION);
        // Обновляем статистику для растений
        statistics.updatePlantCount(location, (int) plantAmount);
    }


    public void printStatistics() {
        statistics.printStatistics();
    }

    private void addAnimalsToLocation(Location location) {
        for (Class<? extends Animal> animalClass : Settings.ANIMAL_CLASSES) {
            double[] parameters = Settings.BASIC_PARAMETERS_OF_ANIMALS.get(animalClass);
            int maxPopulationOnLocation = (int) parameters[1];

            int animalCount = Randomizer.getRandNumFromTo(0, maxPopulationOnLocation);

            for (int i = 0; i < animalCount; i++) {
                Animal animal = createRandomAnimal(location);
                location.addAnimal(animal);
            }
        }
    }

    private void addPlantsToLocation(Location location) {
        if (Randomizer.getRandBool()) {
            int plantCount = Randomizer.getRandNumFromTo(0, Settings.MAX_AMOUNT_OF_PLANT_ON_ONE_LOCATION);
            for (int i = 0; i < plantCount; i++) {
                Plant plant = new Plant(location);
                location.addPlant(plant);
            }
        }
    }

    private Animal createRandomAnimal(Location location) {
        Class<? extends Animal>[] animalTypes = Settings.ANIMAL_CLASSES.toArray(new Class[0]);
        int randomIndex = Randomizer.getRandNumFromTo(0, animalTypes.length - 1);
        Class<? extends Animal> randomAnimalType = animalTypes[randomIndex];

        double[] parameters = Settings.BASIC_PARAMETERS_OF_ANIMALS.get(randomAnimalType);
        double weight = parameters[0];
        int maxPopulationOnLocation = (int) parameters[1];
        int maxMoveSpeed = (int) parameters[2];
        double foodRequiredForFullness = parameters[3];

        try {
            java.lang.reflect.Constructor<? extends Animal> constructor = randomAnimalType
                    .getDeclaredConstructor(Location.class, int.class, int.class, int.class, double.class);

            return constructor.newInstance(location, (int) weight, maxPopulationOnLocation, maxMoveSpeed,
                    foodRequiredForFullness);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

