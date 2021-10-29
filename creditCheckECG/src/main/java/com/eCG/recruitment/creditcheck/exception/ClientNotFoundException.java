package com.eCG.recruitment.creditcheck.exception;

import java.util.function.Supplier;

public class ClientNotFoundException extends RuntimeException  {

    public ClientNotFoundException(String phoneNo,String dob) {

        super(String.format("Client with phone no  %s  and dob %s not found", phoneNo,dob));
    }

}
