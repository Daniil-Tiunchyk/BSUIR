package Labwork_2_v7;

public class ExecutionTracker extends Thread {
    static long elapsedTime;

    public static int carsServiced = 0;

    public static int[] columnCarsServised = {0, 0, 0, 0};

    static final int MAINTENANCE_TIME = 4 * 60; // 4 минуты как 4 часа

    public static synchronized void serviceCar() {
        carsServiced++;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (elapsedTime <= MAINTENANCE_TIME * 1000) {
            elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println("\tВремя выполнения программы: " + (elapsedTime / 1000) + " секунд");
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
