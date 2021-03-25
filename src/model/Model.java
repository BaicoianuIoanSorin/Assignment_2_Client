package model;

import utility.observer.subject.NamedPropertyChangeSubject;

import java.util.ArrayList;

public interface Model extends NamedPropertyChangeSubject
{
  void login(String name);
  void addLogs(ArrayList<String> logs);
  ArrayList<String> getLogs();
  String getName();
}
