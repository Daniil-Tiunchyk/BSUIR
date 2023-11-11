package Labwork_4_v13;

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
                String result = modifyData(inputData);
                byte[] sendData = result.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                socket.send(sendPacket);

                System.out.println("Запрос от " + receivePacket.getAddress() + ":" + receivePacket.getPort() +inputData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String modifyData(String data) {
        char[] dataChars = data.toCharArray();
        for (int i = 0; i < dataChars.length; i++) {
            if ((i + 1) % 4 == 0) dataChars[i] = '%';
            if ((i + 1) % 5 == 0) dataChars[i] = '#';
        }
        return new String(dataChars);
    }
}
