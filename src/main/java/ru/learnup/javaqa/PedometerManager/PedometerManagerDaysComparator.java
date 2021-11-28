package ru.learnup.javaqa.PedometerManager;

import java.util.Comparator;
import java.util.List;

public class PedometerManagerDaysComparator implements Comparator<PedometerManager> {

    private int min = 0;

    public PedometerManagerDaysComparator(int min) {
        this.min = min;
    }

    @Override
    public int compare(PedometerManager o1, PedometerManager o2) {
        List<Integer> list_o1 = o1.getDaysList();
        List<Integer> list_o2 = o2.getDaysList();
        int days1 = 0;
        int days2 = 0;

        for (int i : list_o1) {
            if (i > min) {
                days1++;
            }
        }
        for (int i : list_o2) {
            if (i > min) {
                days2++;
            }
        }
        return days1 - days2;
    }
}
