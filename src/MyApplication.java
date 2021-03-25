import javafx.application.Application;
import javafx.stage.Stage;
import mediator.ChatClient;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

import java.io.IOException;

public class MyApplication extends Application
{
  private ChatClient chatClient;

  public void start(Stage primaryStage) throws IOException {
    try
    {
      Model model = new ModelManager();
      ChatClient chatClient = new ChatClient(model, "localhost", 2021);
      ViewModelFactory viewModelFactory = new ViewModelFactory(model);
      ViewHandler view = new ViewHandler(viewModelFactory);

      view.start(primaryStage);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  @Override public void stop() throws IOException {
    chatClient.close();
  }
}
