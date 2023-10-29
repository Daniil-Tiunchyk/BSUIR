package Labwork_3_v7;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (Socket socket = new Socket("localhost", 5035); BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
            while (true) {
                System.out.print("Введите текст для отправки на сервер (или 'exit' для выхода): ");
                String inputData = scanner.nextLine();
                if (inputData.equalsIgnoreCase("exit")) break;
                writer.println(inputData);
                String receivedData = reader.readLine();
                System.out.println("Получено от сервера: " + receivedData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
