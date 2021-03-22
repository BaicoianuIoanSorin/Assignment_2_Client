package mediator;

import model.Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ExercisesServer implements Runnable{
    private static int PORT = 2910;
    private boolean running;
    private ServerSocket welcomeSocket;
    private Model model;
    private ExercisesClientHandler exercisesClientHandler;
    private Socket socket;

    public ExercisesServer(Model model) throws IOException {
        this.model = model;
        welcomeSocket = new ServerSocket(PORT);
    }

    public void close() throws IOException {
        exercisesClientHandler.close();
        socket.close();
    }

    @Override
    public void run() {
        try {
            socket = welcomeSocket.accept();
            exercisesClientHandler = new ExercisesClientHandler(socket,model);
            running = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread thread = new Thread(exercisesClientHandler);

    }
}
