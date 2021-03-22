package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Exercise;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ListExerciseViewModel implements PropertyChangeListener
{
  private Model model;
  private ViewModelState viewModelState;

  private ObservableList<SimpleExerciseViewModel> list;
  private ObjectProperty<SimpleExerciseViewModel> selectedExerciseProperty;
  private StringProperty errorProperty;

  public ListExerciseViewModel(Model model, ViewModelState viewModelState)
  {
    this.model = model;
    this.model.addListener(this);
    this.viewModelState = viewModelState;
    selectedExerciseProperty = new SimpleObjectProperty<>();
    errorProperty = new SimpleStringProperty();

    list = FXCollections.observableArrayList();
    loadFromModel();
  }

  public void clear()
  {
    errorProperty.set(null);
    // Maybe: loadFromModel()
  }

  private void loadFromModel()
  {
    list.clear();
    ArrayList<Exercise> exercises = model.getAllExercises();
    for (int i = 0; i < exercises.size(); i++)
    {
      list.add(new SimpleExerciseViewModel(exercises.get(i)));
    }
  }

  public void addEdit()
  {
    viewModelState.setRemove(false);
    if (selectedExerciseProperty.get() != null)
    {
      viewModelState
          .setNumber(selectedExerciseProperty.get().getNumberProperty().get());
    }
    else
    {
      viewModelState.removeNumber();
    }
  }

  public boolean remove()
  {
    if (selectedExerciseProperty.get() != null)
    {
      viewModelState
          .setNumber(selectedExerciseProperty.get().getNumberProperty().get());
      viewModelState.setRemove(true);
      return true;
    }
    else
    {
      viewModelState.setRemove(false);
      errorProperty.set("No selection");
      return false;
    }
  }

  public ObservableList<SimpleExerciseViewModel> getAll()
  {
    return list;
  }

  public void setSelected(SimpleExerciseViewModel exerciseVm)
  {
    selectedExerciseProperty.set(exerciseVm);
  }

  public StringProperty getErrorProperty()
  {
    return errorProperty;
  }

  private void removeSimpleExercise(String number)
  {
    for (int i = 0; i < list.size(); i++)
    {
      if (list.get(i).getNumberProperty().get().equals(number))
      {
        list.remove(i);
        break;
      }
    }
  }

  private void addSimpleExercise(Exercise exercise)
  {
    for (int i = 0; i < list.size(); i++)
    {
      Exercise ex = model.getExercise(list.get(i).getNumberProperty().get());
      if (exercise.getSessionNumber() < ex.getSessionNumber())
      {
        list.add(i, new SimpleExerciseViewModel(exercise));
        return;
      }
      else if (exercise.getSessionNumber() == ex
          .getSessionNumber())
      {
        if (exercise.getExerciseNumber() < ex.getExerciseNumber())
        {
          list.add(i, new SimpleExerciseViewModel(exercise));
          return;
        }
        else if (exercise.getExerciseNumber() == ex.getExerciseNumber())
        {
          throw new IllegalStateException("Number already exist");
        };
      }
    }
    list.add(new SimpleExerciseViewModel(exercise));
  }

  @Override public void propertyChange(PropertyChangeEvent event)
  {
    // Alternative: call loadFromModel to load all
    Platform.runLater(() -> {
      switch (event.getPropertyName())
      {
        case "Add":
          addSimpleExercise((Exercise) event.getNewValue());
          break;
        case "Remove":
          removeSimpleExercise((String) event.getOldValue());
          break;
        case "Edit":
          removeSimpleExercise((String) event.getOldValue());
          addSimpleExercise((Exercise) event.getNewValue());
          break;
      }
    });
  }
}
