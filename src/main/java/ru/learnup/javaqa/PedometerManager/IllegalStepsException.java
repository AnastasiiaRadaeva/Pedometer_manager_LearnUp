package ru.learnup.javaqa.PedometerManager;

public class IllegalStepsException extends IllegalArgumentException {
    public IllegalStepsException(String input) {
        super(input);
    }
}
