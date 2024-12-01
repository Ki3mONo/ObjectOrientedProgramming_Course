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

        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4),new Vector2d(4,5),new Vector2d(3,12));
//        GrassField grassMap = new GrassField(10);
//        List<Vector2d> positionsRect = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
//        RectangularMap rectMap = new RectangularMap(6,7);
//        ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();
//        grassMap.addObserver(consoleMapDisplay);
//        rectMap.addObserver(consoleMapDisplay);
//        Simulation grassFieldSimulation = new Simulation(positions,directions,grassMap);
//        Simulation rectSimulation = new Simulation(positionsRect,directions,rectMap);
//        SimulationEngine engine = new SimulationEngine(List.of(grassFieldSimulation,rectSimulation));
//        engine.runSync();
//        System.out.println();
//        engine.runAsync();
//        try {
//            engine.awaitSimulationsEnd();
//        }catch(InterruptedException e){
//            System.out.println(e.getMessage());
//        }
//        System.out.println("System zakonczyl dzialanie.");



        ConsoleMapDisplay consoleMapDisplayThousandSim = new ConsoleMapDisplay();
        List<Simulation> simList= new ArrayList<>();
        for (int i = 0; i<1000;i++){
            if(i%2==0){
                GrassField map = new GrassField(25);
                Simulation simulation = new Simulation(positions,directions,map);
                map.addObserver(consoleMapDisplayThousandSim);
                simList.add(simulation);
            }
            else {
                RectangularMap map = new RectangularMap(14,15);
                Simulation simulation = new Simulation(positions,directions,map);
                map.addObserver(consoleMapDisplayThousandSim);
                simList.add(simulation);
            }
        }

        SimulationEngine engineThousandSim = new SimulationEngine(simList);
//        engineThousandSim.runAsync();
        engineThousandSim.runAsyncInThreadPool();
        try{
            engineThousandSim.awaitSimulationsEnd();;
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        System.out.println("System zakonczyl dzialnie");
    }

}
