package Labwork_1_v13;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double x1, y1, x2, y2, x3, y3;

        try {
            System.out.println("Введите координаты первой точки (x1, y1):");
            x1 = scanner.nextDouble();
            y1 = scanner.nextDouble();

            System.out.println("Введите координаты второй точки (x2, y2):");
            x2 = scanner.nextDouble();
            y2 = scanner.nextDouble();

            System.out.println("Введите координаты третьей точки (x3, y3):");
            x3 = scanner.nextDouble();
            y3 = scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("Неверный формат ввода. Пожалуйста, введите числа.");
            return;
        }

        double a = distance(x1, y1, x2, y2);
        double b = distance(x2, y2, x3, y3);
        double c = distance(x3, y3, x1, y1);

        try {
            Triangle triangle = new Triangle(a, b, c);
            triangle.identifyType();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
