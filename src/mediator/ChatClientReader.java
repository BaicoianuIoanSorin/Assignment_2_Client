package mediator;

import java.io.BufferedReader;

public class ChatClientReader implements Runnable
{
  private BufferedReader in;
  private boolean running;
  private ChatClient chatClient;

  public ChatClientReader(BufferedReader in, ChatClient chatClient)
  {
    this.in = in;
    running = true;
    this.chatClient = chatClient;
  }

  @Override public void run()
  {
    String receivedMessage;
    while(running)
    {
      try
      {
        System.out.println("Entered ClientReader side: ");
        receivedMessage = in.readLine();
        System.out.println("Entered ClientReader side: " +receivedMessage);
        chatClient.receive(receivedMessage);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
}
