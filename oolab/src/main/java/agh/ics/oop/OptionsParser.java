package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class OptionsParser {
    public static MoveDirection[] Parse(String[] args){
        MoveDirection[] moves = new MoveDirection[args.length];
        MoveDirection move;
        for(int i=0; i< args.length; i++) {
            move = switch (args[i]){
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "l" -> MoveDirection.LEFT;
                case "r" -> MoveDirection.RIGHT;
                default -> null;
            };

            moves[i]=move;
        }
        return moves;
    }
}
