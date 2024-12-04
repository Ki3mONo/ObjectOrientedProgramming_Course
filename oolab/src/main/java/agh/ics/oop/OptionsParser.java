package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("No moves specified");
        }
        ArrayList<MoveDirection> moves = new ArrayList<>();
        for (String arg : args) {
            MoveDirection move = switch (arg) {
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "l" -> MoveDirection.LEFT;
                case "r" -> MoveDirection.RIGHT;
                default -> throw new IllegalArgumentException(arg + " is not legal move specification");
            };
            moves.add(move);

        }
        return moves;
    }
}
