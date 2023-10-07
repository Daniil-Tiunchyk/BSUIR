package Labwork_4;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket(5040);
            byte[] receiveData = new byte[1024];

            System.out.println("Сервер запущен");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String inputData = new String(receivePacket.getData(), 0, receivePacket.getLength());
                String[] numbers = inputData.split(" ");

                if (numbers.length == 2) {
                    int num1 = Integer.parseInt(numbers[0]);
                    int num2 = Integer.parseInt(numbers[1]);
                    int gcd = calculateGCD(num1, num2);
                    int lcm = calculateLCM(num1, num2);

                    String result = "НОД: " + gcd + ", НОК: " + lcm;
                    byte[] sendData = result.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                    socket.send(sendPacket);

                    System.out.println("Запрос от " + receivePacket.getAddress() + ":" + receivePacket.getPort() +
                            " - НОД: " + gcd + ", НОК: " + lcm);
                } else {
                    System.out.println("Получен неверный запрос от " + receivePacket.getAddress() + ":" + receivePacket.getPort());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }

    private static int calculateGCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return calculateGCD(b, a % b);
    }

    private static int calculateLCM(int a, int b) {
        return (a * b) / calculateGCD(a, b);
    }
}
