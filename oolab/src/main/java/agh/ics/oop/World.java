package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {
    public static void run(MoveDirection[] moves){
        /*wcześniej podpunkt 8: run()
           System.out.println("Zwierzak idzie do przodu");
        */
        /*wcześniej podpunkt 11:run(String[] args)
            int i;
            for (i=0; i<args.length-1; i++){
                 System.out.print(args[i] + ", ");
            }
            System.out.println(args[i]);
         */
          /*  for (String arg : args){
                String result = switch (arg) {
                case "f" -> "Zwierzak idzie do przodu";
                case "b" -> "Zwierzak idzie do tyłu";
                case "r" -> "Zwierzak skręca w prawo";
                case "l" -> "Zwierzak skręca w lewo";
                default -> null;
                //ewentualnie: default -> "Ignorowany znak: " + arg; i wtedy bez if'a
            };
                if (result!=null){
                    System.out.println(result);
                }
            }
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
        //wcześniej podpunkt 8 : run()
        //wcześniej podpunkt 11 : run(args)
        run(moves);
        System.out.println("System zakończył działanie");
    }
}
