package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main extends Application {
    InputStream is = MapLoader.class.getResourceAsStream("/main.txt");
    private int widthModifier = 0;
    private int heightModifier = 0;
    private final int canvasWidth = 900;
    private final int canvasHeight = 900;
    GameMap map = MapLoader.loadMap(is);
    public Canvas canvas = new Canvas(canvasWidth, canvasHeight);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabelText = new Label("Health: ");
    Label healthLabel = new Label();
    Label inventoryLabelText = new Label("Inventory: ");
    Label inventory = new Label();

    public static void main(String[] args) {
        launch(args);
    }

   @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        designUI(ui);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setBottom(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        scene.setOnKeyPressed(this::onKeyPressed);


        Timeline refresh = new Timeline(
            new KeyFrame(Duration.seconds(0.05),
                    event -> refresh()));
        refresh.setCycleCount(Timeline.INDEFINITE);
        refresh.play();

        primaryStage.setTitle("Legend of Martin");
        primaryStage.show();

        refresh();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().setLastOrder('w');
                break;
            case DOWN:
                map.getPlayer().setLastOrder('s');
                break;
            case LEFT:
                map.getPlayer().setLastOrder('a');
                break;
            case RIGHT:
                map.getPlayer().setLastOrder('d');
                break;
            case SPACE:
                map.getPlayer().setLastOrder('y');
                break;
            case R:
                map.getPlayer().setLastOrder('r');
                break;
            case B:
                map.getPlayer().setLastOrder('b');
                break;
        }
    }

    private void refresh() {
        context.setFill(Color.color(0.278, 0.176, 0.235));
        map.actActors();
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x + 10 - map.getPlayer().getX(), y + 10 - map.getPlayer().getY());
                }else if  (cell.getItem() != null) {
                        Tiles.drawTile(context, cell.getItem(), x + 10 - map.getPlayer().getX(), y + 10 - map.getPlayer().getY());
                } else {
                    Tiles.drawTile(context, cell, x + 10 - map.getPlayer().getX(), y + 10 - map.getPlayer().getY());
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());

        map.getPlayer().pickUpItem();
        inventory.setText(map.getPlayer().inventoryToString());

        inventory.setText(map.getPlayer().inventoryToString());
        if (map.getPlayer().getCell().getType() == CellType.NEXTLEVEL){
            teleportToNextLevel("/memhaz.txt");
        } else if (map.getPlayer().getCell().getType() == CellType.OPENDOOR2){
            teleportToNextLevel("/bosslevel.txt");
        }

    }

    private void teleportToNextLevel(String nextLevelsFilename){
        Map<String, Integer> inventoryOfPlayer = map.getPlayer().getInventory();
        map = MapLoader.loadMap(MapLoader.class.getResourceAsStream(nextLevelsFilename));
        map.getPlayer().setInventory(inventoryOfPlayer);
        refresh();
    }

    private void designUI(GridPane ui){
        List<Label> labels = new ArrayList<>();
        labels.add(healthLabel);
        labels.add(inventory);
        labels.add(healthLabelText);
        labels.add(inventoryLabelText);
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));
        ui.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        for (Label label:labels) {
            label.setFont(Font.font("Impact", FontWeight.NORMAL, 20));
            label.setStyle("-fx-text-fill: white;");
        }

        ui.add(healthLabelText, 0, 0);
        ui.add(inventoryLabelText, 0, 3);
        ui.add(inventory, 1, 3);
        ui.add(healthLabel, 1, 0);
    }
}
