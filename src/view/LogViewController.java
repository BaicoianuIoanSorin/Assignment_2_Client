package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.LogViewModel;

public class LogViewController {
    @FXML
    private TextField userName;
    @FXML
    private Label errorLabel;
    private ViewHandler viewHandler;
    private LogViewModel viewModel;
    private Region root;

    public LogViewController()
    {

    }

    public void init(ViewHandler viewHandler, LogViewModel viewModel, Region root)
    {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;
        userName.textProperty().bindBidirectional(viewModel.getUserNameProperty());
        errorLabel.textProperty().bind(viewModel.getErrorLabelProperty());
    }

    public Region getRoot()
    {
        return root;
    }

    public void reset()
    {
        viewModel.reset();
    }

    @FXML
    private void onEnterName()
    {
        viewModel.enterName();
        viewHandler.openView("Chat");
    }
}
