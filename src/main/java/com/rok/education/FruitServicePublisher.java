package com.rok.education;

import com.rok.education.constants.Constants;
import com.rok.education.sib.FruitServiceImpl;

import javax.xml.ws.Endpoint;

/**
 * Created by roman.kulikov on 5/5/2017.
 * All rights reserved =D
 */
public class FruitServicePublisher {

    public static void main(String[] args) {
        Endpoint.publish(Constants.SERVICE_URI, new FruitServiceImpl());
    }
}
