package com.javarush;

import com.javarush.entity.creatures.animal.Animal;
import com.javarush.entity.island.Location;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SimulationStatistics {
  private Map<Class<? extends Animal>, Integer> animalCounts;
  private Map<Location, LocationStatistics> locationStatistics;
  private TreeMap<Location, LocationStatistics> sortedLocationStatistics;

  public SimulationStatistics() {
    animalCounts = new HashMap<>();
    locationStatistics = new HashMap<>();
    sortedLocationStatistics = new TreeMap<>(new LocationComparator());
  }

  public void updateAnimalCount(Class<? extends Animal> animalClass, int count) {
    animalCounts.put(animalClass, count);
  }

  public void updateAnimalCountPerLocation(Location location, Class<? extends Animal> animalClass, int count) {
    LocationStatistics stats = getLocationStatistics(location);
    stats.updateAnimalCount(animalClass, count);
    sortedLocationStatistics.put(location, stats);
  }

  public void updatePlantCount(Location location, int count) {
    LocationStatistics stats = getLocationStatistics(location);
    stats.setPlantCount(count);
    sortedLocationStatistics.put(location, stats);
  }

  private LocationStatistics getLocationStatistics(Location location) {
    return locationStatistics.computeIfAbsent(location, k -> new LocationStatistics());
  }

  public void printStatistics() {
    System.out.println("----- Статистика животных и растений на локации -----");
    for (Map.Entry<Location, LocationStatistics> entry : sortedLocationStatistics.entrySet()) {
      Location location = entry.getKey();
      LocationStatistics stats = entry.getValue();
      System.out.println("Локация (" + location.getxPos() + ", " + location.getyPos() + "):");
      System.out.println("  Растений: " + stats.getPlantCount());
      for (Map.Entry<Class<? extends Animal>, Integer> animalEntry : stats.getAnimalCounts().entrySet()) {
        System.out.println("  " + animalEntry.getKey().getSimpleName() + ": " + animalEntry.getValue());
      }
    }
  }

  // Компаратор для сравнения локаций
  private static class LocationComparator implements Comparator<Location> {
    @Override
    public int compare(Location loc1, Location loc2) {
      if (loc1.getxPos() != loc2.getxPos()) {
        return Integer.compare(loc1.getxPos(), loc2.getxPos());
      } else {
        return Integer.compare(loc1.getyPos(), loc2.getyPos());
      }
    }
  }

  private static class LocationStatistics {
    private Map<Class<? extends Animal>, Integer> animalCounts;
    private int plantCount;

    public LocationStatistics() {
      animalCounts = new HashMap<>();
      plantCount = 0;
    }

    public Map<Class<? extends Animal>, Integer> getAnimalCounts() {
      return animalCounts;
    }

    public int getPlantCount() {
      return plantCount;
    }

    public void updateAnimalCount(Class<? extends Animal> animalClass, int count) {
      animalCounts.put(animalClass, count);
    }

    public void setPlantCount(int count) {
      plantCount = count;
    }
  }
}



