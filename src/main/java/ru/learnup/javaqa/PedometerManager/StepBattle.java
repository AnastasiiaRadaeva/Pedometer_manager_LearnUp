package ru.learnup.javaqa.PedometerManager;

public class StepBattle {
    private PedometerManager manager1;
    private PedometerManager manager2;

    public StepBattle(PedometerManager manager1, PedometerManager manager2) {
        this.manager1 = manager1;
        this.manager2 = manager2;
    }

    public void addSteps(int player, int day, int steps) {
        if (player != 1 && player != 2) {
            return;
        }
        PedometerManager manager = player == 1 ? manager1 : manager2;
        manager.add(day, steps);
    }

    public int winner() {
        int stepsManager1 = 0;
        int stepsManager2 = 0;
        for (int i : manager1.getDaysList()){
            stepsManager1 += i;
        }
        for (int i : manager2.getDaysList()){
            stepsManager2 += i;
        }
        return stepsManager1 == stepsManager2 ? 0 : (stepsManager1 > stepsManager2 ? 1 : 2);
    }
}
