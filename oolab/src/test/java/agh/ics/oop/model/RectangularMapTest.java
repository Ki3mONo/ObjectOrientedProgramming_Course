package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import agh.ics.oop.model.util.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {

    @Test
    public void testAnimalPlacement() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(3, 2));

        assertTrue(map.place(animal1));
        assertEquals(animal1,map.objectAt(new Vector2d(3,2)));
        assertTrue(map.isOccupied(new Vector2d(3,2)));

    }
    @Test
    public void testGetAnimals() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(3, 2));

        map.place(animal1);
        Map<Vector2d, Animal> animalsMap=map.getAnimals();

        assertTrue(animalsMap.containsValue(animal1));

    }
    @Test
    public void testAnimalPlacementOutOfBounds() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(5, 5));

        assertFalse(map.place(animal1));

        assertFalse(map.isOccupied(new Vector2d(5,5)));

    }
    @Test
    public void testAnimalPlacementAtOccupiedPosition() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 2));

        assertTrue(map.place(animal1));

        assertTrue(map.isOccupied(new Vector2d(2,2)));
        assertFalse(map.place(animal2));
    }

    @Test
    public void testAnimalMovementWithinBounds() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(new Vector2d(2, 2));
        map.place(animal);

        // Ruchy w obrębie granic
        map.move(animal, MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 3), animal.getPosition());
        assertTrue(animal.isAt(new Vector2d(2,3)));
        assertTrue(map.isOccupied(new Vector2d(2,3)));

        map.move(animal, MoveDirection.RIGHT);
        map.move(animal, MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(3,3)));
        assertTrue(map.isOccupied(new Vector2d(3,3)));
        assertFalse(map.isOccupied(new Vector2d(2,3)));
    }

    @Test
    public void testAnimalMovementOutOfBounds() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(new Vector2d(0, 0));
        map.place(animal);

        map.move(animal, MoveDirection.BACKWARD);
        assertTrue(animal.isAt(new Vector2d(0,0)));
        assertTrue(map.isOccupied(new Vector2d(0, 0)));


        map.move(animal, MoveDirection.LEFT);
        map.move(animal, MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(0,0)));
        assertTrue(map.isOccupied(new Vector2d(0, 0)));
    }
    @Test
    public void testAnimalMovementAtOccupiedPosition() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(2,2));
        map.place(animal1);
        Animal animal2 = new Animal(new Vector2d(2,1));
        map.place(animal2);

        map.move(animal2, MoveDirection.FORWARD);
        assertTrue(animal1.isAt(new Vector2d(2,2)));
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertTrue(animal2.isAt(new Vector2d(2,1)));
        assertTrue(map.isOccupied(new Vector2d(2, 1)));
        assertFalse(animal2.isAt(new Vector2d(2,2)));
    }

    @Test
    public void testToString(){
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(new Vector2d(0, 0));
        map.place(animal);

        map.move(animal, MoveDirection.BACKWARD);
        assertTrue(animal.isAt(new Vector2d(0,0)));
        assertTrue(map.isOccupied(new Vector2d(0, 0)));


        map.move(animal, MoveDirection.LEFT);
        map.move(animal, MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(0,0)));
        assertTrue(map.isOccupied(new Vector2d(0, 0)));

        String toStringExpected=
                " y\\x  0 1 2 3 4\r\n  5: -----------\r\n  4: | | | | | |\r\n  3: | | | | | |\r\n  2: | | | | | |\r\n  1: | | | | | |\r\n  0: |W| | | | |\r\n -1: -----------\r\n";

        assertEquals(toStringExpected,map.toString());
    }

}
