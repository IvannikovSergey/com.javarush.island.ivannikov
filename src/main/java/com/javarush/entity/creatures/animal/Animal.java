package com.javarush.entity.creatures.animal;

import com.javarush.entity.creatures.plant.Plant;
import com.javarush.entity.island.Location;

public abstract class Animal {

  public Location location;
  protected boolean isAlive;
  protected int weight;
  protected int maxPopulationOnLocation;
  protected int maxMoveSpeed;
  protected double foodRequiredForFullness;
  protected int fullness;
  protected int maxFullness;

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

  public abstract boolean canEat(Animal animal);

  public abstract boolean canEat(Plant plant);

  public abstract void eat(Plant plant);
  public abstract void eat(Animal animal);

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

  public boolean canAttack(Animal prey) {
    // Хищник может атаковать только живую жертву
    if (!prey.isAlive()) {
      return false;
    }

    // Если жертва тяжелее хищника, то хищник не может атаковать
    if (prey.getWeight() >= this.getWeight()) {
      return false;
    }

    // Предположим, что у каждого хищника есть свой максимальный вес жертвы, которую он может атаковать
    double maxPreyWeight = this.getWeight() * 0.5; // Например, хищник может атаковать жертву, вес которой не превышает половину его собственного веса
    if (prey.getWeight() > maxPreyWeight) {
      return false;
    }

    // В противном случае хищник может атаковать эту жертву
    return true;
  }

  public void attack(Animal prey) {
    // Предполагаем, что атака убивает жертву
    prey.die();
    // И, возможно, вносит изменения в хищника (например, увеличивает его насыщенность)
    this.feed(prey);
  }

  private void feed(Animal prey) {
    // Рассчитываем количество еды, которую хищник получает от жертвы
    // Например, можно использовать вес жертвы и другие факторы
    double foodAmount = prey.getWeight() * 1.0; // Пусть хищник получает 100% от веса жертвы в виде пищи

    // Увеличиваем насыщенность хищника на полученное количество еды
    // Например, у хищника может быть параметр "насыщенность", который увеличивается на количество еды, полученное от жертвы
    this.fullness += (int) foodAmount;

    // Проверяем, не превышает ли насыщенность хищника максимальное значение
    // Например, если максимальная насыщенность хищника равна 100, то убедимся, что она не превышает это значение
    if (this.fullness > this.maxFullness) {
      this.fullness = this.maxFullness;
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
