package mediator;

import com.google.gson.Gson;
import model.Model;
import viewmodel.ChatViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
// Needs to receive fires from ChatViewModel.
public class ChatClient implements PropertyChangeListener
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Gson gson;
  private Model model;
  private ChatClientReader reader;
  private ChatClientSender sender;
  private PropertyChangeSupport propertyChangeSupport;
  private ChatViewModel viewModel;


  public ChatClient(Model model, String host, int port) throws IOException
  {
    this.model = model;
    viewModel = ChatViewModel.getInstance(model);
    viewModel.addListener(this);
    socket = new Socket(host, port);
    out = new PrintWriter(socket.getOutputStream(), true);
    in = new BufferedReader(new BufferedReader(new InputStreamReader(socket.getInputStream())));
    gson = new Gson();
    reader = new ChatClientReader(in, this);

    this.model.addListener(null,this);
    propertyChangeSupport = new PropertyChangeSupport(this);
    sender = new ChatClientSender(out, model);
    Thread thread = new Thread(reader);
    thread.start();
  }
  public void receive(String message)
  {
    SendOutPackage sendOutPackage = gson.fromJson(message, SendOutPackage.class);
    if(sendOutPackage.isCommand())
    {
        switch (sendOutPackage.getCommand())
        {
          case "getUserCount":
          {
            propertyChangeSupport.firePropertyChange("getUserCount", null, sendOutPackage.getMessage());
          }
          case "getUsersNames":
          {
              model.addLog(sendOutPackage.getMessage());
          }
        }
    }
    else
    {
        model.addLogs(sendOutPackage.getLog());
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    System.out.println("Received");
    if(evt.getPropertyName().equals("addUser"))
    {
      sender.sendUser((String)evt.getNewValue());
    }
    else
    {
      System.out.println((String)evt.getNewValue());
      sender.send((String)evt.getNewValue());
    }
  }
  public void close() throws IOException
  {
    socket.close();
  }
}
