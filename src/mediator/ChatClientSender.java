package mediator;

import com.google.gson.Gson;
import model.Model;

import java.io.PrintWriter;

public class ChatClientSender
{
  private PrintWriter out;
  private Gson gson;
  private Model model;

  public ChatClientSender(PrintWriter out, Model model)
  {
    this.out = out;
    gson = new Gson();
    this.model = model;
  }

  public void send(String messageToSend)
  {
    String json;
    switch (messageToSend)
    {
      case "getUserCount":
      {
        SendOutPackage sendOutPackage = new SendOutPackage("getUserCount", messageToSend, true);
        json = gson.toJson(sendOutPackage, SendOutPackage.class);
        break;
      }
      case "getUsersNames":
      {
        SendOutPackage sendOutPackage = new SendOutPackage(null, messageToSend, true);
        json = gson.toJson(sendOutPackage, SendOutPackage.class);
        break;
      }
      default:
      {
        SendOutPackage sendOutPackage = new SendOutPackage(model.getName(), messageToSend);
        json = gson.toJson(sendOutPackage, SendOutPackage.class);
        break;
      }
    }
    out.println(json);
  }
  public void sendUser(String user)
  {
    String json;
    SendOutPackage sendOutPackage = new SendOutPackage(user, "addUser", true);
    json = gson.toJson(sendOutPackage, SendOutPackage.class);
    out.println(json);
  }
}
