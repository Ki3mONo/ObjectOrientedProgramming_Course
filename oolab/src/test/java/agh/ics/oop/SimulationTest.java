package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationTest {
    @Test
    public void animalCorrectParserSimulation(){
        //given
        String[] arguments = {"f", "r","f", "l", "f"};
        RectangularMap map = new RectangularMap(5,5);
        List<Vector2d> positions = List.of(new Vector2d(2, 2));
        List<MoveDirection> expectedOutput = List.of(MoveDirection.FORWARD, MoveDirection.RIGHT,
                MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.FORWARD);
        //when
        List<MoveDirection> directions = new ArrayList<>();
        try{
            directions = OptionsParser.parse(arguments);
        }catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
        Simulation simulation = new Simulation(positions, directions,map);
        simulation.run();
        //then
        assertEquals(expectedOutput,directions);
    }

    @Test
    public void animalCorrectOrientationAfterSimulationWithParser(){
        //given
        RectangularMap map = new RectangularMap(5,5);
        String[] arguments = {"f", "r","f", "l", "f"};
        List<Vector2d> positions = List.of(new Vector2d(2, 2));
        List<MoveDirection> expectedOutput = List.of(MoveDirection.FORWARD, MoveDirection.RIGHT,
                MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.FORWARD);
        //when
        List<MoveDirection> directions = OptionsParser.parse(arguments);
        Simulation simulation = new Simulation(positions, directions,map);
        simulation.run();
        List<Animal> animals = simulation.getAnimals();

        //then
        assertEquals(MapDirection.NORTH, animals.get(0).getOrientation());
    }
    @Test
    public void SimulationParserInvalidArguments(){
        //given
        RectangularMap map = new RectangularMap(5,5);
        String[] arguments = {"m", "f", "l", "f"};
        List<Vector2d> positions = List.of(new Vector2d(1, 1));
        AtomicReference<List<MoveDirection>> directions = new AtomicReference<>(new ArrayList<>());
        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            directions.set(OptionsParser.parse(arguments));
        });
        Simulation simulation = new Simulation(positions, directions.get(),map);
        String expectedMessage = "m is not legal move specification";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void shouldMoveToCorrectPositionAfterSimulationWithParser(){
        //given
        RectangularMap map = new RectangularMap(5,5);
        String[] arguments = {"f", "f", "l", "f"};
        List<Vector2d> positions = List.of(new Vector2d(1, 1));
        List<MoveDirection> directions = OptionsParser.parse(arguments);
        Simulation simulation = new Simulation(positions, directions,map);

        //when
        simulation.run();
        List<Animal> animals = simulation.getAnimals();

        //then
        assertTrue(animals.get(0).isAt(new Vector2d(0, 3)));
    }

    @Test
    public void shouldNotGoOutOfMapBoundsInAllDirectionsWithParser() {
        RectangularMap map = new RectangularMap(5,5);

        String[] northMoves = {"f", "f", "f", "f", "f"};
        List<Vector2d> northPosition = List.of(new Vector2d(2, 4));
        List<MoveDirection> northDirections = OptionsParser.parse(northMoves);
        Simulation northSimulation = new Simulation(northPosition, northDirections,map);
        northSimulation.run();
        List<Animal> northAnimals = northSimulation.getAnimals();
        assertTrue(northAnimals.get(0).isAt(new Vector2d(2, 4)));

        String[] southMoves = {"b", "b", "b", "b", "b"};
        List<Vector2d> southPosition = List.of(new Vector2d(2, 0));
        List<MoveDirection> southDirections = OptionsParser.parse(southMoves);
        Simulation southSimulation = new Simulation(southPosition, southDirections,map);
        southSimulation.run();
        List<Animal> southAnimals = southSimulation.getAnimals();
        assertTrue(southAnimals.get(0).isAt(new Vector2d(2, 0)));

        String[] eastMoves = {"r", "f", "f", "f", "f", "f"};
        List<Vector2d> eastPosition = List.of(new Vector2d(4, 2));
        List<MoveDirection> eastDirections = OptionsParser.parse(eastMoves);
        Simulation eastSimulation = new Simulation(eastPosition, eastDirections,map);
        eastSimulation.run();
        List<Animal> eastAnimals = eastSimulation.getAnimals();
        assertTrue(eastAnimals.get(0).isAt(new Vector2d(4, 2)));

        String[] westMoves = {"l", "f", "f", "f", "f", "f"};
        List<Vector2d> westPosition = List.of(new Vector2d(0, 2));
        List<MoveDirection> westDirections = OptionsParser.parse(westMoves);
        Simulation westSimulation = new Simulation(westPosition, westDirections,map);
        westSimulation.run();
        List<Animal> westAnimals = westSimulation.getAnimals();
        assertTrue(westAnimals.get(0).isAt(new Vector2d(0, 2)));
    }


    @Test
    public void fullSimulationWithParserIntegrationTest(){
        //parserTest
        //given
        String[] arguments = {"b", "f", "r", "b", "r", "f", "f", "b", "l", "b", "l", "b","l"};
        List<MoveDirection> expectedOutput = List.of(MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.RIGHT,
                MoveDirection.BACKWARD,MoveDirection.RIGHT,MoveDirection.FORWARD,MoveDirection.FORWARD,MoveDirection.BACKWARD,
                MoveDirection.LEFT,MoveDirection.BACKWARD,MoveDirection.LEFT,MoveDirection.BACKWARD, MoveDirection.LEFT);
        //when
        List<MoveDirection> directions=OptionsParser.parse(arguments);
        //then
        assertEquals(expectedOutput,directions);

        //simulation of movingAnimals test
        //given
            //previous directions and
        RectangularMap map = new RectangularMap(5, 5);
        List<Vector2d> positions= List.of(new Vector2d(2,2), new Vector2d(1,3),new Vector2d(4,3));
        Simulation simulation = new Simulation(positions,directions,map);
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
