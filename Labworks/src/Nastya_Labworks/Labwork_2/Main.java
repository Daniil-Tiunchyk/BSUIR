package Nastya_Labworks.Labwork_2;

import lombok.AllArgsConstructor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        int simulationTimeInSeconds = 4 * 60 * 60;

        Thread[] carThreads = new Thread[4];
        AtomicInteger[] servicedCars = new AtomicInteger[4];
        for (int i = 0; i < 4; i++) {
            servicedCars[i] = new AtomicInteger();
            carThreads[i] = new Thread(new Car(servicedCars[i], i + 1));
            carThreads[i].start();
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        for (int currentTime = 0; currentTime < simulationTimeInSeconds; currentTime++) {
            for (int i = 0; i < 4; i++) {
                carThreads[i].join();
                carThreads[i] = new Thread(new Car(servicedCars[i], i + 1));
                carThreads[i].start();
            }

            if ((currentTime + 1) % (45 * 60) == 0) {
                writer.write("После " + (currentTime / 60) + " минут работы:\n");
                for (int i = 0; i < 4; i++) {
                    writer.write("Колонка " + (i + 1) + " обслужила " + servicedCars[i] + " автомобилей.\n");
                }
                writer.flush();
            }

            TimeUnit.SECONDS.sleep(1);
        }

        for (int i = 0; i < 4; i++) {
            carThreads[i].join();
        }
    }

    @AllArgsConstructor
    static class Car implements Runnable {
        private AtomicInteger servicedCars;
        private int columnNumber;


        @Override
        public void run() {
            try {
                Thread.sleep(6 * 1000);
                servicedCars.incrementAndGet();
                String message = "Колонка " + columnNumber + " обслужила автомобиль.";
                System.out.println(message);
                Thread.sleep(getMaintenanceTime(columnNumber) * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private int getMaintenanceTime(int columnNumber) {
            return switch (columnNumber) {
                case 1 -> 10;
                case 2 -> 15;
                case 3 -> 5;
                case 4 -> 13;
                default -> 0;
            };
        }
    }
}
