import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfBooks;

        while (true) {
            try {
                System.out.println("Введите количество книг: ");
                numberOfBooks = scanner.nextInt();
                scanner.nextLine();

                if (numberOfBooks <= 0) {
                    System.out.println("Количество книг должно быть положительным числом.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Некорректный ввод. Пожалуйста, введите число.");
                scanner.nextLine();
            }
        }

        Book[] books = new Book[numberOfBooks];

        for (int i = 0; i < numberOfBooks; i++) {
            while (true) {
                try {
                    System.out.println("Введите наименование книги " + (i + 1) + ": ");
                    String title = scanner.nextLine();

                    System.out.println("Введите год издания книги " + (i + 1) + ": ");
                    int year = scanner.nextInt();
                    scanner.nextLine();

                    if (year <= 0) {
                        System.out.println("Год издания должен быть положительным числом.");
                        continue;
                    }

                    books[i] = new Book(title, year);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Некорректный ввод. Пожалуйста, введите число для года.");
                    scanner.nextLine();
                }
            }
        }

        System.out.println("Введенные книги:");
        for (int i = 0; i < numberOfBooks; i++) {
            System.out.println(books[i]);
        }
    }
}

record Book(String title, int year) {

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", year=" + year +
                '}';
    }
}
