package Labwork_4;

import java.io.IOException;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        DatagramSocket socket;

        try {
            socket = new DatagramSocket(5040);

            System.out.println("Сервер запущен");

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String inputData = new String(receivePacket.getData(), 0, receivePacket.getLength());
                String[] numbers = inputData.split(" ");

                if (numbers.length != 2) {
                    System.out.println("Получен неверный запрос от " + receivePacket.getAddress() + ":" + receivePacket.getPort());
                    continue;
                }
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int calculateGCD(int a, int b) {
        return b == 0 ? a : calculateGCD(b, a % b);
    }

    private static int calculateLCM(int a, int b) {
        return (a * b) / calculateGCD(a, b);
    }
}

// RFC 768 (User Datagram Protocol)
//Source Port + Destination Port + Length + Checksum + данные
//расчёт контрольной суммы с использованием обратного кода(one's complement checksum).
//Может вычисляться сетевым адаптером(?)
//Protocol Data Unit (header + payload). Инкапсуляция?
//IP/ICMP. TCP/UDP. DNS/DHCP
//виртуальное полнодуплексное(?) соеденение медлу процессами