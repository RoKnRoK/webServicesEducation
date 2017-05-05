package com.rok.education.sei;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by roman.kulikov on 5/5/2017.
 * All rights reserved =D
 */
@WebService
@SOAPBinding
public interface FruitService {
    @WebMethod
    public String giveMeSomeFruit();
    @WebMethod
    public String giveMeFruit(String fruitName);
}
