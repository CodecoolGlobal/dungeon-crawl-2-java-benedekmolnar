package com.codecool.dungeoncrawl;

import javafx.scene.control.ChoiceDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoadDialog {

    public static void display(){
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
            //TODO: load gamestate
            dialog.close();
        }
    }
}
