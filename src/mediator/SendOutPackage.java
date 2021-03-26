package mediator;

import java.util.ArrayList;

public class SendOutPackage {
    private String messageSenderName;
    private String message;
    private String command;
    private boolean isCommand;
    private ArrayList<String> log;
    private String commandName;

    public SendOutPackage(String messageName,String message){
        this.messageSenderName = messageName;
        this.message = message;
        this.command = null;
    }

    public SendOutPackage(String commandName,String command, boolean isCommand){
        this.commandName = commandName;
        this.command = command;
        this.isCommand = isCommand;
        this.messageSenderName = null;
        this.message = null;
    }

    public SendOutPackage(ArrayList<String> log){
        this.log = log;
        this.messageSenderName = null;
        this.message = null;
        this.command = null;
    }

    public ArrayList<String> getLog() {
        return log;
    }

    public String getCommand() {
        return command;
    }

    public String getCommandName()
    {
        return commandName;
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
