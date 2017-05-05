package com.rok.education.model;


import com.rok.education.exc.NoSuchFruitException;

import java.util.Objects;

public enum Fruit {
    APPLE, ORANGE, BANANA, GRAPES, GRAPEFRUIT;

    /**
     * Created by roman.kulikov on 5/5/2017.
     * All rights reserved =D
     */
    public static Fruit fromString(String fruitName) throws NoSuchFruitException {
        for (Fruit fruit : values()) {
            if (Objects.equals(fruit.toString().toLowerCase(), fruitName.toLowerCase())){
                return fruit;
            }
        }
        throw new NoSuchFruitException("No such fruit: " + fruitName);
    }
}
