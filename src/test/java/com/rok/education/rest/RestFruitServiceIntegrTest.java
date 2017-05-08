package com.rok.education.rest;

import com.rok.education.FruitServiceCommon;
import com.rok.education.model.Fruit;
import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.core.Is.is;

/**
 * Created by roman.kulikov on 5/8/2017.
 * All rights reserved =D
 */
public class RestFruitServiceIntegrTest extends FruitServiceCommon {


    private static SelectorThread threadSelector;
    public static final String BASE_URI = "http://localhost:7080/";

    @BeforeClass
    public static void initService() {
        final Map<String, String> initParams = new HashMap<String, String>();

        // Register the package that contains your javax.ws.rs-annotated beans here
        initParams.put("com.sun.jersey.config.property.packages", "com.rok.education.rest");

        System.out.println("Starting grizzly...");
        try {
            threadSelector = GrizzlyWebContainerFactory.create(BASE_URI, initParams);
            System.out.println(String.format("Jersey started with WADL available at %sapplication.wadl.", BASE_URI));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void stopService() {
        threadSelector.stopEndpoint();
    }

    @Override
    public void giveMeSomeFruit() {
        try {
            URL url = new URL(BASE_URI + "fruits/random");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String output;
            StringBuilder result = new StringBuilder();
            while ((output = br.readLine()) != null) {
                result.append(output);
            }
            System.out.println(result.toString());
            conn.disconnect();

            Assert.assertThat(result.toString(), anyOf(
                    is(Fruit.APPLE.toString()),
                    is(Fruit.BANANA.toString()),
                    is(Fruit.GRAPEFRUIT.toString()),
                    is(Fruit.ORANGE.toString()),
                    is(Fruit.GRAPES.toString())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void giveMeApple() {
        try {
            URL url = new URL(BASE_URI + "fruits/fruit?name=appLe");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String output;
            StringBuilder result = new StringBuilder();
            while ((output = br.readLine()) != null) {
                result.append(output);
            }
            System.out.println(result.toString());
            conn.disconnect();

            Assert.assertEquals("APPLE", result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void giveMePassionFruit() {
        try {
            URL url = new URL(BASE_URI + "fruits/fruit?name=passionFruit");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String output;
            StringBuilder result = new StringBuilder();
            while ((output = br.readLine()) != null) {
                result.append(output);
            }
            System.out.println(result.toString());
            conn.disconnect();

            Assert.assertEquals("No such fruit: passionFruit", result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
