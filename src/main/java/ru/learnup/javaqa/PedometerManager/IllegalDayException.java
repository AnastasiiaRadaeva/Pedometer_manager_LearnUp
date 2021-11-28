package ru.learnup.javaqa.PedometerManager;

public class IllegalDayException extends IllegalArgumentException {
    public IllegalDayException(String input) {
        super(input);
    }
}
