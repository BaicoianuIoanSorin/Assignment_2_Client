package viewmodel;

import model.Model;

public class ViewModelFactory {
    private ChatViewModel chatViewModel;
    private LogViewModel logViewModel;

    public ViewModelFactory(Model model)
    {
        this.chatViewModel = ChatViewModel.getInstance(model);
        this.logViewModel = new LogViewModel(model);
    }

    public ChatViewModel getChatViewModel()
    {
        return chatViewModel;
    }

    public LogViewModel getLogViewModel()
    {
        return logViewModel;
    }
}
