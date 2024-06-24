package com.example.filmes_api.domain.exception;

public class ResourceBadRequestException extends RuntimeException {
    public ResourceBadRequestException(String mensagem){
        super(mensagem);
    }
}