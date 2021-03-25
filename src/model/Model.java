package model;

import utility.observer.subject.NamedPropertyChangeSubject;

import java.util.ArrayList;

public interface Model extends NamedPropertyChangeSubject
{
  void login(String name);
  UserList getAllUsers();
  void addLogs(ArrayList<String> logs);
  ArrayList<String> getLogs();
  int getConnectedUsersInt();
  ArrayList<String> getConnectedUsers();
  ArrayList<String> getMessages(String activeUserName);
  String getName();
  int getSizeOfUsers();
  void sendMessage(String userName, String message);
}
