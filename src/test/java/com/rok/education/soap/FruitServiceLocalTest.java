package com.rok.education.soap;

import com.rok.education.FruitServiceCommon;
import com.rok.education.model.Fruit;
import com.rok.education.sei.FruitService;
import com.rok.education.sib.FruitServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.core.Is.is;

/**
 * Created by roman.kulikov on 5/5/2017.
 * All rights reserved =D
 */

public class FruitServiceLocalTest extends FruitServiceCommon {


    @Before
    public void initService() {
        fruitService = new FruitServiceImpl();
    }


}
