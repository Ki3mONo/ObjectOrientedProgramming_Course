package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {
    @Test
    public void toStringInitTest(){
        //given
        Animal animal = new Animal(1,2);
        //when
        String string = animal.toString();
        //then
        assertEquals("{(1,2), Polnoc}",string);
    }
    @Test
    public void initWithoutPositionTest(){
        //given
        Animal animal = new Animal();
        //when
        MapDirection orientation = animal.getOrientation();
        Vector2d position = animal.getPosition();
        //then
        assertEquals(new Vector2d(2,2), position);
        assertEquals(MapDirection.NORTH, orientation);
    }
    @Test
    public void isAtTestTrue(){
        //given
        Animal animal = new Animal(1,3);
        Vector2d expectedPosition = new Vector2d(1,3);
        //when
        //then
        assertTrue(animal.isAt(expectedPosition));
    }
    @Test
    public void isAtTestFalse(){
        //given
        Animal animal = new Animal(1,3);
        Vector2d expectedPosition = new Vector2d(2,2);
        //when
        //then
        assertFalse(animal.isAt(expectedPosition));
    }
    @Test
    public void changeOrientationTest(){
        //given
        Animal animal = new Animal(1,2);
        //when
        animal.move(MoveDirection.RIGHT);
        //then
        assertEquals(MapDirection.EAST,animal.getOrientation());
    }
    @Test
    public void toStringChangeDirTest(){
        //given
        Animal animal = new Animal(1,2);
        //when
        animal.move(MoveDirection.LEFT);
        String string = animal.toString();
        //then
        assertEquals("{(1,2), Zachod}",string);
    }
    @Test
    public void testMove(){
        //given
        Animal animal = new Animal(new Vector2d(2,1));
        //when
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        //then
        assertTrue(animal.isAt(new Vector2d(1,2)));
        assertFalse(animal.isAt(new Vector2d(2,2)));
        //when
        animal.move(MoveDirection.FORWARD);
        //then
        assertTrue(animal.isAt(new Vector2d(0,2)));
        //when
        animal.move(MoveDirection.FORWARD);
        //then
        assertFalse(animal.isAt(new Vector2d(-1,2)));
        assertTrue(animal.isAt(new Vector2d(0,2)));
        //when
        animal.move(MoveDirection.RIGHT);
        //then
        assertEquals(animal.getOrientation(),MapDirection.NORTH);
        //when
        animal.move(MoveDirection.BACKWARD);
        //then
        assertTrue(animal.isAt(new Vector2d(0,1)));
        //when
        animal.move(MoveDirection.LEFT);
        //then
        assertEquals(animal.getOrientation(),MapDirection.WEST);
        //when
        animal.move(MoveDirection.BACKWARD);
        //then
        assertTrue(animal.isAt(new Vector2d(1,1)));

    }

}
