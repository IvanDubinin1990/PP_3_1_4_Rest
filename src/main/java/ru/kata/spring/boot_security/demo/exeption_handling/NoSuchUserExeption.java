package ru.kata.spring.boot_security.demo.exeption_handling;

public class NoSuchUserExeption extends RuntimeException{

    public NoSuchUserExeption(String message) {
        super(message);
    }
}
