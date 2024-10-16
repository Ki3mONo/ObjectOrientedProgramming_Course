package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;

public class OptionsParser {
    public static MoveDirection[] Parse(String[] args) {
        ArrayList<MoveDirection> moves = new ArrayList<>();
        for (String arg : args) {
            MoveDirection move = switch (arg) {
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "l" -> MoveDirection.LEFT;
                case "r" -> MoveDirection.RIGHT;
                default -> null;
            };
            if (move != null) {
                moves.add(move);
            }
        }
        return moves.toArray(new MoveDirection[0]);
    }
}
