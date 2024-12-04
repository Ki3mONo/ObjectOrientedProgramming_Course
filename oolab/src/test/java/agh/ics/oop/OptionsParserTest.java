package agh.ics.oop;

import agh.ics.oop.model.IncorrectPositionException;
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


        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            List<MoveDirection> result = OptionsParser.parse(input);
        });

        String expectedMessage = "i is not legal move specification";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void testParseInvalidInput() {
        //given
        String[] input = {"i", "m", "k"};
        List<MoveDirection> expectedOutput = List.of();

        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            List<MoveDirection> result = OptionsParser.parse(input);
        });

        String expectedMessage = "i is not legal move specification";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage,actualMessage);

    }

    @Test
    public void testParseEmptyInput() {
        //given
        String[] input = {};
        List<MoveDirection> expectedOutput = List.of();

        //when
        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            List<MoveDirection> result = OptionsParser.parse(input);
        });

        String expectedMessage = "No moves specified";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage,actualMessage);
    }
}
