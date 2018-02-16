package com.test.aimprosoft.util;

public class DataBaseException extends Exception{

    public DataBaseException(){
        super();
    }

    public DataBaseException(String message){
        super(message);
    }
    public DataBaseException(String message, Throwable cause){
        super(message, cause);
    }
}

