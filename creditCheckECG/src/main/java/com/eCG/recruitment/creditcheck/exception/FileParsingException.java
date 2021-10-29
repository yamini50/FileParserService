package com.eCG.recruitment.creditcheck.exception;

public class FileParsingException extends RuntimeException{


    public FileParsingException() {

        super("something went wrong in parsing file. Please check if file format is correct !!");
    }

}
