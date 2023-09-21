package Labwork_2;

import lombok.Getter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
class Ship implements Runnable {
    private final AtomicInteger totalContainers;
    private final int capacity;
    private final BufferedWriter writer;
    private final Object lock;
    private final int loadingTime;
    private final int deliveryTime;
    private int trips;

    public Ship(AtomicInteger totalContainers, int capacity, BufferedWriter writer, Object lock, int loadingTime, int deliveryTime) {
        this.totalContainers = totalContainers;
        this.capacity = capacity;
        this.writer = writer;
        this.lock = lock;
        this.loadingTime = loadingTime;
        this.deliveryTime = deliveryTime;
    }

    @Override
    public void run() {
        while (true) {
            if (totalContainers.get() <= 0) {
                printAndWrite("Корабль с вместимостью " + capacity + " завершил работу.");
                break;
            }
            int containersToLoad;
            synchronized (lock) {
                if (totalContainers.get() <= 0) {
                    break;
                }
                containersToLoad = Math.min(capacity, totalContainers.get());
                totalContainers.addAndGet(-containersToLoad);
                printAndWrite("В порту осталось " + totalContainers.get() + " контейнеров.");
            }
            try {
                Thread.sleep((long) loadingTime * containersToLoad);
                Thread.sleep(deliveryTime);
                trips++;
                printAndWrite("Корабль с вместимостью " + capacity + " завершил рейс номер " + trips);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printAndWrite(String message) {
        System.out.println(message);
        try {
            writer.write(message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
