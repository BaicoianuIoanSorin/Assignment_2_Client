package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Exercise;

public class SimpleExerciseViewModel
{
  private StringProperty numberProperty;
  private StringProperty topicProperty;
  private ObjectProperty<Boolean> completedProperty;

  public SimpleExerciseViewModel(Exercise exercise)
  {
    numberProperty = new SimpleStringProperty(exercise.getNumber());
    topicProperty = new SimpleStringProperty(exercise.getTopic());
    completedProperty = new SimpleObjectProperty<>(exercise.isCompleted());
  }

  public StringProperty getNumberProperty()
  {
    return numberProperty;
  }

  public StringProperty getTopicProperty()
  {
    return topicProperty;
  }

  public ObjectProperty<Boolean> getCompletedProperty()
  {
    return completedProperty;
  }
}
