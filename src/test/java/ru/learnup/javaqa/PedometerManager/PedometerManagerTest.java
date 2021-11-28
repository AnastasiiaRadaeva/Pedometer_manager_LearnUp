package ru.learnup.javaqa.PedometerManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class PedometerManagerTest {
    PedometerManager manager = new PedometerManager();
    PedometerManager manager_2 = new PedometerManager();
    PedometerManager manager_3 = new PedometerManager();
    PedometerManager mock_manager_1 = mock(PedometerManager.class);
    PedometerManager mock_manager_2 = mock(PedometerManager.class);
    StepBattle stepBattle = new StepBattle(mock_manager_1, mock_manager_2);

    @BeforeEach
    public void startSettings() {
        manager.addSteps(10000);
        manager.addSteps(12000);
        manager.addSteps(7000);
        manager.addSteps(500);
        manager.addSteps(20000);
    }

    /*                               |
    | --- PedometerManager class --- |
    |                               */
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

    @Test
    public void checkGetDaysList() {
        List<Integer> actual = manager.getDaysList();
        List<Integer> expected = new ArrayList<Integer>();
        expected.add(10000);
        expected.add(12000);
        expected.add(7000);
        expected.add(500);
        expected.add(20000);
        Assertions.assertEquals(expected, actual, "CheckGetDaysList");
    }

    @Test//49500 сумма у manager
    public void checkCompareToVar1() {
        manager_2.addSteps(1000);
        manager_2.addSteps(10000);
        manager_2.addSteps(5000);

        manager_3.addSteps(20000);
        manager_3.addSteps(10000);
        manager_3.addSteps(5000);

        List<PedometerManager> actual = new ArrayList<>();
        actual.add(manager);
        actual.add(manager_2);
        actual.add(manager_3);

        List<PedometerManager> expected = new ArrayList<>();
        expected.add(manager_2);
        expected.add(manager_3);
        expected.add(manager);

        Collections.sort(actual);
        Assertions.assertEquals(expected, actual, "compareTo, var1");
    }

    @Test//49500 сумма у manager
    public void checkCompareToVar2() {
        manager_2.addSteps(40000);
        manager_2.addSteps(10000);
        manager_2.addSteps(5000);

        manager_3.addSteps(20000);
        manager_3.addSteps(10000);
        manager_3.addSteps(5000);

        List<PedometerManager> actual = new ArrayList<>();
        actual.add(manager);
        actual.add(manager_2);
        actual.add(manager_3);

        List<PedometerManager> expected = new ArrayList<>();
        expected.add(manager_3);
        expected.add(manager);
        expected.add(manager_2);

        Collections.sort(actual);
        Assertions.assertEquals(expected, actual, "compareTo, var1");
    }

    /*                         |
    | --- StepBattle class --- |
    |                         */

    //addSteps
    @ParameterizedTest
    @CsvSource({"1, 3, 5000", "2, 3, 7000"})
    public void checkAddStepsSB(int player, int day, int steps) {
        stepBattle.addSteps(player, day, steps);
        if (player == 1) {
            verify(mock_manager_1).add(day, steps);
        }
        if (player == 2) {
            verify(mock_manager_2).add(day, steps);
        }
    }

    //winner
    @ParameterizedTest
    @CsvSource({"3000, 5000, 2", "4000, 700, 1", "4000, 4000, 0"})
    public void checkWinnerSB(int ind_1, int ind_2, int expected) {
        doReturn(
                List.of(ind_1, ind_1 + 100, ind_1 + 500)
        ).when(mock_manager_1).getDaysList();
        doReturn(
                List.of(ind_2, ind_2 + 100, ind_2 + 500)
        ).when(mock_manager_2).getDaysList();
        int actual = stepBattle.winner();
        verify(mock_manager_1).getDaysList();
        verify(mock_manager_2).getDaysList();
        Assertions.assertEquals(expected, actual);
    }

    /*                                             |
    | --- PedometerManagerDaysComparator class --- |
    |                                             */
    @ParameterizedTest
    @CsvSource({"5000, 10000, 7000, -1", "10000, 5000, 9000, 1", "5000, 5000, 10000, 0"})
    public void checkComparator(int ind_2, int ind_3, int min, int expected) {
        PedometerManagerDaysComparator comp = new PedometerManagerDaysComparator(min);

        manager_2.addSteps(ind_2);
        manager_2.addSteps(ind_2 + 5000);
        manager_2.addSteps(ind_2 + 10000);

        manager_3.addSteps(ind_3);
        manager_3.addSteps(ind_3 + 5000);
        manager_3.addSteps(ind_3 + 10000);

        int actual = comp.compare(manager_2, manager_3);
        Assertions.assertEquals(expected, actual, "PedometerManagerDaysComparator class");
    }
}
