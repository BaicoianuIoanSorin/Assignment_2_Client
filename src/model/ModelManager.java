package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;
import mediator.*;

public class ModelManager implements Model
{
  private ArrayList<String> log;
  private User user;
  private PropertyChangeSupport propertyChangeSupport;
  private ChatClient client;

  public ModelManager(String host, int port) throws IOException
  {
    this.log = new ArrayList<>();
    this.user = new User();
    client = new ChatClient(this, host, port);
  }

  @Override public void addLog(String log1)
  {
    log.add(log1);
    propertyChangeSupport.firePropertyChange("Log",null,log);
  }

  @Override public void sendMessage(String message)
  {

  }


  @Override public void addListener(String propertyName,
      PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(propertyName,listener);
  }

  @Override public void removeListener(String propertyName,
      PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
  }
}
