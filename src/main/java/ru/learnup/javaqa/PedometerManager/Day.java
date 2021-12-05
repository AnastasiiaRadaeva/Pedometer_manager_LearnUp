package ru.learnup.javaqa.PedometerManager;

public class Day {
    private Integer day;
    private Integer steps;

    public Day(Integer day, Integer steps) {
        this.day = day;
        this.steps = steps;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public Integer getDay() {
        return day;
    }

    public Integer getSteps() {
        return steps;
    }

    @Override
    public String toString() {
        return day + " day - " +
                steps + " steps";
    }
}
