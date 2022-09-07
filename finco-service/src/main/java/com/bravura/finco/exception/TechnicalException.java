package com.bravura.finco.exception;

import org.springframework.http.HttpStatus;

public class TechnicalException extends RuntimeException{

    public String cause;
    public HttpStatus code;

    public TechnicalException(String cause){
        super(cause);
    }

    public TechnicalException(String cause, HttpStatus httpCode){
        super(cause);
        this.cause = cause;
        this.code = httpCode;
    }


}
