package agh.ics.oop.presenter;

import agh.ics.oop.*;
import agh.ics.oop.model.*;
import agh.ics.oop.model.MapChangeListener;
import agh.ics.oop.model.WorldMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    private WorldMap map;
    @FXML
    private Label infoLabel;
    @FXML
    private TextField moveInputField; // Bind the TextField
    @FXML
    private Button startButton; // Bind the Button
    @FXML
    private GridPane mapGrid;

    public void setWorldMap(WorldMap map) {
        this.map = map;
    }
    public void drawMap(){
        clearGrid();
        infoLabel.setText(map.toString());
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        drawMap();
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }
    @FXML
    public void onSimulationStartClicked(ActionEvent event) {
        GrassField map = new GrassField(10);
        this.setWorldMap(map);
        ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();
        map.addObserver(consoleMapDisplay);
        map.addObserver(this);
        String inputText = moveInputField.getText();
        if (inputText != null && !inputText.trim().isEmpty()) {
            String[] inputTokens = inputText.split("\\s+");

            List<MoveDirection> directions = OptionsParser.parse(inputTokens);

            List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));

            Simulation simulation = new Simulation(positions, directions, map);
            simulation.run();
        } else {
            infoLabel.setText("Please enter valid moves.");
        }
    }
}
