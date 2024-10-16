package agh.ics.oop;

import agh.ics.oop.model.*;
public class World {
    public static void run(MoveDirection[] moves){
        for (MoveDirection move : moves){
            String message = switch(move){
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD -> "Zwierzak idzie do tylu";
                case RIGHT -> "Zwierzak idzie w prawo";
                case LEFT -> "Zwierzak idzie w lewo";
            };
            System.out.println(message);
        }
    }
    public static void main(String[] args) {
        System.out.println("System wystartowal");
        run(OptionsParser.Parse(args));
        Vector2d position1 = new Vector2d(1,2);
        System.out.println("System zakonczyl dzialanie");
    }
}
