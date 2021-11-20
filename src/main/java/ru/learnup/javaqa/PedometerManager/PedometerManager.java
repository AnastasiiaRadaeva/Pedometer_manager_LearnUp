package ru.learnup.javaqa.PedometerManager;
import java.util.ArrayList;
import java.util.List;

public class PedometerManager {
    private List<Integer> stat = new ArrayList<>();

    public int getSteps(int day) {
        if (day < 1 || day > stat.size()) {
            return -1;
        }
        return stat.get(day - 1);
    }

    public void addSteps(int steps) {
        if (steps >= 0) {
            stat.add(steps);
        }
    }

    public int getMaxDay() {
        int maxDay = 0;
        for (int i = 0; i < stat.size(); i++) {
            if (stat.get(i) > stat.get(maxDay)) {
                maxDay = i;
            }
        }
        return maxDay + 1;
    }

    public int add(int day, int steps) { //дни считаем с 1
        if ((day > stat.size() || day < 1) || steps < 0)
            return -1;
        int maxDay = stat.get(getMaxDay() - 1);
        stat.set(day - 1, stat.get(day - 1) + steps);
        return Math.max((maxDay - stat.get(day - 1)) + 1, 0); //доб 1, чтобы превысить пред максимум
    }
}
