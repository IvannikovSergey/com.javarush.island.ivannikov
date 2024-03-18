package com.javarush.abstractions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Интерфейс маркер
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Eatable {

    /**
     * Поля аннотации
     * 0 - может быть съеден
     * 1 - может съесть
     */
    int value() default 0;
}
