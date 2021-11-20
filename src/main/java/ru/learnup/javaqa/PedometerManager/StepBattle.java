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
        for (int i = 0; i < manager1.getDay(); i++) {
            stepsManager1 += manager1.getSteps(i + 1);
        }
        for (int i = 0; i < manager2.getDay(); i++) {
            stepsManager2 += manager2.getSteps(i + 1);
        }
        return stepsManager1 == stepsManager2 ? 0 : (stepsManager1 > stepsManager2 ? 1 : 2);
    }
}
