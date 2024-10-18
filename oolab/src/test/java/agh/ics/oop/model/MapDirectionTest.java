package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDirectionTest {
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
}

