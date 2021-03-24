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
import java.net.SocketException;

public class ChatClient implements PropertyChangeListener
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Gson gson;
  private Model model;
  private ChatClientReader reader;
  private ChatClientSender sender;


  public ChatClient(Model model, String host, int port) throws IOException
  {
    this.model = model;
    socket = new Socket(host, port);
    out = new PrintWriter(socket.getOutputStream(), true);
    in = new BufferedReader(new BufferedReader(new InputStreamReader(socket.getInputStream())));
    gson = new Gson();
    reader = new ChatClientReader(in, this);
    sender = new ChatClientSender(out);
    model.addListener(null,this);
  }
  public void receive(String message)
  {
    SendOutPackage sendOutPackage = gson.fromJson(message, SendOutPackage.class);
    if(sendOutPackage.isCommand())
    {

    }
    else
    {

    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    sender.send((String)evt.getNewValue());
  }
}
