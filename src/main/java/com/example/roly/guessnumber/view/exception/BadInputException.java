package com.example.roly.guessnumber.view.exception;

/**
 * Created by Roly on 14/12/2021.
 */

public class BadInputException extends Exception {

    public BadInputException(String fieldName){
        super("Error on the field " + fieldName + ".");
    }
}
