import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket; //Response for listening to the client requests

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer(){
        try{
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept(); //block method, waiting for client requests until it receives one
                System.out.println("A new client has connected");

                ClientHandler clientHander = new ClientHandler(socket);
                //when a class implements runnable interface, the instance of the class
                //can be executed by a separate thread, however to spawn a new Thread object
                //we need to pass the instance of the class to the Thread constructor

                Thread thread = new Thread(clientHander);
                thread.start(); //start the thread
            }
        }catch (IOException e){

        }
    }

    public void closeServerSocket(){
        try{
            if(serverSocket != null){
                serverSocket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{

        ServerSocket serverSocket = new ServerSocket(1234); //create a server for listening to the client requests at port 1234
        Server server = new Server(serverSocket);
        server.startServer();

    }
}
