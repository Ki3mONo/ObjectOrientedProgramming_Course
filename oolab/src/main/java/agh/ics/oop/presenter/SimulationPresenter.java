package agh.ics.oop.presenter;

import agh.ics.oop.*;
import agh.ics.oop.model.*;
import agh.ics.oop.model.MapChangeListener;
import agh.ics.oop.model.WorldMap;
import agh.ics.oop.model.util.FileMapDisplay;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    private WorldMap map;
    private SimulationEngine engine;


    @FXML
    private Label infoLabel;
    @FXML
    private TextField moveInputField;
    @FXML
    private GridPane mapGrid;
    @FXML
    private Label moveDescriptionLabel;

    @FXML
    public void initialize() {
        moveDescriptionLabel.setVisible(true);
        mapGrid.setVisible(false);
    }
    public void setWorldMap(WorldMap map) {
        this.map = map;
    }
    public void drawMap(){
        clearGrid();
        Boundary bounds = map.getCurrentBounds();
        Vector2d lowerLeft = bounds.bottomLeft();
        Vector2d upperRight = bounds.upperRight();

        int width = upperRight.getX() - lowerLeft.getX() + 1;
        int height = upperRight.getY() - lowerLeft.getY() + 1;

        for (int x = 0; x < width; x++) {
            Label label = new Label(Integer.toString(lowerLeft.getX() + x));
            label.setStyle("-fx-font-weight: bold; -fx-border-color: black; -fx-alignment: center;");
            label.setMinSize(50,20);
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.add(label, x + 1, 0);
        }

        for (int y = 0; y < height; y++) {
            Label label = new Label(Integer.toString(upperRight.getY() - y));
            label.setStyle("-fx-font-weight: bold; -fx-border-color: black; -fx-alignment: center;");
            label.setMinSize(20,50);
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.add(label, 0, y + 1);
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int inX = x;
                int inY = y;
                Vector2d position = new Vector2d(lowerLeft.getX() + x, upperRight.getY() - y);
                map.objectAt(position).ifPresent(element -> {
                    WorldElementBox elementBox = new WorldElementBox(element);
                    GridPane.setHalignment(elementBox, HPos.CENTER);
                    mapGrid.add(elementBox, inX+1, inY+1);
                });
            }
        }

    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            drawMap();
            moveDescriptionLabel.setText(message);
        });
    }
    private void clearGrid() {

        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0));
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }
    @FXML
    public void onSimulationStartClicked(ActionEvent event) {
        GrassField map = new GrassField(10);
        this.setWorldMap(map);

        ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();
        FileMapDisplay fileMapDisplay = new FileMapDisplay();
        map.addObserver(consoleMapDisplay);
        map.addObserver(this);
        map.addObserver(fileMapDisplay);
        map.addObserver((worldMap, message) -> {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String formattedMessage = timestamp + " " + worldMap.getID().toString() + " " + message;
            Platform.runLater(() -> moveDescriptionLabel.setText(formattedMessage));
        });

        String inputText = moveInputField.getText();
        if (inputText != null && !inputText.trim().isEmpty()) {
            String[] inputTokens = inputText.split(" ");

            List<MoveDirection> directions = OptionsParser.parse(inputTokens);

            List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));

            Simulation simulation = new Simulation(positions, directions, map);
            engine = new SimulationEngine(List.of(simulation));
            engine.runAsyncInThreadPool();
            infoLabel.setVisible(false);
            mapGrid.setVisible(true);
        } else {
            infoLabel.setText("Please enter valid moves.");
        }
    }
}
