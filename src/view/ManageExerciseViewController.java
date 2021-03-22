package view;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import utility.IntStringConverter;
import viewmodel.ManageExerciseViewModel;

public class ManageExerciseViewController
{
  @FXML private Label headerLabel;
  @FXML private TextField sessionField;
  @FXML private TextField numberField;
  @FXML private TextField topicField;
  @FXML private RadioButton completedRadiobutton;
  @FXML private Label errorLabel;
  @FXML private Button submitButton;

  private Region root;
  private ViewHandler viewHandler;
  private ManageExerciseViewModel viewModel;

  public void init(ViewHandler viewHandler,
      ManageExerciseViewModel manageExerciseViewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = manageExerciseViewModel;
    this.root = root;

    errorLabel.textProperty().bind(viewModel.getErrorProperty());
    headerLabel.textProperty().bind(viewModel.getHeaderProperty());
    topicField.textProperty().bindBidirectional(viewModel.getTopicProperty());
    completedRadiobutton.selectedProperty()
        .bindBidirectional(viewModel.getCompletedProperty());
    Bindings.bindBidirectional(numberField.textProperty(),
        viewModel.getNumberProperty(), new IntStringConverter());
    Bindings.bindBidirectional(sessionField.textProperty(),
        viewModel.getSessionProperty(), new IntStringConverter());

    topicField.editableProperty().bind(viewModel.getEditableProperty());
    numberField.editableProperty().bind(viewModel.getEditableProperty());
    sessionField.editableProperty().bind(viewModel.getEditableProperty());
    viewModel.getEditableProperty()
        .addListener((obs, v1, v2) -> completedRadiobutton.setDisable(!v2));

    reset();
  }

  public void reset()
  {
    viewModel.reset();
  }

  @FXML private void submitButton()
  {
    boolean ok = viewModel.accept();
    if (ok)
      viewHandler.openView("list");
  }

  @FXML private void cancelButton()
  {
    viewHandler.openView("list");
  }

  public Region getRoot()
  {
    return root;
  }

  @FXML private void onEnter(ActionEvent actionEvent)
  {
    if (actionEvent.getTarget() == sessionField)
    {
      numberField.requestFocus();
    }
    else if (actionEvent.getTarget() == numberField)
    {
      topicField.requestFocus();
    }
    else if (actionEvent.getTarget() == topicField)
    {
      submitButton.requestFocus();
    }
  }
}
