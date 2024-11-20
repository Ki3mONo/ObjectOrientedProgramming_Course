package agh.ics.oop;

import agh.ics.oop.model.*;
import java.util.List;
public class World {
//    public static void run(MoveDirection[] moves){
//        for (MoveDirection move : moves){
//            String message = switch(move){
//                case FORWARD -> "Zwierzak idzie do przodu";
//                case BACKWARD -> "Zwierzak idzie do tylu";
//                case RIGHT -> "Zwierzak idzie w prawo";
//                case LEFT -> "Zwierzak idzie w lewo";
//            };
//            System.out.println(message);
//        }
//    }
    public static void main(String[] args) {
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        RectangularMap rectMap = new RectangularMap(5,5);
        Simulation<Animal,Vector2d> simulation1 = new Simulation<>(positions, directions, rectMap);
        simulation1.run();

        WorldMap textMap = new TextMap();
        List<String> textPositions =List.of("Za","te","walke","nalezy","sie","srebrna","skrzynka",":)");
        Simulation<String,Integer> simulation2 = new Simulation<>(textPositions,directions,textMap);
        simulation2.run();

    }
}
