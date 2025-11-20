package com.todocodeacademy.pruebatecsupermercado.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String mensaje) {
        super(mensaje);
    }
}
