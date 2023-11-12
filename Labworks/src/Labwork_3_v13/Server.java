package Labwork_3_v13;

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
