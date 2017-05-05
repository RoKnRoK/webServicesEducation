package com.rok.education;

import com.rok.education.constants.Constants;
import com.rok.education.sei.FruitService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by roman.kulikov on 5/5/2017.
 * All rights reserved =D
 */
public class FruitServiceClient {
    public static void main(String[] args) throws MalformedURLException {
        URL serviceUrl = new URL(Constants.SERVICE_URI);
        QName serviceName = new QName("http://sib.education.rok.com/", "FruitServiceImplService");

        Service service = Service.create(serviceUrl, serviceName);

        FruitService fruitService = service.getPort(FruitService.class);

        System.out.println(fruitService.giveMeSomeFruit());
        System.out.println(fruitService.giveMeFruit("banana"));
    }
}
