package com.skripsi.waste_bank.errors;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
@Getter
@Setter
public class ApiError {
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ApiError(HttpStatus status, String message, List<String> errors){
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(HttpStatus status){
        super();
        this.status = status;
    }

    public ApiError(HttpStatus status, String error){
        super();
        this.status = status;
        errors = Arrays.asList(error);
    }

    public ApiError(HttpStatus status, String message, String error){
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }
}
