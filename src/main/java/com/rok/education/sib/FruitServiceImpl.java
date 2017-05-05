package com.rok.education.sib;

import com.rok.education.exc.NoSuchFruitException;
import com.rok.education.model.Fruit;
import com.rok.education.sei.FruitService;

import javax.jws.WebService;
import java.util.Random;

/**
 * Created by roman.kulikov on 5/5/2017.
 * All rights reserved =D
 */
@WebService(endpointInterface = "com.rok.education.sei.FruitService")
/*
  Without endpointInterface parameter all public methods of SIB will be exposed as web methods
 */
public class FruitServiceImpl implements FruitService {

    public String giveMeSomeFruit() {
        Random randomGenerator = new Random();
        int fruitsCount = Fruit.values().length;
        int randomFruitNumber = randomGenerator.nextInt(fruitsCount * 100) % fruitsCount;
        return Fruit.values()[randomFruitNumber].toString();
    }

    public String giveMeFruit(String fruitName) {
        try {
            return Fruit.fromString(fruitName).toString();
        } catch (NoSuchFruitException e) {
            return e.getMessage();
        }
    }

    public String notAWEbMethod(boolean forSure) {
        return "Definitely";
    }
}
