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
//        System.out.println("System wystartowal");
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        RectangularMap map = new RectangularMap(5,5);
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
//        System.out.println("System zakonczyl dzialanie");
    }
}
