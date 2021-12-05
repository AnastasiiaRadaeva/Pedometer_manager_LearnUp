package ru.learnup.javaqa.PedometerManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PedometerManagerTest {
    PedometerManager manager = new PedometerManager();
    //для пустой таблицы
//        manager.addSteps(1000);
//        manager.addSteps(15000);
//        manager.addSteps(9000);
//        manager.addSteps(8000);
//        manager.addSteps(23000);

    @Test
    public void checkGetListDay() {
        List<Integer> expected = new ArrayList<>();
        expected.add(1000);
        expected.add(15000);
        expected.add(9000);
        expected.add(8000);
        expected.add(23000);
        Assertions.assertEquals(expected, manager.getDaysList());
    }

    @Test
    public void checkGetSteps() {
        Assertions.assertEquals(9000, manager.getSteps(3));
    }

    @Test
    public void checkgetMaxday() {
        Assertions.assertEquals(5, manager.getMaxDay());
    }

    @Test
    public void checkAddList() {
        List<Integer> expected = new ArrayList<>();
        expected.add(1200);
        expected.add(15000);
        expected.add(9000);
        expected.add(8000);
        expected.add(23000);
        manager.add(1, 200);
        Assertions.assertEquals(expected, manager.getDaysList());
    }

    @Test
    public void checkAddReturn() {
        int expected = 21601;
        Assertions.assertEquals(expected, manager.add(1, 200));
    }
}
