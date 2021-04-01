import java.net.*;
import java.io.*;

public class HelloServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String hello = in.readLine();
            if ("hello server".equals(hello)){
                out.println("hello client");
            }
            else {
                out.println("unrecognised greeting");
            }
        }
        catch(Exception e) {
            System.out.println("Something went wrong: "+e.toString());
        }
    }

    public void stop() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        }
        catch(Exception e) {
            System.out.println("Something went wrong: " + e.toString());
        }
    }

    public static void main(String[] args) {
        HelloServer server = new HelloServer();
        server.start(6666);
    }

}
