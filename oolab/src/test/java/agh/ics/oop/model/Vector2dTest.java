package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    @Test
    public void equalsTest(){
        //given
        Vector2d v1 = new Vector2d(1,4);
        Vector2d v2 = new Vector2d(1,4);
        //when
        boolean isEqual = v1.equals(v2);
        //then
        assertTrue(isEqual);
        // można też tak:
        // assertEquals(v1,v2);
    }

    @Test
    public void toStringTest(){
        //given
        Vector2d v1 = new Vector2d(1,3);
        //when
        //then
        assertEquals("(1,3)",v1.toString());
    }
    @Test
    public void precedesTrueTest(){
        //given
        Vector2d v1 = new Vector2d(1,1);
        Vector2d v2 = new Vector2d(2,2);
        //when
        //then
        assertTrue(v1.precedes(v2));
    }
    public void precedesTrueEqualXTest(){
        //given
        Vector2d v1 = new Vector2d(1,1);
        Vector2d v2 = new Vector2d(1,2);
        //when
        //then
        assertTrue(v1.precedes(v2));
    }
    public void precedesTrueEqualYTest(){
        //given
        Vector2d v1 = new Vector2d(1,1);
        Vector2d v2 = new Vector2d(2,1);
        //when
        //then
        assertTrue(v1.precedes(v2));
    }
    @Test
    public void precedesFalseTest(){
        //given
        Vector2d v1 = new Vector2d(2,2);
        Vector2d v2 = new Vector2d(1,1);
        //when
        //then
        assertFalse(v1.precedes(v2));
    }
    @Test
    public void precedesFalseEqualXTest(){
        //given
        Vector2d v1 = new Vector2d(2,2);
        Vector2d v2 = new Vector2d(2,1);
        //when
        //then
        assertFalse(v1.precedes(v2));
    }
    @Test
    public void precedesFalseEqualYTest(){
        //given
        Vector2d v1 = new Vector2d(2,2);
        Vector2d v2 = new Vector2d(1,2);
        //when
        //then
        assertFalse(v1.precedes(v2));
    }
    @Test
    public void followsTrueTest(){
        //given
        Vector2d v1 = new Vector2d(2,2);
        Vector2d v2 = new Vector2d(1,1);
        //when
        //then
        assertTrue(v1.follows(v2));
    }
    @Test
    public void followsTrueEqualXTest(){
        //given
        Vector2d v1 = new Vector2d(2,2);
        Vector2d v2 = new Vector2d(2,1);
        //when
        //then
        assertTrue(v1.follows(v2));
    }
    @Test
    public void followsTrueEqualYTest(){
        //given
        Vector2d v1 = new Vector2d(2,2);
        Vector2d v2 = new Vector2d(1,2);
        //when
        //then
        assertTrue(v1.follows(v2));
    }
    @Test
    public void followsFalseTest(){
        //given
        Vector2d v1 = new Vector2d(1,1);
        Vector2d v2 = new Vector2d(2,2);
        //when
        //then
        assertFalse(v1.follows(v2));
    }
    public void followsFalseEqualXTest(){
        //given
        Vector2d v1 = new Vector2d(2,1);
        Vector2d v2 = new Vector2d(2,2);
        //when
        //then
        assertFalse(v1.follows(v2));
    }
    public void followsFalseEqualYTest(){
        //given
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(2,2);
        //when
        //then
        assertFalse(v1.follows(v2));
    }
    @Test
    public void upperRightTest(){
        //given
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(2,1);
        Vector2d expectedV = new Vector2d(2,2);
        //when
        Vector2d v3 = v1.upperRight(v2);
        //then
        assertEquals(expectedV,v3);
    }

    @Test
    public void lowerLeftTest(){
        //given
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(2,1);
        Vector2d expectedV = new Vector2d(1,1);
        //when
        Vector2d v3 = v1.lowerLeft(v2);
        //then
        assertEquals(expectedV,v3);
    }

    @Test
    public void addTest(){
        //given
        Vector2d v1 = new Vector2d(2,1);
        Vector2d v2 = new Vector2d(3,7);
        Vector2d expectedV = new Vector2d(5,8);
        //when
        Vector2d v3 = v1.add(v2);
        //then
        assertEquals(expectedV,v3);
    }
    @Test
    public void subtractTest() {
        //given
        Vector2d v1 = new Vector2d(2,1);
        Vector2d v2 = new Vector2d(3,7);
        Vector2d expectedV = new Vector2d(-1,-6);
        //when
        Vector2d v3 = v1.substract(v2);
        //then
        assertEquals(expectedV,v3);
    }
    @Test
    public void oppositeTest() {
        //given
        Vector2d v1 = new Vector2d(2,1);
        Vector2d expectedV = new Vector2d(-2,-1);
        //when
        Vector2d v2 = v1.opposite();
        //then
        assertEquals(expectedV,v2);
    }
}
