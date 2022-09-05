package com.finCo.hub.exception;

import org.springframework.http.HttpStatus;

public class TechnicalException extends RuntimeException{

    public String cause;
    public HttpStatus code;

    public TechnicalException(){
        super();
    }

    public TechnicalException(String cause, HttpStatus httpCode){
        super();
        this.cause = cause;
        this.code = httpCode;
    }


}
