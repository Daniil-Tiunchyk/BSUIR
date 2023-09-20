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

        int loadingTime = 200;
        int deliveryTime = 4000;

        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        Thread[] ships = new Thread[4];
        int[] trips = new int[4];

        long startTime = System.currentTimeMillis();

        Object lock = new Object();

        for (int i = 0; i < 4; i++) {
            final int index = i;
            ships[i] = new Thread(() -> {
                while (true) {
                    if (totalContainers.get() <= 0) {
                        String output = "Корабль с вместимостью " + capacities[index] + " завершил работу.";
                        System.out.println(output);
                        try {
                            writer.write(output + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    int containersToLoad;
                    synchronized (lock) {
                        if (totalContainers.get() <= 0) {
                            break;
                        }
                        containersToLoad = Math.min(capacities[index], totalContainers.get());
                        totalContainers.addAndGet(-containersToLoad);
                        String output = "Осталось " + totalContainers.get() + " контейнеров.";
                        System.out.println(output);
                        try {
                            writer.write(output + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep((long) loadingTime * containersToLoad);

                        Thread.sleep(deliveryTime);

                        trips[index]++;
                        String output = "Корабль с вместимостью " + capacities[index] + " завершил рейс номер " + trips[index];
                        System.out.println(output);
                        writer.write(output + "\n");
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            ships[i].start();
        }

        for (Thread ship : ships) {
            try {
                ship.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long totalTime = System.currentTimeMillis() - startTime;
        String output = "Все контейнеры вывезены за " + totalTime + " миллисекунд.";
        System.out.println(output);
        writer.write(output + "\n");

        for (int i = 0; i < 4; i++) {
            output = "Корабль с вместимостью " + capacities[i] + " сделал " + trips[i] + " рейсов.";
            System.out.println(output);
            writer.write(output + "\n");
        }

        writer.close();
    }
}
