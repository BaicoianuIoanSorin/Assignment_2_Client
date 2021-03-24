package view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;

public class ChatViewModel {
    private Model model;
    private ObservableList<String> messages;
    private StringProperty newMessage;
    private StringProperty userNameInfo;
    private IntegerProperty activeUsers;

    public ChatViewModel(Model model)
    {
        this.model = model;
        activeUsers = new SimpleIntegerProperty(model.getSizeOfUsers());
        newMessage = new SimpleStringProperty("");
        userNameInfo = new SimpleStringProperty("");
        messages = FXCollections.observableArrayList();
        messages.addAll(model.getMessages(userNameInfo.get()));

    }

    public StringProperty getNewMessageProperty()
    {
        return newMessage;
    }

    public StringProperty getUserNameInfoProperty()
    {
        return userNameInfo;
    }

    public IntegerProperty getActiveUsersProperty()
    {
        return activeUsers;
    }

    public ObservableList<String> getListOfMessages()
    {
        return messages;
    }

    public void reset()
    {
        /** ->>> More variables to be added <<<- **/
        messages.clear();
        messages.addAll(model.getMessages(userNameInfo.get()));
        newMessage.set("");
        activeUsers.set(model.getSizeOfUsers());
    }

    public void sendMessage()
    {
        model.sendMessage(userNameInfo.get(), newMessage.get());
    }
}
