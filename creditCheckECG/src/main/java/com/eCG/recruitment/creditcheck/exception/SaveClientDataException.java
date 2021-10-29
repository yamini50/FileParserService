package com.eCG.recruitment.creditcheck.exception;

public class SaveClientDataException extends RuntimeException {

    public SaveClientDataException() {

        super("Not able to save client's data in database. Please check !!");
    }

}
