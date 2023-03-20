package com.skripsi.waste_bank.errors;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class NullPointerException extends RuntimeException{
    private HttpStatus status;
    private String message;
    private List<String> errors;
    public NullPointerException(HttpStatus status, String error){
        super();
        this.status = status;
        errors = Arrays.asList(error);
    }
}
