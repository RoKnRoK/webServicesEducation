package com.rok.education;

import com.rok.education.model.Fruit;
import com.rok.education.sei.FruitService;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.core.Is.is;

/**
 * Created by roman.kulikov on 5/5/2017.
 * All rights reserved =D
 */
public abstract class FruitServiceCommon implements FruitServiceTest{

    static FruitService fruitService;

    @Test
    public void giveMeSomeFruit() {
        String fruit = fruitService.giveMeSomeFruit();
        System.out.println(fruit);
        Assert.assertThat(fruit, anyOf(
                is(Fruit.APPLE.toString()),
                is(Fruit.BANANA.toString()),
                is(Fruit.GRAPEFRUIT.toString()),
                is(Fruit.ORANGE.toString()),
                is(Fruit.GRAPES.toString())));
    }

    @Test
    public void giveMeApple() {
        String fruit = fruitService.giveMeFruit("apple");
        System.out.println(fruit);
        Assert.assertEquals("APPLE", fruit);
    }

    @Test
    public void giveMePassionFruit() {
        String fruit = fruitService.giveMeFruit("passionFruit");
        System.out.println(fruit);
        Assert.assertEquals("No such fruit: passionFruit", fruit);
    }
}
