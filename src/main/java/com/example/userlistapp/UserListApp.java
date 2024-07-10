package com.example.userlistapp;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class UserListApp extends Application {

    private ObservableList<String> userList;
    private ListView<String> listView;
    private TextField nameInput;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        userList = FXCollections.observableArrayList(new ArrayList<>());
        listView = new ListView<>(userList);

        nameInput = new TextField();
        nameInput.setPromptText("Enter name");

        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");

        addButton.setOnAction(e -> addUser());
        deleteButton.setOnAction(e -> deleteUser());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(nameInput, addButton, deleteButton, listView);

        Scene scene = new Scene(layout, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("User List App");
        primaryStage.show();
    }

    private void addUser() {
        String name = nameInput.getText().trim();
        if (validateName(name)) {
            userList.add(name);
            nameInput.clear();
        } else {
            showAlert("Invalid Name", "The name must start with an uppercase letter and be at least 5 characters long.");
        }
    }

    private void deleteUser() {
        String selectedUser = listView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userList.remove(selectedUser);
        } else {
            showAlert("No Selection", "Please select a user to delete.");
        }
    }

    private boolean validateName(String name) {
        return !name.isEmpty() && Character.isUpperCase(name.charAt(0)) && name.length() >= 5;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
