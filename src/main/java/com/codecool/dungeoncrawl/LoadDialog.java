package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.model.ActorsModel;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.InventoryModel;
import com.codecool.dungeoncrawl.model.ItemsModel;
import javafx.scene.control.ChoiceDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoadDialog {

    public static void display(GameDatabaseManager dbManager, GameMap map){
        List<String> choices = new ArrayList<>();
        //TODO: choices = list of all player names

        choices.add("a");
        choices.add("b");

        ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
        dialog.setTitle("Load game");
        dialog.setGraphic(null);
        dialog.setHeaderText("Load game");
        dialog.setContentText("Choose a name:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your choice: " + result.get());
            GameState gs = dbManager.loadGameState(result.get());
            map.loadMap(gs.getCurrentMap());
            List<ActorsModel> actorModals = dbManager.loadActorModels(gs.getId());
            map.loadActors(actorModals);
            List<ItemsModel> itemModals = dbManager.loadItemModels(gs.getId());
            map.loadItems(itemModals);
            dialog.close();
            InventoryModel inventoryModel = dbManager.loadInventoryModal(gs.getId());
            map.loadInventory(inventoryModel);
        }
    }
}
