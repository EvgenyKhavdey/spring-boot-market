package ru.gb.springbootdemoapp.controller.exceptions;

public class NoPasswordException extends RuntimeException{
    public NoPasswordException(String message) {
        super(message);
    }
}
