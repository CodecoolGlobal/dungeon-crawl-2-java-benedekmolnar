package com.codecool.dungeoncrawl;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InputDialog {
    static String playerName;

    public static String display() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        TextField text = new TextField();

        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");
        saveButton.setOnAction(e -> {
            playerName = text.getText();
            stage.close();
        });
        cancelButton.setOnAction(e -> {
            stage.close();
        });

        Label label1 = new Label(" Enter your name:");

        GridPane layout = new GridPane();

        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(5);
        layout.setHgap(5);

        layout.add(text, 1,1);
        layout.add(cancelButton, 0,3);
        layout.add(label1, 1,0);
        layout.add(saveButton, 2,3);

        Scene scene = new Scene(layout, 310, 110);
        stage.setTitle("Save");
        stage.setScene(scene);
        stage.showAndWait();

        return playerName;
    }
}
