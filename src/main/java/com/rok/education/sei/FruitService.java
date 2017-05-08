package com.rok.education.sei;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by roman.kulikov on 5/5/2017.
 * All rights reserved =D
 */
@WebService
@SOAPBinding(style=SOAPBinding.Style.DOCUMENT)
public interface FruitService {
    @WebMethod
    @WebResult(partName = "some_fruit")
    public String giveMeSomeFruit();

    @WebMethod
    @WebResult(partName = "the_fruit")
    public String giveMeFruit(@WebParam String fruitName);
}
