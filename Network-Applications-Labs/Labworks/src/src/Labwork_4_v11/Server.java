package Labwork_4_v11;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.StringJoiner;

public class Server {

  public static void main(String[] args) {
    final int port = 12345;
    byte[] buffer = new byte[65507];

    try (DatagramSocket socket = new DatagramSocket(port)) {
      System.out.println("Срвер запущен. Ожидание данных...");

      DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
      socket.receive(packet);

      String receivedData = new String(
        packet.getData(),
        0,
        packet.getLength(),
        StandardCharsets.UTF_8
      );
      String[] lines = receivedData.split("\n", 2);

      String editedLine1 = editString(lines[0]);
      String editedLine2 = editString(lines[1]);
      String commonWord = findCommonWord(editedLine1, editedLine2);

      StringJoiner responseJoiner = new StringJoiner("\n")
        .add("Редактированная строка 1: " + editedLine1)
        .add("Редактированная строка 2: " + editedLine2)
        .add("Самое длинное общее слово: " + commonWord);

      byte[] responseData = responseJoiner
        .toString()
        .getBytes(StandardCharsets.UTF_8);
      DatagramPacket responsePacket = new DatagramPacket(
        responseData,
        responseData.length,
        packet.getAddress(),
        packet.getPort()
      );
      socket.send(responsePacket);

      System.out.println("Ответ отправлен клиенту.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static String editString(String input) {
    String[] words = input.split("\\s+");
    StringJoiner result = new StringJoiner(" ");
    String prevWord = "";

    for (String word : words) {
      if (!word.equals(prevWord)) {
        result.add(word);
      }
      prevWord = word;
    }

    return result.toString();
  }

  private static String findCommonWord(String line1, String line2) {
    String[] words1 = line1.split("\\s+");
    String[] words2 = line2.split("\\s+");

    String commonWord = "";

    for (String word1 : words1) {
      for (String word2 : words2) {
        if (word1.equals(word2) && word1.length() > commonWord.length()) {
          commonWord = word1;
        }
      }
    }

    return commonWord;
  }
}
