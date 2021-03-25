package mediator;

import java.util.ArrayList;

public class SendOutPackage {
    private String messageSenderName;
    private String message;
    private String command;
    private ArrayList<String> log;

    public SendOutPackage(String messageName,String message){
        this.messageSenderName = messageName;
        this.message = message;
        this.command = null;
    }

    public SendOutPackage(String command){
        this.command = command;
        this.messageSenderName = null;
        this.message = null;
    }

    public SendOutPackage(ArrayList log){
        this.log = log;
        this.messageSenderName = null;
        this.message = null;
        this.command = null;
    }

    public String getCommand() {
        return command;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageName() {
        return messageSenderName;
    }

    public boolean isCommand(){
        if(this.getCommand()==null){
            return false;
        }
        else return true;
    }
}