package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;
public class World {

    public static void main(String[] args) {
        List<MoveDirection> directions = new ArrayList<>();
        try {
            directions = OptionsParser.parse(args); // Parsowanie opcji
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }

        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        GrassField map = new GrassField(10);

        ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();
        map.addObserver(consoleMapDisplay);

        System.out.println("Poczatkowy stan mapy:");
        System.out.println(map);

        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
    }

}
