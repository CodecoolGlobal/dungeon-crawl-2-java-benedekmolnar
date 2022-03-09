package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.actors.Player;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    private int widthModifier = 0;
    private int heightModifier = 0;
    private final int canvasWidth = 600;
    private final int canvasHeight = 600;
    GameMap map = MapLoader.loadMap();
    public Canvas canvas = new Canvas(canvasWidth, canvasHeight);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label meo = new Label();
    Label inventory = new Label();
    Button pickUpButton = new Button("Pick up item");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(new Label("Meo: "), 0, 1);
        ui.add(pickUpButton, 0, 2);
        ui.add(new Label("Inventory: "), 0, 3);
        ui.add(inventory, 1, 3);
        ui.add(healthLabel, 1, 0);
        ui.add(meo, 1, 1);

        pickUpButton.setFocusTraversable(false);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Legend of Martin");
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                if (map.getPlayer().getCell().getNeighbor(0, -1).getType() == CellType.FLOOR
                        && map.getPlayer().getCell().getNeighbor(0, -1).getActor() == null){
                    heightModifier++;
                }
                map.getPlayer().move(0, -1);
                refresh();
                break;
            case DOWN:
                if (map.getPlayer().getCell().getNeighbor(0, 1).getType() == CellType.FLOOR
                        && map.getPlayer().getCell().getNeighbor(0, 1).getActor() == null){
                    heightModifier--;
                }
                map.getPlayer().move(0, 1);
                refresh();
                break;
            case LEFT:
                if (map.getPlayer().getCell().getNeighbor(-1, 0).getType() == CellType.FLOOR
                        && map.getPlayer().getCell().getNeighbor(-1, 0).getActor() == null){
                    widthModifier++;
                }
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case RIGHT:
                if (map.getPlayer().getCell().getNeighbor(1, 0).getType() == CellType.FLOOR
                        && map.getPlayer().getCell().getNeighbor(1, 0).getActor() == null){
                    widthModifier--;
                }
                map.getPlayer().move(1, 0);
                refresh();
                break;
        }
    }

    private void refresh() {
        context.setFill(Color.color(0.278, 0.176, 0.235));
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x + widthModifier, y + heightModifier);
                }else if  (cell.getItem() != null) {
                        Tiles.drawTile(context, cell.getItem(), x + widthModifier, y + heightModifier);
                } else {
                    Tiles.drawTile(context, cell, x + widthModifier, y + heightModifier);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        meo.setText("15");
        pickUpButton.setOnMouseClicked(this::onClick);
    }

    private void onClick(MouseEvent mouseEvent) {
        Cell currentCell = map.getPlayer().getCell();
        Item itemToPickUp = currentCell.getItem();
        map.getPlayer().addItemToInventory(itemToPickUp.getTileName());
        currentCell.setItem(null);
        inventory.setText(map.getPlayer().inventoryToString());
    }
}
