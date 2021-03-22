package viewmodel;

import model.Exercise;
import model.Model;

public class ManageExerciseViewModel
{
  private ViewModelState viewModelState;
  private StringProperty errorProperty;
  private StringProperty headerProperty;
  private ObjectProperty<Boolean> completedProperty;
  private StringProperty topicProperty;
  private IntegerProperty numberProperty;
  private IntegerProperty sessionProperty;
  private ObjectProperty<Boolean> editableProperty;

  private Model model;

  public ManageExerciseViewModel(Model model, ViewModelState viewModelState)
  {
    this.model = model;
    this.viewModelState = viewModelState;
    errorProperty = new SimpleStringProperty();
    headerProperty = new SimpleStringProperty("Add exercise");
    completedProperty = new SimpleObjectProperty<>(false);
    topicProperty = new SimpleStringProperty();
    numberProperty = new SimpleIntegerProperty();
    sessionProperty = new SimpleIntegerProperty();
    editableProperty = new SimpleObjectProperty<>(true);
   }

  public void reset()
  {
    errorProperty.set(null);
    if (viewModelState != null && viewModelState.getNumber() != null)
    {
      Exercise ex = model.getExercise(viewModelState.getNumber());
      headerProperty.set("Remove exercise");
      numberProperty.set(ex.getExerciseNumber());
      sessionProperty.set(ex.getSessionNumber());
      topicProperty.set(ex.getTopic());
      completedProperty.set(ex.isCompleted());
      if (viewModelState.isRemove())
      {
        headerProperty.set("Remove exercise");
        editableProperty.set(false);
      }
      else
      {
        headerProperty.set("Edit exercise");
        editableProperty.set(true);
      }
    }
    else if (viewModelState != null && !viewModelState.isRemove())
    {
      headerProperty.set("Add exercise");
      numberProperty.set(0);
      sessionProperty.set(0);
      topicProperty.set(null);
      completedProperty.set(false);
      editableProperty.set(true);
    }
  }

  public StringProperty getErrorProperty()
  {
    return errorProperty;
  }

  public StringProperty getHeaderProperty()
  {
    return headerProperty;
  }

  public ObjectProperty<Boolean> getCompletedProperty()
  {
    return completedProperty;
  }

  public StringProperty getTopicProperty()
  {
    return topicProperty;
  }

  public IntegerProperty getNumberProperty()
  {
    return numberProperty;
  }

  public IntegerProperty getSessionProperty()
  {
    return sessionProperty;
  }

  public ObjectProperty<Boolean> getEditableProperty()
  {
    return editableProperty;
  }

  private Exercise createExerciseObject()
  {
    Exercise exercise = new Exercise(sessionProperty.get(),
        numberProperty.get(), topicProperty.get());
    exercise.setCompleted(completedProperty.get());
    return exercise;
  }

  public boolean accept()
  {
    if (headerProperty == null)
    {
      errorProperty.set("Null property");
      return false;
    }
    try
    {
      if (headerProperty.get().contains("Add"))
      {
        model.addExercise(createExerciseObject());
      }
      else if (headerProperty.get().contains("Edit"))
      {
        model.editExercise(viewModelState.getNumber(), createExerciseObject());
      }
      else // if (headerProperty.get().contains("Remove"))
      {
        model.removeExercise(createExerciseObject().getNumber());
      }
      return true;
    }
    catch (Exception e)
    {
      errorProperty.set(e.getMessage());
      return false;
    }
  }

}
