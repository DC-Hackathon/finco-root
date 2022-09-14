package com.bravura.finco.exception;

import org.springframework.http.HttpStatus;

public class TechnicalException extends RuntimeException{

    private final String message;
    private HttpStatus httpCode;

    public TechnicalException(String message){
        super(message);
        this.message = message;
    }

    public TechnicalException(String message, HttpStatus httpCode){
        super(message);
        this.message = message;
        this.httpCode = httpCode;
    }

    public TechnicalException(String message, HttpStatus httpCode, Throwable throwable){
        super(message, throwable);
        this.message = message;
        this.httpCode = httpCode;
    }

    public int getHttpStatus() {
        return this.httpCode.value();
    }

    @Override
    public String getMessage(){
        return this.message;
    }
}
