// Client Side
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", Integer.parseInt(EnvUtils.get("PORT")));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Send a message to the server
        out.println("Hello, server!");

        // Receive response from the server
        String response = in.readLine();
        System.out.println("Received from server: " + response);

        out.close();
        in.close();
        socket.close();
    }
}
