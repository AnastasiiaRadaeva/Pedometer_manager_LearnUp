package ru.learnup.javaqa.PedometerManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PedometerManager implements Comparable<PedometerManager> {

    private static final String url = "jdbc:postgresql://localhost:5432/learnup";
    private static final String user = "postgres";
    private static final String password = "postgres";

    private static final DBHelper helper = new DBHelper(url, user, password);

    public int getSteps(int day) {
        if (day < 1 || day > 365) {
            throw new IllegalDayException(
                    "Праметр ДЕНЬ может принимать значения от 1 до 365. Ваше значение - " + day);
        }
        return helper.getDay(new Day(day, 0));
    }

    public List<Integer> getDaysList() {
        List<Day> days = helper.getAllPos();
        List<Integer> stat = new ArrayList<>();
        for (Day note : days) {
            stat.add(note.getSteps());
        }
        return stat;
    }

    public void addSteps(int steps) {
        if (steps < 0) {
            throw new IllegalStepsException(
                    "Только положительное число может обозначать количество шагов. Ваше значение - " + steps);
        }
        helper.addDay(new Day(0, steps));
    }

    public int getMaxDay() {
        return helper.getMaxDay(new Day(0, 0));
    }

    public int add(int day, int steps) { //дни считаем с 1
        if (day < 1 || day > 365) {
            throw new IllegalDayException(
                    "Праметр ДЕНЬ может принимать значения от 1 до 365. Ваше значение - " + day);
        }
        if (steps < 0) {
            throw new IllegalStepsException(
                    "Только положительное число может обозначать количество шагов. Ваше значение - " + steps);
        }
        if (day > getDaysList().size())
            return -1;

        helper.updateDay(new Day(day, steps));
        return Math.max(getSteps(getMaxDay()) - getSteps(day) + 1, 0); //доб 1, чтобы превысить пред максимум
    }

    @Override
    public int compareTo(PedometerManager o) {
        int stepsManager1 = 0;
        int stepsManager2 = 0;
        for (int i : getDaysList()) {
            stepsManager1 += i;
        }
        for (int i : o.getDaysList()) {
            stepsManager2 += i;
        }
        return stepsManager1 - stepsManager2;
    }

    public Stream<Integer> getAllAbove(int steps) {
        if (steps < 0) {
            throw new IllegalStepsException(
                    "Только положительное число может обозначать количество шагов. Ваше значение - " + steps);
        }
        return getDaysList().stream().
                filter(i -> i > steps);
    }

    @Override
    public String toString() {
        String final_str = "";
        for (Day note : helper.getAllPos()) {
            final_str += note.toString() + "\n";
        }
        return final_str;
    }
}
