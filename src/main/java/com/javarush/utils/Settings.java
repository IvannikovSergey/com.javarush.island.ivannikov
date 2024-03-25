package com.javarush.utils;

import com.javarush.entity.creatures.animal.Animal;
import com.javarush.entity.creatures.animal.herbivores.*;
import com.javarush.entity.creatures.animal.predators.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Settings {

  public static final int MAX_AMOUNT_OF_PLANT_ON_ONE_LOCATION = 200;
  public static final int SIZE_OF_GAME_FIELD_X = 100;
  public static final int SIZE_OF_GAME_FIELD_Y = 20;

  private Settings() {
    throw new IllegalStateException("Setting class");
  }

  /*
   * Этот код определяет базовые характеристики различных видов животных в виде массива double[] и
   * ассоциирует каждый вид животного с его параметрами с помощью Map<Class<? extends Animal>, double[]>
   * Каждый элемент массива double[] содержит определенные характеристики для конкретного вида животного.
   * В данном случае, эти параметры представляют собой следующие:
   * Вес одного животного, кг - первый элемент массива.
   * Максимальное количество животных этого вида на одной клетке - второй элемент массива.
   * Скорость перемещения, не более чем, клеток за ход - третий элемент массива.
   * Сколько пищи нужно животному для полного насыщения, кг - четвертый элемент массива.
   */
  public static final Map<Class<? extends Animal>, double[]> BASIC_PARAMETERS_OF_ANIMALS = new HashMap<>();

  static {
    BASIC_PARAMETERS_OF_ANIMALS.put(Bear.class, new double[]{500, 5, 2, 80});
    BASIC_PARAMETERS_OF_ANIMALS.put(Boa.class, new double[]{15, 30, 1, 3});
    BASIC_PARAMETERS_OF_ANIMALS.put(Boar.class, new double[]{400, 50, 2, 50});
    BASIC_PARAMETERS_OF_ANIMALS.put(Buffalo.class, new double[]{700, 10, 3, 100});
    BASIC_PARAMETERS_OF_ANIMALS.put(Deer.class, new double[]{300, 20, 4, 50});
    BASIC_PARAMETERS_OF_ANIMALS.put(Fox.class, new double[]{8, 30, 2, 2});
    BASIC_PARAMETERS_OF_ANIMALS.put(Goat.class, new double[]{60, 140, 3, 10});
    BASIC_PARAMETERS_OF_ANIMALS.put(Horse.class, new double[]{400, 20, 4, 60});
    BASIC_PARAMETERS_OF_ANIMALS.put(Mouse.class, new double[]{0.05, 500, 1, 0.01});
    BASIC_PARAMETERS_OF_ANIMALS.put(Rabbit.class, new double[]{2, 150, 2, 0.45});
    BASIC_PARAMETERS_OF_ANIMALS.put(Sheep.class, new double[]{70, 140, 3, 15});
    BASIC_PARAMETERS_OF_ANIMALS.put(Wolf.class, new double[]{50, 30, 3, 8});
    BASIC_PARAMETERS_OF_ANIMALS.put(Duck.class, new double[]{1, 200, 4, 0.15});
    BASIC_PARAMETERS_OF_ANIMALS.put(Eagle.class, new double[]{6, 20, 3, 1});
    BASIC_PARAMETERS_OF_ANIMALS.put(Caterpillar.class, new double[]{0.01, 1000, 0, 0.001});
  }

  /*
   * Набор классов, которые являются подклассами (или сами являются) класса Animal.
   * Переменная ANIMAL_CLASSES содержит классы всех видов животных,
   * которые представлены приложении.
   */
  public static final Set<Class<? extends Animal>> ANIMAL_CLASSES = Set.of(Bear.class, Boa.class,
      Boar.class, Buffalo.class, Deer.class,
      Fox.class, Goat.class, Horse.class, Mouse.class, Rabbit.class, Sheep.class, Wolf.class,
      Caterpillar.class,
      Duck.class, Eagle.class);

  /*
   * Переменная CHANCE_TO_EAT хранит вероятности поедания конкретного животного другим животным.
   */
  public static final Map<Class<? extends Animal>, Map<Class<? extends Animal>, Integer>>
      CHANCE_TO_EAT = new HashMap<>();

  /*
   * механизм статического блока инициализации, который позволяет лаконично инициализировать данные
   * для конкретного вида животного внутри статического блока,
   * который запускается при загрузке класса.
   */
  static {
    /*
     * Иницализация вероятности поедания ВОЛКОМ других животных
     */
    Map<Class<? extends Animal>, Integer> wolfChanceToEat = new HashMap<>();
    wolfChanceToEat.put(Horse.class, 10);
    wolfChanceToEat.put(Deer.class, 15);
    wolfChanceToEat.put(Rabbit.class, 60);
    wolfChanceToEat.put(Mouse.class, 80);
    wolfChanceToEat.put(Goat.class, 60);
    wolfChanceToEat.put(Sheep.class, 70);
    wolfChanceToEat.put(Boar.class, 15);
    wolfChanceToEat.put(Buffalo.class, 10);
    wolfChanceToEat.put(Duck.class, 40);
    CHANCE_TO_EAT.put(Wolf.class, wolfChanceToEat);

    /*
     * Иницализация вероятности поедания МЕДВЕДЕМ других животных
     */
    Map<Class<? extends Animal>, Integer> bearChanceToEat = new HashMap<>();
    bearChanceToEat.put(Horse.class, 5);
    bearChanceToEat.put(Deer.class, 20);
    bearChanceToEat.put(Rabbit.class, 40);
    bearChanceToEat.put(Mouse.class, 60);
    bearChanceToEat.put(Goat.class, 30);
    bearChanceToEat.put(Sheep.class, 25);
    bearChanceToEat.put(Boar.class, 25);
    bearChanceToEat.put(Buffalo.class, 10);
    bearChanceToEat.put(Duck.class, 30);
    CHANCE_TO_EAT.put(Bear.class, bearChanceToEat);

    /*
     * Иницализация вероятности поедания УДАВОМ других животных
     */
    Map<Class<? extends Animal>, Integer> boaChanceToEat = new HashMap<>();
    boaChanceToEat.put(Fox.class, 15);
    boaChanceToEat.put(Rabbit.class, 20);
    boaChanceToEat.put(Mouse.class, 40);
    boaChanceToEat.put(Duck.class, 10);
    CHANCE_TO_EAT.put(Boa.class, boaChanceToEat);

    /*
     * Иницализация вероятности поедания ЛИСОЙ других животных
     */
    Map<Class<? extends Animal>, Integer> foxChanceToEat = new HashMap<>();
    foxChanceToEat.put(Rabbit.class, 70);
    foxChanceToEat.put(Mouse.class, 90);
    foxChanceToEat.put(Duck.class, 60);
    foxChanceToEat.put(Caterpillar.class, 40);
    CHANCE_TO_EAT.put(Fox.class, foxChanceToEat);

    /*
     * Иницализация вероятности поедания ОРЛОМ других животных
     */
    Map<Class<? extends Animal>, Integer> eagleChanceToEat = new HashMap<>();
    eagleChanceToEat.put(Fox.class, 10);
    eagleChanceToEat.put(Rabbit.class, 90);
    eagleChanceToEat.put(Mouse.class, 90);
    eagleChanceToEat.put(Duck.class, 80);
    CHANCE_TO_EAT.put(Eagle.class, eagleChanceToEat);

    /*
     * Иницализация вероятности поедания МЫШЬЮ других животных
     */
    Map<Class<? extends Animal>, Integer> mouseChanceToEat = new HashMap<>();
    mouseChanceToEat.put(Caterpillar.class, 90);
    CHANCE_TO_EAT.put(Mouse.class, mouseChanceToEat);

    /*
     * Иницализация вероятности поедания КАБАНОМ других животных
     */
    Map<Class<? extends Animal>, Integer> boarChanceToEat = new HashMap<>();
    boarChanceToEat.put(Mouse.class, 50);
    boarChanceToEat.put(Caterpillar.class, 90);
    CHANCE_TO_EAT.put(Boar.class, boarChanceToEat);

    /*
     * Иницализация вероятности поедания УТКОЙ других животных
     */
    Map<Class<? extends Animal>, Integer> duckChanceToEat = new HashMap<>();
    duckChanceToEat.put(Caterpillar.class, 90);
    CHANCE_TO_EAT.put(Duck.class, duckChanceToEat);
  }
}
