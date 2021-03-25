package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

public class ViewHandler {
    private Scene currentScene;
    private Stage primaryStage;
    private ViewModelFactory viewModelFactory;
    private LogViewController logViewController;
    private ChatViewController chatViewController;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.currentScene = new Scene(new Region());
        openView("Log");
    }

    public void openView(String window) {
        Region root = null;
        switch (window) {
            case "Log":
                root = loadLogView("LogView.fxml");
                break;
            case "Chat":
                root = loadChatView("ChatView.fxml");
                break;
        }
        currentScene.setRoot(root);
        String title = "";
        if (root.getUserData() != null) {
            title += root.getUserData();
        }
        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.show();
    }

    public Region loadLogView(String fxmlFile) {
        if (logViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                logViewController = loader.getController();
                logViewController.init(this, viewModelFactory.getLogViewModel(), root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            logViewController.reset();
        }
        return logViewController.getRoot();
    }

    public Region loadChatView(String fxmlFile) {
        if (chatViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                chatViewController = loader.getController();
                chatViewController.init(this, viewModelFactory.getChatViewModel(), root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            chatViewController.reset();
        }
        return chatViewController.getRoot();
    }

}

