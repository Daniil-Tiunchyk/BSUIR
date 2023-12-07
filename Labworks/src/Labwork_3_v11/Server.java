package Labwork_3_v11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringJoiner;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Сервер запущен. Ожидание подключения...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Клиент подключен.");

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            String line1 = reader.readLine();
            String line2 = reader.readLine();

            String editedLine1 = editString(line1);
            String editedLine2 = editString(line2);

            String commonWord = findCommonWord(editedLine1, editedLine2);

            writer.println("Редактированная строка 1: " + editedLine1);
            writer.println("Редактированная строка 2: " + editedLine2);
            writer.println("Самое длинное общее слово: " + commonWord);

            clientSocket.close();
            serverSocket.close();
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
