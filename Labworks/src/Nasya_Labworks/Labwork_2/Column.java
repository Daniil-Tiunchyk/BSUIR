package Nasya_Labworks.Labwork_2;

import static Nasya_Labworks.Labwork_2.ExecutionTracker.*;

public class Column implements Runnable {
    public Column(int id) {
        this.id = id;
    }

    private final int id;

    @Override
    public void run() {
        try {
            while (elapsedTime <= MAINTENANCE_TIME * 1000) {
                serviceCar();
                columnCarsServised[id - 1]++;
                System.out.println("Колонка №" + id + " начала обслуживание автомобиля");

                if ((elapsedTime > 0) && (elapsedTime / 1000 % 45 == 0)) {
                    System.out.println("Колонка №" + id + " закрыта на " + maintenanceTime(id) + " секунд техобслуживания");
                    Thread.sleep(maintenanceTime(id) * 1000L);
                }

                Thread.sleep(6 * 1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int maintenanceTime(int id) {
        return switch (id) {
            case 1 -> 10;
            case 2 -> 15;
            case 3 -> 5;
            case 4 -> 13;
            default -> 0;
        };
    }
}
