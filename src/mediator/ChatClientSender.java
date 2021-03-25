package mediator;

import com.google.gson.Gson;

import java.io.PrintWriter;

public class ChatClientSender
{
  private PrintWriter out;
  private Gson gson;

  public ChatClientSender(PrintWriter out)
  {
    this.out = out;
    gson = new Gson();
  }

  public void send(String messageToSend)
  {
    String json;
    switch (messageToSend)
    {
      case "getUserCount":
      {
        SendOutPackage sendOutPackage = new SendOutPackage(messageToSend);
        json = gson.toJson(sendOutPackage, SendOutPackage.class);
      }
      case "getUsersNames":
      {
        SendOutPackage sendOutPackage = new SendOutPackage(messageToSend);
        json = gson.toJson(sendOutPackage, SendOutPackage.class);
      }
      default:
    }
  }
}
