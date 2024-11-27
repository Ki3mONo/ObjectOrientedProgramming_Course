package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;
public class World {

    public static void main(String[] args) {
        System.out.println("System rozpoczal dzialnie");
        List<MoveDirection> directions = new ArrayList<>();
        try {
            directions = OptionsParser.parse(args); // Parsowanie opcji
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }

        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4),new Vector2d(4,5),new Vector2d(-3,12));
//        GrassField map = new GrassField(10);
//        List<Vector2d> positionsRect = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
//        RectangularMap rectMap = new RectangularMap(6,7);
//        ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();
//        map.addObserver(consoleMapDisplay);
//        ConsoleMapDisplay rectConsoleMapDisplay = new ConsoleMapDisplay();
//        rectMap.addObserver(rectConsoleMapDisplay);]
        ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();
        List<Simulation> simList= new ArrayList<>();
        for (int i = 0; i<10;i++){
            GrassField map = new GrassField(i);
            Simulation simulation = new Simulation(positions,directions,map);
            map.addObserver(consoleMapDisplay);
            simList.add(simulation);
        }

        SimulationEngine engine = new SimulationEngine(simList);
        try{
            engine.runAsync();
        }catch (InterruptedException e){
            e.getMessage();
        }
        System.out.println("System zakonczyl dzialnie");
    }

}
