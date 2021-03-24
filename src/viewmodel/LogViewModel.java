package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class LogViewModel {
    private StringProperty userNameProperty;
    private StringProperty errorLabelProperty;
    private Model model;

    public LogViewModel(Model model)
    {
        this.model = model;

        userNameProperty = new SimpleStringProperty("");
        errorLabelProperty = new SimpleStringProperty("");
    }

    public StringProperty getUserNameProperty()
    {
        return userNameProperty;
    }

    public StringProperty getErrorLabelProperty()
    {
        return errorLabelProperty;
    }

    public void reset()
    {
        userNameProperty.set("");
        errorLabelProperty.set("");
    }

    public void enterName()
    {
        model.login(userNameProperty.get());
    }
}
