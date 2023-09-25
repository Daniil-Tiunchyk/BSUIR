package Labwork_2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество контейнеров для погрузки:");
        AtomicInteger totalContainers = new AtomicInteger(scanner.nextInt());

        System.out.println("Введите вместимость кораблей через пробел (4 числа):");
        int[] capacities = new int[4];
        for (int i = 0; i < 4; i++) {
            capacities[i] = scanner.nextInt();
        }

        int loadingTime = 20;
        int deliveryTime = 400;

        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        Thread[] shipsThreads = new Thread[4];
        Ship[] ships = new Ship[4];

        long startTime = System.currentTimeMillis();

        Object lock = new Object();

        for (int i = 0; i < 4; i++) {
            ships[i] = new Ship(totalContainers, capacities[i], writer, lock, loadingTime, deliveryTime);
            shipsThreads[i] = new Thread(ships[i]);
            shipsThreads[i].start();
        }

        for (Thread shipThread : shipsThreads) {
            try {
                shipThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long totalTime = System.currentTimeMillis() - startTime;
        String output = "Все контейнеры вывезены за " + totalTime + " миллисекунд.";
        System.out.println(output);
        writer.write(output + "\n");

        for (Ship ship : ships) {
            output = "Корабль с вместимостью " + ship.getCapacity() + " сделал " + ship.getTrips() + " рейсов.";
            System.out.println(output);
            writer.write(output + "\n");
        }

        writer.close();
    }
}
