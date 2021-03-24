package mediator;

import model.Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MessageServer implements Runnable{

    private static int PORT = 2021;
    private boolean running;
    private ServerSocket welcomeSocket;
    private Model model;
    private MessageClientHandler messageClientHandler;
    private Socket socket;

    public MessageServer(Model model)throws IOException {
        this.model = model;
        this.welcomeSocket = new ServerSocket(PORT);
    }

    public void close() throws IOException{
        messageClientHandler.close();
        socket.close();
    }

    @Override
    public void run() {
        try {
            socket = welcomeSocket.accept();
            messageClientHandler = new MessageClientHandler(socket,model);
            running = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread serverThread = new Thread(messageClientHandler);
        serverThread.start();
    }
}
