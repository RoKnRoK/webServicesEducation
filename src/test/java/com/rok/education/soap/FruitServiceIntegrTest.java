package com.rok.education.soap;

import com.rok.education.FruitServiceCommon;
import com.rok.education.constants.Constants;
import com.rok.education.sei.FruitService;
import com.rok.education.sib.FruitServiceImpl;
import com.sun.xml.internal.ws.wsdl.parser.InaccessibleWSDLException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by roman.kulikov on 5/5/2017.
 * All rights reserved =D
 */
public class FruitServiceIntegrTest extends FruitServiceCommon {

    private static Endpoint endpoint;

    @BeforeClass
    public static void initService() throws MalformedURLException {
        endpoint = Endpoint.publish(Constants.SERVICE_URI, new FruitServiceImpl());
        URL serviceUrl = new URL(Constants.SERVICE_URI);
        QName serviceName = new QName("http://sib.education.rok.com/", "FruitServiceImplService");

        try {
            Service service = Service.create(serviceUrl, serviceName);

            fruitService = service.getPort(FruitService.class);
        } catch (InaccessibleWSDLException e) {
            System.out.println("Service not found");
            fruitService = null;
        }
        Assert.assertNotNull(fruitService);
    }

    @AfterClass
    public static void stopService() {
        endpoint.stop();
    }

}
