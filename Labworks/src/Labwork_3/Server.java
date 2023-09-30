package Labwork_3;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5035);
            System.out.println("Сервер запущен");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клиент подключился.");

                Thread clientHandler = new ClientHandler(clientSocket);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            File file = new File("server_log.txt");
            FileWriter fileWriter = new FileWriter(file, true);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Получено от клиента: " + inputLine);
                String[] numbers = inputLine.split(" ");
                if (numbers.length == 2) {
                    try {
                        int num1 = Integer.parseInt(numbers[0]);
                        int num2 = Integer.parseInt(numbers[1]);
                        int gcd = calculateGCD(num1, num2);
                        int lcm = calculateLCM(num1, num2);

                        out.println("НОД: " + gcd + ", НОК: " + lcm);
                        fileWriter.write("Получено от клиента: " + inputLine + ", НОД: " + gcd + ", НОК: " + lcm + "\n");
                    } catch (NumberFormatException e) {
                        out.println("Ошибка: Введите два целых числа через пробел.");
                    }
                } else {
                    out.println("Ошибка: Введите два целых числа через пробел.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int calculateGCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return calculateGCD(b, a % b);
    }

    private int calculateLCM(int a, int b) {
        return (a * b) / calculateGCD(a, b);
    }
}
