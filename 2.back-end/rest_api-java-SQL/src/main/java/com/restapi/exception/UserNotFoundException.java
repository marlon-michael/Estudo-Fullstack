package com.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserNotFoundException extends RuntimeException{
    public String msg;

    public UserNotFoundException(String msg){
        this.msg = msg;
    }

    public ResponseEntity<Object> toResponse (){
        // throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro/Placa não encontrado");
        return new ResponseEntity<>("Usuario não encontrado"+msg, HttpStatus.NOT_FOUND);
    }
}