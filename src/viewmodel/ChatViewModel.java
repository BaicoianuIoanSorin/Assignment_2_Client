package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import utility.observer.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ChatViewModel implements UnnamedPropertyChangeSubject, PropertyChangeListener {
    private Model model;
    private ObservableList<String> messages;
    private StringProperty newMessage;
    private StringProperty userNameInfo;
    private StringProperty activeUsers;
    private PropertyChangeSupport propertyChangeSupport;
    private static ChatViewModel instance;
    private static Object lock = new Object();


    private ChatViewModel(Model model)
    {
        this.model = model;
        activeUsers = new SimpleStringProperty();
        newMessage = new SimpleStringProperty("");
        userNameInfo = new SimpleStringProperty("");
        messages = FXCollections.observableArrayList();
        messages.addAll(model.getLogs());
        propertyChangeSupport = new PropertyChangeSupport(this);
        this.model.addListener(null,this);
    }

    public static ChatViewModel getInstance(Model model)
    {
        if(instance ==null)
        {
            synchronized (lock)
            {
                if (instance==null)
                {
                    instance = new ChatViewModel(model);
                }
            }
        }
        return instance;
    }
    public StringProperty getNewMessageProperty()
    {
        return newMessage;
    }

    public StringProperty getUserNameInfoProperty()
    {
        return userNameInfo;
    }

    public StringProperty getActiveUsersProperty()
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
        messages.addAll(model.getLogs());
        newMessage.set("");
        activeUsers.set(null);
    }

    public void sendMessage()
    {
        propertyChangeSupport.firePropertyChange(model.getName(),null,newMessage.get());
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        Platform.runLater(()->{
            messages.clear();
            if(evt.getPropertyName().equals("getUserCount"))
            {
                activeUsers.set((String)evt.getNewValue());
            }
            else if(evt.getPropertyName().equals("getUsersNames")){
                messages.add((String) evt.getNewValue());
            }
            else if(evt.getPropertyName().equals("DisplayLog")){

                messages.addAll((ArrayList<String>)evt.getNewValue());
            }
        });
    }
}
