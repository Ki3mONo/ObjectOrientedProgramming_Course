package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationTest {
    @Test
    public void animalCorrectParserSimulation(){
        //given
        String[] arguments = {"f", "r","u","f","asd", "l", "m", "f"};
        RectangularMap map = new RectangularMap(5,5);
        List<Vector2d> positions = List.of(new Vector2d(2, 2));
        List<MoveDirection> expectedOutput = List.of(MoveDirection.FORWARD, MoveDirection.RIGHT,
                MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.FORWARD);
        //when
        List<MoveDirection> directions = OptionsParser.parse(arguments);
        Simulation simulation = new Simulation(positions, directions,map);
        simulation.run();
        //then
        assertEquals(expectedOutput,directions);
    }

    @Test
    public void animalCorrectOrientationAfterSimulationWithParser(){
        //given
        RectangularMap map = new RectangularMap(5,5);
        String[] arguments = {"f", "r","u","f","asd", "l", "m", "f"};
        List<Vector2d> positions = List.of(new Vector2d(2, 2));
        List<MoveDirection> expectedOutput = List.of(MoveDirection.FORWARD, MoveDirection.RIGHT,
                MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.FORWARD);
        //when
        List<MoveDirection> directions = OptionsParser.parse(arguments);
        Simulation simulation = new Simulation(positions, directions,map);
        simulation.run();
        List<Animal> animals = simulation.getElements();

        //then
        assertEquals(MapDirection.NORTH, animals.get(0).getOrientation());
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
        List<Animal> animals = simulation.getElements();

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
        List<Animal> northAnimals = northSimulation.getElements();
        assertTrue(northAnimals.get(0).isAt(new Vector2d(2, 4)));

        String[] southMoves = {"b", "b", "b", "b", "b"};
        List<Vector2d> southPosition = List.of(new Vector2d(2, 0));
        List<MoveDirection> southDirections = OptionsParser.parse(southMoves);
        Simulation southSimulation = new Simulation(southPosition, southDirections,map);
        southSimulation.run();
        List<Animal> southAnimals = southSimulation.getElements();
        assertTrue(southAnimals.get(0).isAt(new Vector2d(2, 0)));

        String[] eastMoves = {"r", "f", "f", "f", "f", "f"};
        List<Vector2d> eastPosition = List.of(new Vector2d(4, 2));
        List<MoveDirection> eastDirections = OptionsParser.parse(eastMoves);
        Simulation eastSimulation = new Simulation(eastPosition, eastDirections,map);
        eastSimulation.run();
        List<Animal> eastAnimals = eastSimulation.getElements();
        assertTrue(eastAnimals.get(0).isAt(new Vector2d(4, 2)));

        String[] westMoves = {"l", "f", "f", "f", "f", "f"};
        List<Vector2d> westPosition = List.of(new Vector2d(0, 2));
        List<MoveDirection> westDirections = OptionsParser.parse(westMoves);
        Simulation westSimulation = new Simulation(westPosition, westDirections,map);
        westSimulation.run();
        List<Animal> westAnimals = westSimulation.getElements();
        assertTrue(westAnimals.get(0).isAt(new Vector2d(0, 2)));
    }


    @Test
    public void fullSimulationWithParserIntegrationTest(){
        //parserTest
        //given
        String[] arguments = {"b", "f", "r", "t", "b", "r", "f", "m","f", "b", "l", "b", "l", "b","l"};
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
        List<Animal> animals = simulation.getElements();
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

    @Test
    public void simulationTestWithTextMap(){
        String[] arguments = {"b", "f", "r", "t", "b", "r", "m","r"};
        List<MoveDirection> expectedOutput = List.of(MoveDirection.BACKWARD,
                MoveDirection.FORWARD, MoveDirection.RIGHT,
                MoveDirection.BACKWARD,MoveDirection.RIGHT,
                MoveDirection.RIGHT);
        //when
        List<MoveDirection> directions=OptionsParser.parse(arguments);
        //then
        assertEquals(expectedOutput,directions);
        TextMap map = new TextMap();
        List<String> words= List.of("Ala","ma","kota","i","psa");
        Simulation simulation = new Simulation(words,directions,map);
        //when
        List<String > listOfWords = simulation.getElements();
        simulation.run();
        //then
        //ala
        assertEquals(listOfWords.get(0),map.objectAt(1));
        //ma
        assertEquals(listOfWords.get(1),map.objectAt(0));
        //kota
        assertEquals(listOfWords.get(2),map.objectAt(3));
        //i
        assertEquals(listOfWords.get(3),map.objectAt(2));
        //psa
        assertEquals(listOfWords.get(4),map.objectAt(4));
    }

}
