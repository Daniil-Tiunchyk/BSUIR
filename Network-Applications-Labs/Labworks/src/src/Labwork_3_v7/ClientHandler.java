package Labwork_3_v7;

import java.io.*;
import java.net.Socket;

class ClientHandler implements Runnable {

  private final Socket clientSocket;

  public ClientHandler(Socket socket) {
    this.clientSocket = socket;
  }

  @Override
  public void run() {
    try {
      System.out.println(
        "Клиент подключен: " + clientSocket.getInetAddress().getHostAddress()
      );
      BufferedReader reader = new BufferedReader(
        new InputStreamReader(clientSocket.getInputStream())
      );
      PrintWriter writer = new PrintWriter(
        clientSocket.getOutputStream(),
        true
      );
      File outputFile = new File("server_output.txt");
      BufferedWriter fileWriter = new BufferedWriter(
        new FileWriter(outputFile)
      );
      String inputData;
      while ((inputData = reader.readLine()) != null) {
        System.out.println("Получено от клиента: " + inputData);
        String modifiedData = modifyData(inputData);
        writer.println(modifiedData);
        fileWriter.write(modifiedData);
        fileWriter.newLine();
        fileWriter.flush();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String modifyData(String data) {
    char[] dataChars = data.toCharArray();
    for (int i = 0; i < dataChars.length; i++) {
      if ((i + 1) % 4 == 0) dataChars[i] = '%';
      if ((i + 1) % 5 == 0) dataChars[i] = '#';
    }
    return new String(dataChars);
  }
}
