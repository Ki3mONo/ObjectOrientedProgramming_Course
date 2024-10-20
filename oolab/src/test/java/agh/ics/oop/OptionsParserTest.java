package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class OptionsParserTest {
    @Test
    public void testParseValidInput() {
        //given
        String[] input = {"f", "b", "l", "r", "f"};
        MoveDirection[] expectedOutput = {
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.LEFT,
                MoveDirection.RIGHT,
                MoveDirection.FORWARD
        };

        //when
        MoveDirection[] result = OptionsParser.parse(input);

        //when
        assertArrayEquals(expectedOutput, result);
    }

    @Test
    public void testParseMixedInput() {
        //given
        String[] input = {"f", "i", "r", "m", "k"};
        MoveDirection[] expectedOutput = {
                MoveDirection.FORWARD,
                MoveDirection.RIGHT
        };

        //when
        MoveDirection[] result = OptionsParser.parse(input);

        //then
        assertArrayEquals(expectedOutput, result);
    }

    @Test
    public void testParseInvalidInput() {
        //given
        String[] input = {"i", "m", "k"};
        MoveDirection[] expectedOutput = {};

        //when
        MoveDirection[] result = OptionsParser.parse(input);

        //then
        assertArrayEquals(expectedOutput, result);
    }

    @Test
    public void testParseEmptyInput() {
        //given
        String[] input = {};
        MoveDirection[] expectedOutput = {};

        //when
        MoveDirection[] result = OptionsParser.parse(input);

        //then
        assertArrayEquals(expectedOutput, result);
    }
}
