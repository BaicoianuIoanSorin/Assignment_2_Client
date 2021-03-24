package model;

import java.util.ArrayList;
import java.util.Random;

public class User
{
  private String name;
  private ArrayList<String> messages;

  public User()
  {
    Random random = new Random();
    this.name = "User" + random.nextInt(100);
    messages = new ArrayList<>();
  }

  public User(String name)
  {
    this.name = name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void addMessage(String message)
  {
   messages.add(message);
  }

  public ArrayList<String> getMessages()
  {
    return messages;
  }

  @Override public String toString()
  {
    return "User{" + "name='" + name + '\'' + '}';
  }

  public boolean equals(Object obj)
  {
    if(!(obj instanceof User))
      return false;
    User other = (User)obj;
    return other.name.equals(name);
  }
}