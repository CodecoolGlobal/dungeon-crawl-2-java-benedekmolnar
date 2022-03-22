package com.codecool.dungeoncrawl;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class SaveDialog {
    static String playerName;
    static Stage stage;

    public static String display() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        TextField text = new TextField();

        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");
        saveButton.setOnAction(e -> {
            playerName = text.getText();
            showConfirmationDialog();
        });
        cancelButton.setOnAction(e -> {
            stage.close();
        });

        Label label1 = new Label("Name:");

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

    public static void showConfirmationDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();

        dialog.setTitle("Confirmation");
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);

        dialog.setContentText("Would you like to overwrite the already existing state?");

        dialog.getDialogPane().getButtonTypes().add(yes);
        dialog.getDialogPane().getButtonTypes().add(no);

        dialog.showAndWait().ifPresent(response -> {
            if (response == yes) {
                System.out.println("yes");
                stage.close();
            }
        });
    }
}
