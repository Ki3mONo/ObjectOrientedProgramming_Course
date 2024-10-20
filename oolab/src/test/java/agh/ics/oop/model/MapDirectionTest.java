package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapDirectionTest {
    @Test
    public void testToStringNorth(){
        //given
        MapDirection direction = MapDirection.NORTH;
        String expectedResult = "Polnoc";
        //when
        String result = direction.toString();
        //then
        assertEquals(expectedResult, result);
    }
    @Test
    public void testToStringSouth(){
        //given
        MapDirection direction = MapDirection.SOUTH;
        String expectedResult = "Poludnie";
        //when
        String result = direction.toString();
        //then
        assertEquals(expectedResult, result);
    }
    @Test
    public void testToStringEast(){
        //given
        MapDirection direction = MapDirection.EAST;
        String expectedResult = "Wschod";
        //when
        String result = direction.toString();
        //then
        assertEquals(expectedResult, result);
    }
    @Test
    public void testToStringWest(){
        //given
        MapDirection direction = MapDirection.WEST;
        String expectedResult = "Zachod";
        //when
        String result = direction.toString();
        //then
        assertEquals(expectedResult, result);
    }
    @Test
    public void testNextSouth(){
        //given
        MapDirection direction = MapDirection.SOUTH;
        MapDirection expectedResult = MapDirection.WEST;
        //when
        MapDirection nextDirection = direction.next();
        //then
        assertEquals(expectedResult, nextDirection);
    }
    @Test
    public void testNextNorth(){
        //given
        MapDirection direction = MapDirection.NORTH;
        MapDirection expectedResult = MapDirection.EAST;
        //when
        MapDirection nextDirection = direction.next();
        //then
        assertEquals(expectedResult, nextDirection);
    }
    @Test
    public void testNextWest(){
        //given
        MapDirection direction = MapDirection.WEST;
        MapDirection expectedResult = MapDirection.NORTH;
        //when
        MapDirection nextDirection = direction.next();
        //then
        assertEquals(expectedResult, nextDirection);
    }
    @Test
    public void testNextEast(){
        //given
        MapDirection direction = MapDirection.EAST;
        MapDirection expectedResult = MapDirection.SOUTH;
        //when
        MapDirection nextDirection = direction.next();
        //then
        assertEquals(expectedResult, nextDirection);
    }
    @Test
    public void testPrevSouth(){
        //given
        MapDirection direction = MapDirection.SOUTH;
        MapDirection expectedResult = MapDirection.EAST;
        //when
        MapDirection previousDirection = direction.previous();
        //then
        assertEquals(expectedResult, previousDirection);
    }
    @Test
    public void testPrevNorth(){
        //given
        MapDirection direction = MapDirection.NORTH;
        MapDirection expectedResult = MapDirection.WEST;
        //when
        MapDirection previousDirection = direction.previous();
        //then
        assertEquals(expectedResult, previousDirection);
    }
    @Test
    public void testPrevWest(){
        //given
        MapDirection direction = MapDirection.WEST;
        MapDirection expectedResult = MapDirection.SOUTH;
        //when
        MapDirection previousDirection = direction.previous();
        //then
        assertEquals(expectedResult, previousDirection);
    }
    @Test
    public void testPrevEast(){
        //given
        MapDirection direction = MapDirection.EAST;
        MapDirection expectedResult = MapDirection.NORTH;
        //when
        MapDirection previousDirection = direction.previous();
        //then
        assertEquals(expectedResult, previousDirection);
    }
    @Test
    public void testUnitVectorNorth(){
        //given
        MapDirection direction = MapDirection.NORTH;
        Vector2d expectedResult = new Vector2d(0,1);;
        //when
        Vector2d result = direction.toUnitVector();
        //then
        assertEquals(expectedResult, result);
    }
    @Test
    public void testUnitVectorSouth(){
        //given
        MapDirection direction = MapDirection.SOUTH;
        Vector2d expectedResult = new Vector2d(0,-1);;
        //when
        Vector2d result = direction.toUnitVector();
        //then
        assertEquals(expectedResult, result);
    }
    @Test
    public void testUnitVectorEast(){
        //given
        MapDirection direction = MapDirection.EAST;
        Vector2d expectedResult = new Vector2d(1,0);;
        //when
        Vector2d result = direction.toUnitVector();
        //then
        assertEquals(expectedResult, result);
    }
    @Test
    public void testUnitVectorWest(){
        //given
        MapDirection direction = MapDirection.WEST;
        Vector2d expectedResult = new Vector2d(-1,0);;
        //when
        Vector2d result = direction.toUnitVector();
        //then
        assertEquals(expectedResult, result);
    }

}
