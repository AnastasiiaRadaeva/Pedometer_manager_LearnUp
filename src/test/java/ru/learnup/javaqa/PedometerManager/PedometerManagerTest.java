package ru.learnup.javaqa.PedometerManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PedometerManagerTest {
    PedometerManager manager = new PedometerManager();

    @BeforeEach
    public void startSettings() {
        manager.addSteps(10000);
        manager.addSteps(12000);
        manager.addSteps(7000);
        manager.addSteps(500);
        manager.addSteps(20000);
    }

    @ParameterizedTest
    @CsvSource({"0, -1", "1, 10000", "2, 12000", "5, 20000", "6, -1"})
    public void checkGetSteps(int day, int expected) {
        int actual = manager.getSteps(day);
        Assertions.assertEquals(expected, actual, "CheckGetSteps");
    }

    @ParameterizedTest
    @CsvSource({"-1, 6, -1", "0, 6, 0", "200, 6, 200"})
    public void checkAddSteps(int steps, int day, int expected) {
        manager.addSteps(steps);
        int actual = manager.getSteps(day);
        Assertions.assertEquals(expected, actual, "CheckAddSteps");
    }

    @ParameterizedTest
    @CsvSource({"0, 0, 5", "1, 21000, 6"})
    public void checkMaxDay(int flag, int steps, int expected) {
        if (flag == 1) {
            manager.addSteps(steps);
        }
        int actual = manager.getMaxDay();
        Assertions.assertEquals(expected, actual, "CheckMaxDay");
    }

    @ParameterizedTest
    @CsvSource({"0, 2, -1", "6, 8, -1", "1, -1, -1", "1, 3000, 7001", "5, 300, 0", "2, 10000, 0", "1, 10000, 1"})
    public void checkAdd(int day, int steps, int expected) {
        int actual = manager.add(day, steps);
        Assertions.assertEquals(expected, actual, "CheckAdd");
    }

}
