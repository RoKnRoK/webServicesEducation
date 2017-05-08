package com.rok.education.sib;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Set;

/**
 * Created by roman.kulikov on 5/8/2017.
 * All rights reserved =D
 */
public class MyServiceLogHandler implements SOAPHandler<SOAPMessageContext> {

    @Override
    public Set<QName> getHeaders() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void close(MessageContext arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean handleFault(SOAPMessageContext arg0) {
        SOAPMessage message = arg0.getMessage();
        try {
            message.writeTo(System.out);
        } catch (SOAPException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext arg0) {
        SOAPMessage message = arg0.getMessage();
        Boolean isOutboundMessage = (Boolean) arg0.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (isOutboundMessage) {
            System.out.print("OUTBOUND MESSAGE\n");
        } else {
            System.out.print("INBOUND MESSAGE\n");
        }

        try {
            message.writeTo(System.out);
            System.out.println();
            System.out.println();
        } catch (SOAPException | IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
