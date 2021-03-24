package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ModelManager implements Model
{
  private ArrayList<String> log;
  private ArrayList<String> messages;
  private String name;
  private PropertyChangeSupport propertyChangeSupport;
  private UserList userList;
  private ArrayList<MessageClientHandler> OnlineUsers;

  public ModelManager()
  {
    this.name = "";
    this.messages = new ArrayList<>();
    this.log = new ArrayList<>();
    this.userList = new UserList();
    this.propertyChangeSupport = new PropertyChangeSupport(this);
    this.OnlineUsers = new ArrayList<>();
  }

  @Override
  public void login(String name) {
    userList.addUser(name); // SENDING OUT THE INFO TO THE SERVER
    this.name = name;
    /** ->>> Property Change Support to be added(i think - Ionut) <<<- **/

  }

  @Override public UserList getAllUsers()
  {
    return userList; //THIS SHOULD BE A REQUEST TO SERVER
  }

//  @Override public void addLog(String log1)
//  {
//    log.add(log1);
//    propertyChangeSupport.firePropertyChange("Log",null,log);
//  }

  @Override public ArrayList<String> getLog()
  {
    return log;  //SHOULD REQUEST FROM SERVER
  }

  @Override public int getConnectedUsersInt()
  {
    return 0; // A REQUEST FROM SERVER
  }

  @Override public ArrayList<String> getConnectedUsers()
  {
    return null;  // A REQUEST FROM SERVER
  }

  @Override public void addListener(String propertyName,
                                    PropertyChangeListener listener)
  {
    if(propertyName == null){
      propertyChangeSupport.addPropertyChangeListener(listener);
    }
    else {
      propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }
  }

  @Override public void removeListener(String propertyName,
                                       PropertyChangeListener listener)
  {
    if(propertyName == null){
      propertyChangeSupport.removePropertyChangeListener(listener);
    }
    else {
      propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }
  }
//
//  @Override
//  public ArrayList<String> getMessages(String activeUserName) {
//
//    for(int i = 0; i < userList.size(); i++)
//    {
//      if(userList.getUser(i).equals(activeUserName))
//      {
//        return userList.getUser(i).getMessages();
//      }
//    }
//    return null;
//  }

  @Override
  public String getName()
  {
    return name;
  }

  @Override
  public int getSizeOfUsers()
  {
    return userList.size();
  }

//  @Override
//  public void sendMessage(String userName, String message)
//  {
//    for(int i = 0; i < userList.size(); i++)
//    {
//      if(userList.getUser(i).getName().equals(userName))
//      {
//        userList.getUser(i).addMessage(message);
//      }
//    }
//  }
}
