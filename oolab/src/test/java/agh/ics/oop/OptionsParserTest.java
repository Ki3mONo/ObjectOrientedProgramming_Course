package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class OptionsParserTest {
    @Test
    public void testParseValidInput() {
        //given
        String[] input = {"f", "b", "l", "r", "f"};
        List<MoveDirection> expectedOutput = Arrays.asList(
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.LEFT,
                MoveDirection.RIGHT,
                MoveDirection.FORWARD
        );

        //when
        List<MoveDirection> result = OptionsParser.parse(input);

        //when
        assertEquals(expectedOutput, result);
    }

    @Test
    public void testParseMixedInput() {
        //given
        String[] input = {"f", "i", "r", "m", "k"};
        List<MoveDirection> expectedOutput = Arrays.asList(
                MoveDirection.FORWARD,
                MoveDirection.RIGHT
        );

        //when
        List<MoveDirection> result = OptionsParser.parse(input);

        //then
        assertEquals(expectedOutput, result);
    }

    @Test
    public void testParseInvalidInput() {
        //given
        String[] input = {"i", "m", "k"};
        List<MoveDirection> expectedOutput = List.of();

        //when
        List<MoveDirection> result = OptionsParser.parse(input);

        //then
        assertEquals(expectedOutput, result);
    }

    @Test
    public void testParseEmptyInput() {
        //given
        String[] input = {};
        List<MoveDirection> expectedOutput = List.of();

        //when
        List<MoveDirection> result = OptionsParser.parse(input);

        //then
        assertEquals(expectedOutput, result);
    }
}
