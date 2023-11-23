import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Введите строку 1: ");
            String line1 = reader.readLine();
            System.out.print("Введите строку 2: ");
            String line2 = reader.readLine();

            writer.println(line1);
            writer.println(line2);

            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(serverReader.readLine());
            System.out.println(serverReader.readLine());
            System.out.println(serverReader.readLine());

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
