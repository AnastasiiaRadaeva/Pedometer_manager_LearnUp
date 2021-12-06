package ru.learnup.javaqa.PedometerManager.entities;

import javax.persistence.*;

@Entity
@Table(name = "pedometer_manager")
public class Day {
    @Id
    private Integer day;
    @Column
    private Integer steps;

    public Day() {
    }

    public Day(Integer steps){
        this.steps = steps;
    }

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
