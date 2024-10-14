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
          /*wcześniej podpunkt 14:run(String[] args)
           for (String arg : args){
                String result = switch (arg) {
                case "f" -> "Zwierzak idzie do przodu";
                case "b" -> "Zwierzak idzie do tyłu";
                case "r" -> "Zwierzak skręca w prawo";
                case "l" -> "Zwierzak skręca w lewo";
                default -> null;
            };
                if (result!=null){
                    System.out.println(result);
                }
            }
           */
        for (MoveDirection move : moves){
            String message = switch(move){
                //usunięcie polskich znaków, bo przy odpalaniu programu z konsoli (gradlew.bat) dzieją się problemy "�" z polskimi "Ł" i "Ń", przy odpaleniu w IntelliJ problem nie występuje
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
        // można osobno wczytać moves albo od razu wywołać run(OptionsParser.Parse(args))
        //MoveDirection[] moves = OptionsParser.Parse(args);
        // wcześniej podpunkt 8 : run()
        // wcześniej podpunkt 11 i 14 : run(args)
        run(OptionsParser.Parse(args));
        System.out.println("System zakonczyl dzialanie");
    }
}
