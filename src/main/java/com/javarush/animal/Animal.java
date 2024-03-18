package com.javarush.animal;

import com.javarush.abstractions.Creatures;
import com.javarush.abstractions.Eatable;

@Eatable(value = 1)
public abstract class Animal implements Creatures {
    public void eat() {}

    public void move() {}

    public void chooseDirection(){}

    public void multiply() {}

    public void die() {}
}
