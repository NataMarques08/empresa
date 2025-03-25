package com.nata.empresa.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileStorageExceptions {
    
    public FileStorageExceptions(String message) {
        super(message);
    }

    public FileStorageExceptions(String message, Throwable cause) {
        super(message, cause);
    }

}   
