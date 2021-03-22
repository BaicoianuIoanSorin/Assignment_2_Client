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
    SendOutPackage sendOutPackage = new SendOutPackage (gson.toJson(messageToSend, SendOutPackage.class));
    out.println(sendOutPackage);
  }
}
