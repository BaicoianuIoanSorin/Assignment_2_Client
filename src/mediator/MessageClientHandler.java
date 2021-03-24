package mediator;

import com.google.gson.Gson;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageClientHandler implements Runnable, PropertyChangeListener {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean running;
    private Gson gson;
    private Model model;
    private PropertyChangeSupport propertyChangeSupport;

    public MessageClientHandler(Socket socket, Model model) throws IOException{
        this.model = model;
        gson = new Gson();
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        out.println(gson.toJson(new SendOutPackage(evt.getPropertyName(),(String)evt.getNewValue())));
    }

    @Override
    public void run() {
        SendOutPackage sendOutPackage;
        while (true) {
            try {
                sendOutPackage = gson.fromJson(in.readLine(), SendOutPackage.class);
                if (sendOutPackage.isCommand()) {
                    switch (sendOutPackage.getCommand()) {
                        case "getUserCount": {
                            out.println(gson.toJson(new SendOutPackage("getUserCount", String.valueOf(model.getConnectedUsersInt()))));
                        }
                        case "getUsersNames": {
                            out.println(gson.toJson(new SendOutPackage("getUsersNames", model.getConnectedUsers().toString())));
                        }
                    }
                } else {
                    model.addLog(sendOutPackage.getMessage());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() throws IOException {
        socket.close();
    }
}
