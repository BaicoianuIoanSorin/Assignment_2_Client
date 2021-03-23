package model;

import utility.observer.subject.NamedPropertyChangeSubject;

import java.util.ArrayList;

public interface Model extends NamedPropertyChangeSubject
{
  void addLog(String log1);
  void sendMessage(String message);
  ArrayList<String> getLog();

}
