package ru.pavlov.neostudy.vacationPayCalculator.exceptions;

public class IncorrectDateException extends RuntimeException {
    public IncorrectDateException(String message) {
        super(message);
    }
}
