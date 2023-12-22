package Labwork_4_v11;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {

  public static void main(String[] args) {
    final String serverHostname = "localhost";
    final int serverPort = 12345;
    byte[] buffer = new byte[65507];

    try {
      InetAddress serverAddress = InetAddress.getByName(serverHostname);

      try (DatagramSocket socket = new DatagramSocket()) {
        socket.setSoTimeout(5000);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите строку 1: ");
        String line1 = scanner.nextLine().trim();
        System.out.print("Введите строку 2: ");
        String line2 = scanner.nextLine().trim();

        String combinedLines = line1 + "\n" + line2;
        byte[] dataToSend = combinedLines.getBytes(StandardCharsets.UTF_8);

        DatagramPacket packetToSend = new DatagramPacket(
          dataToSend,
          dataToSend.length,
          serverAddress,
          serverPort
        );
        socket.send(packetToSend);

        DatagramPacket packetToReceive = new DatagramPacket(
          buffer,
          buffer.length
        );
        socket.receive(packetToReceive);

        String responseData = new String(
          packetToReceive.getData(),
          0,
          packetToReceive.getLength(),
          StandardCharsets.UTF_8
        );
        System.out.println("Ответ от сервера:\n" + responseData);
      }
    } catch (SocketTimeoutException e) {
      System.out.println("Время ожидания ответа истекло.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
