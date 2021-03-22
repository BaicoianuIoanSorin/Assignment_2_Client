import javafx.application.Application;
import javafx.stage.Stage;
import mediator.ExercisesServer;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

import java.io.IOException;

public class MyApplication extends Application
{
  private ExercisesServer exercisesServer;

  public void start(Stage primaryStage) throws IOException {

    Model model = new ModelManager();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler view = new ViewHandler(viewModelFactory);
    exercisesServer = new ExercisesServer(model);
    Thread thread = new Thread(exercisesServer);
    thread.start();


    view.start(primaryStage);
  }
  @Override public void stop() throws IOException {
    exercisesServer.close();
  }
}
