package com.javarush.entity.creatures.animal;

import com.javarush.abstractions.Creatures;
import com.javarush.abstractions.Eatable;
import com.javarush.utils.Randomizer;

@Eatable(value = 1)
public abstract class Animal implements Creatures, Cloneable {

  public Animal() {
  }

  public void eat() {
  }

  public void move() {
  }

  public void chooseDirection() {
  }

  public void multiply() {
  }

  public void die() {
  }

  //Не забыть доделать позже!!!!!!!!!!
  @Override
  public Animal clone() {
    try {
      Animal clone = (Animal) super.clone();
//      clone.weight = Randomizer.getRandNumFromTo(Setting.BASIC_PARAMETERS_OF_ANIMALS.get(aClass)[0] / 1.5,
//          Setting.BASIC_PARAMETERS_OF_ANIMALS.get(this.aClass)[0]);
      return clone;
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }

}
