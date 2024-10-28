package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationTest {
    @Test
    public void animalCorrectParserAndOrientationAfterSimulation(){
        //given
        String[] arguments = {"f", "r", "f", "l", "m", "f"};
        List<Vector2d> positions = List.of(new Vector2d(2, 2));
        List<MoveDirection> expectedOutput = List.of(MoveDirection.FORWARD, MoveDirection.RIGHT,
                MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.FORWARD);
        //when
        List<MoveDirection> directions = OptionsParser.parse(arguments);
        //then
        assertEquals(expectedOutput,directions);
        Simulation simulation = new Simulation(positions, directions);

        //when
        simulation.run();
        List<Animal> animals = simulation.getAnimals();

        //then
        assertEquals(MapDirection.NORTH, animals.get(0).getOrientation());
    }

    @Test
    public void shouldMoveToCorrectPositionAfterSimulation(){
        //given
        String[] arguments = {"f", "f", "l", "f"};
        List<Vector2d> positions = List.of(new Vector2d(1, 1));
        List<MoveDirection> directions = OptionsParser.parse(arguments);
        Simulation simulation = new Simulation(positions, directions);

        //when
        simulation.run();
        List<Animal> animals = simulation.getAnimals();

        //then
        assertTrue(animals.get(0).isAt(new Vector2d(0, 3)));
    }

    @Test
    public void shouldNotGoOutOfMapBounds(){
        //given
        String[] arguments = {"f", "f", "f", "f", "f", "f"};
        List<Vector2d> positions = List.of(new Vector2d(0, 0));
        List<MoveDirection> directions = OptionsParser.parse(arguments);
        Simulation simulation = new Simulation(positions, directions);

        //when
        simulation.run();
        List<Animal> animals = simulation.getAnimals();

        //then
        assertTrue(animals.get(0).isAt(new Vector2d(0, 4)));
    }

    @Test
    public void fullSimulationIntegrationTest(){
        //given
        String[] arguments = {"b", "f", "r", "t", "b", "r", "f", "m","f", "b", "l", "b", "l", "b","l"};
        List<MoveDirection> expectedOutput = List.of(MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.RIGHT,
                MoveDirection.BACKWARD,MoveDirection.RIGHT,MoveDirection.FORWARD,MoveDirection.FORWARD,MoveDirection.BACKWARD,
                MoveDirection.LEFT,MoveDirection.BACKWARD,MoveDirection.LEFT,MoveDirection.BACKWARD, MoveDirection.LEFT);
        //when
        List<MoveDirection> directions=OptionsParser.parse(arguments);
        //then
        assertEquals(expectedOutput,directions);


        //given
            //previous directions and
        List<Vector2d> positions= List.of(new Vector2d(2,2), new Vector2d(1,3),new Vector2d(4,3));
        Simulation simulation = new Simulation(positions,directions);
        //when
        List<Animal> animals = simulation.getAnimals();
        simulation.run();
        //then
        assertEquals(MapDirection.WEST, animals.get(0).getOrientation());
        assertEquals(MapDirection.NORTH, animals.get(1).getOrientation());
        assertEquals(MapDirection.NORTH, animals.get(2).getOrientation());
        assertTrue(animals.get(0).isAt(new Vector2d(2,0)));
        assertFalse(animals.get(0).isAt(new Vector2d(0,2)));
        assertFalse(animals.get(0).isAt(new Vector2d(0,4)));
        assertTrue(animals.get(1).isAt(new Vector2d(0,4)));
        assertFalse(animals.get(1).isAt(new Vector2d(4,0)));
        assertFalse(animals.get(1).isAt(new Vector2d(4,2)));
        assertEquals(animals.get(2).getPosition(),new Vector2d(4,2));
        assertTrue(animals.get(2).isAt(new Vector2d(4,2)));
        assertFalse(animals.get(2).isAt(new Vector2d(2,0)));
    }

}
