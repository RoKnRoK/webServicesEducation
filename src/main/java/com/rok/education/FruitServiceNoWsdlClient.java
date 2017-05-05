package com.rok.education;

import com.rok.education.constants.Constants;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by roman.kulikov on 5/5/2017.
 * All rights reserved =D
 */
public class FruitServiceNoWsdlClient {
    public static void main(String[] arg) throws Exception {
        //todo: to be implemented may be
    }

    public String getWeather(String city) throws MalformedURLException,
            IOException, MalformedURLException {

//Code to make a webservice HTTP request
        String responseString = "";
        URL url = new URL(Constants.SERVICE_URI);
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        String xmlInput =
                " <soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://litwinconsulting.com/webservices/\">\n" +
                        " <soapenv:Header/>\n" +
                        " <soapenv:Body>\n" +
                        " <web:GetWeather>\n" +
                        " <!--Optional:-->\n" +
                        " <web:City>" + city + "</web:City>\n" +
                        " </web:GetWeather>\n" +
                        " </soapenv:Body>\n" +
                        " </soapenv:Envelope>";

        bout.write(xmlInput.getBytes());
        byte[] b = bout.toByteArray();
        String SOAPAction = "webMethod";
// Set the appropriate HTTP parameters.
        httpConnection.setRequestProperty("Content-Length", String.valueOf(b.length));
        httpConnection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        httpConnection.setRequestProperty("SOAPAction", SOAPAction);
        httpConnection.setRequestMethod("POST");
        httpConnection.setDoOutput(true);
        httpConnection.setDoInput(true);
        OutputStream out = httpConnection.getOutputStream();
//Write the content of the request to the outputstream of the HTTP Connection.
        out.write(b);
        out.close();
//Ready with sending the request.

//Read the response.
        InputStreamReader isr = new InputStreamReader(httpConnection.getInputStream());
        BufferedReader in = new BufferedReader(isr);

        StringBuilder outputString = new StringBuilder();
//Write the SOAP message response to a String.
        while ((responseString = in.readLine()) != null) {
            outputString.append(responseString);
        }
//Parse the String output to a org.w3c.dom.Document and be able to reach every node with the org.w3c.dom API.
        Document document = parseXmlFile(outputString.toString());
        NodeList nodeLst = document.getElementsByTagName("GetWeatherResult");
        String weatherResult = nodeLst.item(0).getTextContent();
        System.out.println("Weather: " + weatherResult);

//Write the SOAP message formatted to the console.
        String formattedSOAPResponse = formatXML(outputString.toString());
        System.out.println(formattedSOAPResponse);
        return weatherResult;
    }

    //format the XML in your String
    private String formatXML(String unformattedXml) {
        try {
            Document document = parseXmlFile(unformattedXml);
            OutputFormat format = new OutputFormat(document);
            format.setIndenting(true);
            format.setIndent(3);
            format.setOmitXMLDeclaration(true);
            Writer out = new StringWriter();
            XMLSerializer serializer = new XMLSerializer(out, format);
            serializer.serialize(document);
            return out.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Document parseXmlFile(String in) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(in));
            return db.parse(is);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
