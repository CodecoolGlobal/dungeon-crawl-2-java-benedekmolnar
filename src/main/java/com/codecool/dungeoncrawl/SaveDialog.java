package com.codecool.dungeoncrawl;

import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.util.Optional;


public class SaveDialog {
    static String playerName;
    static Stage stage;

    public static String display() {
        /*TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("Save");
        textInputDialog.setHeaderText(null);
        textInputDialog.setGraphic(null);
        textInputDialog.setContentText("Name:");
        textInputDialog.getDialogPane().getButtonTypes().remove(ButtonType.OK);
        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.APPLY);
        textInputDialog.getDialogPane().getButtonTypes().add(saveButtonType);

        textInputDialog.showAndWait();
        Button saveButton = (Button) textInputDialog.getDialogPane().lookupButton(saveButtonType);
        Button cancelButton = (Button) textInputDialog.getDialogPane().lookupButton(ButtonType.CANCEL);

        saveButton.setOnAction(e -> {
            playerName = textInputDialog.getResult();
            showConfirmationDialog();
            System.out.println("kjk");
        });
        cancelButton.setOnMouseClicked(e -> {
            stage.close();
        });*/
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

        Label nameLabel = new Label("Name:");

        GridPane layout = new GridPane();
        GridPane buttons = new GridPane();

        buttons.add(cancelButton, 0,0);
        buttons.add(saveButton, 1, 0);

        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(5);
        layout.setHgap(5);

        layout.add(text, 0,1);
        layout.add(nameLabel, 0,0);
        layout.add(buttons,0, 2 );

        Scene scene = new Scene(layout, 200, 110);
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
