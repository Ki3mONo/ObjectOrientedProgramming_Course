package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {
    public static void run(MoveDirection[] moves){
        /*
         






        */
        for (MoveDirection move : moves){
            String message = switch(move){
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD -> "Zwierzak idzie do tyłu";
                case RIGHT -> "Zwierzak idzie w prawo";
                case LEFT -> "Zwierzak idzie w lewo";
            };
            System.out.println(message);
        }
    }
    public static void main(String[] args) {
        System.out.println("System wystartował");
        MoveDirection[] moves = OptionsParser.Parse(args);
        run(moves);
        System.out.println("System zakończył działanie");
    }
}
