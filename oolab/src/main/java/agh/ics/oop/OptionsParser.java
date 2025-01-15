package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("No moves specified");
        }
        return Stream.of(args)
                .map(arg -> switch (arg) {
                    case "f" -> MoveDirection.FORWARD;
                    case "b" -> MoveDirection.BACKWARD;
                    case "l" -> MoveDirection.LEFT;
                    case "r" -> MoveDirection.RIGHT;
                    default -> throw new IllegalArgumentException(arg + " is not legal move specification");
                })
                .collect(Collectors.toList());
    }
}
