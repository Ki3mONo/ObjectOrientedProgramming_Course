package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;

public class OptionsParser {
    public static MoveDirection[] Parse(String[] args) {
        /*tutaj można podejściem jak z C ale uznałem że (chyba?) wolno im użyć ArrayListy
        int validCount = 0;
        for (String arg : args) {
            switch (arg) {
                case "f", "b", "l", "r" -> validCount++;
                default -> {

                }
            }
        }
        MoveDirection[] moves = new MoveDirection[validCount];

        int index = 0;
        for (String arg : args) {
            MoveDirection move = switch (arg) {
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "l" -> MoveDirection.LEFT;
                case "r" -> MoveDirection.RIGHT;
                default -> null;
            };

            if (move != null) {
                moves[index] = move;
                index++;
            }
        }
         */
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
