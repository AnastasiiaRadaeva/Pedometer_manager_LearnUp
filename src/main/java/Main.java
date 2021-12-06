import ru.learnup.javaqa.PedometerManager.PedometerManager;

public class Main {

    public static void main(String[] args) {
        PedometerManager manager = new PedometerManager();
//        manager.addSteps(1000);
//        manager.addSteps(15000);
//        manager.addSteps(9000);
//        manager.addSteps(8000);
//        manager.addSteps(23000);

        System.out.println(manager);

        System.out.print("Шагов в 5 день: ");
        System.out.println(manager.getSteps(5));
        System.out.print("Максимальный по шагам день: ");
        System.out.println(manager.getMaxDay());
        System.out.println("К 1 дню добавляем 200: ");
        int upMax = manager.add(1, 200);
        System.out.println(manager);
        System.out.println("До максимального значения осталось пройти: " + upMax);
    }
}
