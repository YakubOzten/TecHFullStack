package org.yakub.exception;

public class NotFound404Exception extends  RuntimeException{
    NotFound404Exception(String message){
        super(message);
    }
}
