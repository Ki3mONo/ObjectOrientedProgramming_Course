package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import agh.ics.oop.model.util.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {

    @Test
    public void testAnimalPlacement() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(3, 2));

        try{
            map.place(animal1);
        }catch (IncorrectPositionException e){
            System.err.println(e.getMessage());
        }
        Optional<WorldElement> animal1element = map.objectAt(new Vector2d(3,2));
        animal1element.ifPresent(e -> assertEquals(animal1, e));
        assertTrue(map.isOccupied(new Vector2d(3,2)));

    }
    @Test
    public void testGetAnimals() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(3, 2));

        try{
            map.place(animal1);
        }catch (IncorrectPositionException e){
            System.err.println(e.getMessage());
        }
        Map<Vector2d, Animal> animalsMap=map.getAnimals();

        assertTrue(animalsMap.containsValue(animal1));

    }
    @Test
    public void testAnimalPlacementOutOfBounds() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(5, 5));

        Exception exception = assertThrows(IncorrectPositionException.class, ()->{
            map.place(animal1);
        });

        String expectedMessage = "Position " + animal1.getPosition() + " is not correct";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage,actualMessage);

    }
    @Test
    public void testAnimalPlacementAtOccupiedPosition() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 2));

        try{
            map.place(animal1);
        }catch (IncorrectPositionException e){
            System.err.println(e.getMessage());
        }

        assertTrue(map.isOccupied(new Vector2d(2,2)));
        Exception exception = assertThrows(IncorrectPositionException.class, ()->{
            map.place(animal2);
        });

        String expectedMessage = "Position " + animal1.getPosition() + " is not correct";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void testAnimalMovementWithinBounds() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(new Vector2d(2, 2));
        try{
            map.place(animal);
        }catch (IncorrectPositionException e){
            System.err.println(e.getMessage());
        }

        try{
            map.move(animal, MoveDirection.FORWARD);
        }catch (IncorrectPositionException e){
            System.err.println(e.getMessage());
        }
        assertEquals(new Vector2d(2, 3), animal.getPosition());
        assertTrue(animal.isAt(new Vector2d(2,3)));
        assertTrue(map.isOccupied(new Vector2d(2,3)));
        try{
            map.move(animal, MoveDirection.RIGHT);
            map.move(animal, MoveDirection.FORWARD);
        }catch (IncorrectPositionException e){
            System.err.println(e.getMessage());
        }
        assertTrue(animal.isAt(new Vector2d(3,3)));
        assertTrue(map.isOccupied(new Vector2d(3,3)));
        assertFalse(map.isOccupied(new Vector2d(2,3)));
    }

    @Test
    public void testAnimalMovementOutOfBounds() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(new Vector2d(0, 0));
        try{
            map.place(animal);
        }catch (IncorrectPositionException e){
            System.err.println(e.getMessage());
        }


        try{
            map.move(animal, MoveDirection.BACKWARD);
        }catch (IncorrectPositionException e){
            System.err.println(e.getMessage());
        }
        assertTrue(animal.isAt(new Vector2d(0,0)));
        assertTrue(map.isOccupied(new Vector2d(0, 0)));

        try{
            map.move(animal, MoveDirection.LEFT);
            map.move(animal, MoveDirection.FORWARD);
        }catch (IncorrectPositionException e){
            System.err.println(e.getMessage());
        }

        assertTrue(animal.isAt(new Vector2d(0,0)));
        assertTrue(map.isOccupied(new Vector2d(0, 0)));
    }
    @Test
    public void testAnimalMovementAtOccupiedPosition() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(2,2));
        try{
            map.place(animal1);
        }catch (IncorrectPositionException e){
            System.err.println(e.getMessage());
        }

        Animal animal2 = new Animal(new Vector2d(2,1));
        try{
            map.place(animal2);
        }catch (IncorrectPositionException e){
            System.err.println(e.getMessage());
        }
        try{
            map.move(animal2, MoveDirection.FORWARD);
        }catch (IncorrectPositionException e){
            System.err.println(e.getMessage());
        }

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
        try{
            map.place(animal);
        }catch (IncorrectPositionException e){
            System.err.println(e.getMessage());
        }

        try{
            map.move(animal, MoveDirection.BACKWARD);
        }catch (IncorrectPositionException e){
            System.err.println(e.getMessage());
        }

        assertTrue(animal.isAt(new Vector2d(0,0)));
        assertTrue(map.isOccupied(new Vector2d(0, 0)));

        try{
            map.move(animal, MoveDirection.LEFT);
            map.move(animal, MoveDirection.FORWARD);
        }catch (IncorrectPositionException e){
            System.err.println(e.getMessage());
        }


        assertTrue(animal.isAt(new Vector2d(0,0)));
        assertTrue(map.isOccupied(new Vector2d(0, 0)));

        String toStringExpected=
                " y\\x  0 1 2 3 4\r\n  5: -----------\r\n  4: | | | | | |\r\n  3: | | | | | |\r\n  2: | | | | | |\r\n  1: | | | | | |\r\n  0: |W| | | | |\r\n -1: -----------\r\n";

        assertEquals(toStringExpected,map.toString());
    }

    @Test
    public void testGetOrderedAnimals() {
        Animal animal1 = new Animal(new Vector2d(2, 3));
        Animal animal2 = new Animal(new Vector2d(1, 1));
        Animal animal3 = new Animal(new Vector2d(2, 2));
        Animal animal4 = new Animal(new Vector2d(1, 3));
        WorldMap map = new RectangularMap(10,10);
        try {
            map.place(animal1);
            map.place(animal2);
            map.place(animal3);
            map.place(animal4);
        } catch (IncorrectPositionException e) {
            System.err.println(e.getMessage());
        }

        List<Animal> orderedAnimals = map.getOrderedAnimals();

        assertEquals(animal2, orderedAnimals.get(0));
        assertEquals(animal4, orderedAnimals.get(1));
        assertEquals(animal3, orderedAnimals.get(2));
        assertEquals(animal1, orderedAnimals.get(3));
    }

}
