package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.ChatViewModel;

public class ChatViewController {
    @FXML
    private ListView<String> messages;
    @FXML
    private TextField newMessage;
    @FXML
    private Label userNameInfo;
    @FXML
    private Label activeUsers;
    private ViewHandler viewHandler;
    private Region root;
    private ChatViewModel viewModel;

    public ChatViewController()
    {

    }

    public void init(ViewHandler viewHandler, ChatViewModel viewModel, Region root)
    {
        this.viewModel = viewModel;
        this.viewHandler = viewHandler;
        this.root = root;
        userNameInfo.textProperty().set(viewModel.getUserNameInfoProperty().get());
        activeUsers.textProperty().bind(viewModel.getActiveUsersProperty());
        messages.itemsProperty().set(viewModel.getListOfMessages());
        newMessage.textProperty().bindBidirectional(viewModel.getNewMessageProperty());
    }

    public Region getRoot()
    {
        return root;
    }

    public void reset()
    {
        viewModel.reset();
    }

    @FXML private void onSend()
    {
        viewModel.sendMessage();
        newMessage.setText("");
    }
}
