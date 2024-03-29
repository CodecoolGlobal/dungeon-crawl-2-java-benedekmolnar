package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.GameMap;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.sql.Date;


public class SaveDialog {
    static String playerName;
    static Stage stage;

    public static String display(GameDatabaseManager dbManager, GameMap map) {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        TextField text = new TextField();

        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");
        saveButton.setOnAction(e -> {
            playerName = text.getText();
            String currentMap = map.saveMap();
            Date date = Date.valueOf(java.time.LocalDate.now());
            if (dbManager.isPlayerNameInDatabase(playerName)){
                showConfirmationDialog(dbManager, map, currentMap, date);
            }else{
                int gameStateID = dbManager.saveGameState(currentMap, date, playerName);
                dbManager.saveActors(map.saveActors(), gameStateID);
                dbManager.saveItems(map.saveItems(), gameStateID);
                dbManager.saveInventory(map.saveInventory(), gameStateID);
                stage.close();
            }
        });
        cancelButton.setOnAction(e -> stage.close());

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

    public static void showConfirmationDialog(GameDatabaseManager databaseManager,GameMap map,String currentMap, Date date){
        Dialog<ButtonType> dialog = new Dialog<>();

        dialog.setTitle("Confirmation");
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);

        dialog.setContentText("Would you like to overwrite the already existing state?");

        dialog.getDialogPane().getButtonTypes().add(yes);
        dialog.getDialogPane().getButtonTypes().add(no);

        dialog.showAndWait().ifPresent(response -> {
            if (response.equals(yes)) {
                int gameStateId = databaseManager.updateGameState(currentMap, date, playerName);
                databaseManager.updateActors(map.saveActors(), gameStateId);
                databaseManager.updateItems(map.saveItems(), gameStateId);
                databaseManager.updateInventory(map.saveInventory(), gameStateId);
                stage.close();
            }
        });
    }
}
