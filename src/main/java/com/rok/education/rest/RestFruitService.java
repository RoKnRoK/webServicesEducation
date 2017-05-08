package com.rok.education.rest;

import com.rok.education.exc.NoSuchFruitException;
import com.rok.education.model.Fruit;
import com.rok.education.sei.FruitService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.Random;

/**
 * Created by roman.kulikov on 5/8/2017.
 * All rights reserved =D
 */
@Path("/fruits")
public class RestFruitService  implements FruitService{
    @Override
    @GET
    @Path("/random")
    public String giveMeSomeFruit() {
        Random randomGenerator = new Random();
        int fruitsCount = Fruit.values().length;
        int randomFruitNumber = randomGenerator.nextInt(fruitsCount * 100) % fruitsCount;
        return Fruit.values()[randomFruitNumber].toString();
    }

    @Override
    @GET
    @Path("/fruit")
    @Produces("application/json")
    public String giveMeFruit(@QueryParam("name") String fruitName) {
        try {
            return Fruit.fromString(fruitName).toString();
        } catch (NoSuchFruitException e) {
            return e.getMessage();
        }
    }
}
