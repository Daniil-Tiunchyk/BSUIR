package Labwork_4;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 5040;

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.print("Введите два числа через пробел (или 'exit' для выхода): ");
                String input = reader.readLine();

                if ("exit".equalsIgnoreCase(input)) {
                    break;
                }

                String[] numbers = input.split(" ");

                if (numbers.length == 2) {
                    int num1 = Integer.parseInt(numbers[0]);
                    int num2 = Integer.parseInt(numbers[1]);

                    String sendData = num1 + " " + num2;
                    byte[] sendDataBytes = sendData.getBytes();

                    DatagramPacket sendPacket = new DatagramPacket(sendDataBytes, sendDataBytes.length, serverAddress, serverPort);
                    socket.send(sendPacket);

                    byte[] receiveData = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    socket.receive(receivePacket);

                    String result = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println("Результат: " + result);
                } else {
                    System.out.println("Неверный формат ввода. Введите два числа через пробел или 'exit' для выхода.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
