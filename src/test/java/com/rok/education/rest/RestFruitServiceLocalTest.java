package com.rok.education.rest;

import com.rok.education.FruitServiceCommon;
import com.rok.education.rest.RestFruitService;
import org.junit.Before;

/**
 * Created by roman.kulikov on 5/8/2017.
 * All rights reserved =D
 */
public class RestFruitServiceLocalTest extends FruitServiceCommon {

    @Before
    public void initService() {
        fruitService = new RestFruitService();
    }

}
