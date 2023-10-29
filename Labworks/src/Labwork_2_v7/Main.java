package Labwork_2_v7;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Thread[] columns = new Thread[4];

        ExecutionTracker timeTracker = new ExecutionTracker();
        timeTracker.start();

        for (int i = 0; i < 4; i++) {
            Column column = new Column(i + 1);
            columns[i] = new Thread(column);
            columns[i].start();
        }

        for (Thread column : columns) {
            column.join();
        }

        timeTracker.join();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            for (int i = 0; i < 4; i++) {
                System.out.println("Колонка №" + (i + 1) + " обслужила " + ExecutionTracker.columnCarsServised[i] + " автомобилей");
                writer.write("Колонка №" + (i + 1) + " обслужила " + ExecutionTracker.columnCarsServised[i] + " автомобилей");
                writer.newLine();
            }
            System.out.println("Общее количество обслуженных автомобилей: " + ExecutionTracker.carsServiced);
            writer.write("Общее количество обслуженных автомобилей: " + ExecutionTracker.carsServiced);
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
