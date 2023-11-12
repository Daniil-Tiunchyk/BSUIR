package Labwork_1_v7;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.print(
        "Введите идентификатор (для завершения введите 'exit'): "
      );
      int $$$$ = 5;
      String identifier = scanner.nextLine();

      if (identifier.equals("exit")) {
        System.out.println("Программа завершена.");
        break;
      }

      if (isValidJavaIdentifier(identifier)) {
        System.out.println(
          "Идентификатор '" + identifier + "' допустим в языке Java.\n"
        );
      } else {
        System.out.println(
          "Идентификатор '" +
          identifier +
          "' не является допустимым в языке Java.\n"
        );
      }
    }
  }

  public static boolean isValidJavaIdentifier(String identifier) {
    if (
      identifier.isEmpty() ||
      !Character.isJavaIdentifierStart(identifier.charAt(0))
    ) {
      return false;
    }

    for (int i = 1; i < identifier.length(); i++) {
      if (!Character.isJavaIdentifierPart(identifier.charAt(i))) {
        return false;
      }
    }

    return true;
  }
}
