package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class TextMapTest {
    @Test
    public void findKeyTest(){
        TextMap map = new TextMap();
        String str1 = "Ala";
        String str2 = "ma";
        String str3 = "sowoniedźwiedzia";
        String str4 = "kota";
        String str5 = "Ala";

        map.place(str1);
        map.place(str2);
        map.place(str3);

        Integer idx1 = 0;
        Integer idx2 = 1;
        Integer idx3 = 2;

        assertEquals(idx1, map.findKey(str1));
        assertEquals(idx2, map.findKey(str2));
        assertEquals(idx3, map.findKey(str3));
        assertEquals(null,map.findKey(str4));

        assertFalse(map.place(str5));


    }
    @Test
    public void testWordsPlacement() {
        TextMap map = new TextMap();
        String str1 = "Ala";
        String str2 = "ma";
        String str3 = "sowoniedźwiedzia";

        assertFalse(map.isOccupied(0));
        assertFalse(map.isOccupied(1));
        assertFalse(map.isOccupied(2));
        assertTrue(map.place(str1));
        assertTrue(map.place(str2));
        assertTrue(map.place(str3));
        assertEquals(str1,map.objectAt(0));
        assertEquals(str2,map.objectAt(1));
        assertEquals(str3,map.objectAt(2));
        assertFalse(map.isOccupied(3));
        assertFalse(map.isOccupied(-1));
        assertFalse(map.isOccupied(6));
    }
    @Test
    public void testWordsMovementWithinBoundsForward(){
        TextMap map = new TextMap();
        String str1 = "Ala";
        String str2 = "ma";
        String str3 = "sowoniedźwiedzia";
        map.place(str1);
        map.place(str2);
        map.place(str3);
        String strToMove = "ma";
        assertEquals(strToMove,map.objectAt(1));
        assertEquals(str3,map.objectAt(2));
        map.move(strToMove,MoveDirection.FORWARD);
        assertEquals(strToMove,map.objectAt(2));
        assertEquals(str3,map.objectAt(1));

    }

    @Test
    public void testWordsMovementWithinBoundsRight(){
        TextMap map = new TextMap();
        String str1 = "Ala";
        String str2 = "ma";
        String str3 = "sowoniedźwiedzia";
        map.place(str1);
        map.place(str2);
        map.place(str3);
        String strToMove = "ma";
        assertEquals(strToMove,map.objectAt(1));
        assertEquals(str3,map.objectAt(2));
        map.move(strToMove,MoveDirection.RIGHT);
        assertEquals(strToMove,map.objectAt(2));
        assertEquals(str3,map.objectAt(1));

    }
    @Test
    public void testWordsBackwardMovementWithinBoundsBackward(){
        TextMap map = new TextMap();
        String str1 = "Ala";
        String str2 = "ma";
        String str3 = "sowoniedźwiedzia";
        map.place(str1);
        map.place(str2);
        map.place(str3);
        String strToMove = "ma";
        assertEquals(strToMove,map.objectAt(1));
        assertEquals(str1,map.objectAt(0));
        map.move(strToMove,MoveDirection.BACKWARD);
        assertEquals(strToMove,map.objectAt(0));
        assertEquals(str1,map.objectAt(1));

    }

    @Test
    public void testWordsBackwardMovementWithinBoundsLeft(){
        TextMap map = new TextMap();
        String str1 = "Ala";
        String str2 = "ma";
        String str3 = "sowoniedźwiedzia";
        map.place(str1);
        map.place(str2);
        map.place(str3);
        String strToMove = "ma";
        assertEquals(strToMove,map.objectAt(1));
        assertEquals(str1,map.objectAt(0));
        map.move(strToMove,MoveDirection.LEFT);
        assertEquals(strToMove,map.objectAt(0));
        assertEquals(str1,map.objectAt(1));

    }

    @Test
    public void testAnimalMovementLeftBound(){
        TextMap map = new TextMap();
        String str1 = "Ala";
        String str2 = "ma";
        String str3 = "sowoniedźwiedzia";
        map.place(str1);
        map.place(str2);
        map.place(str3);
        String strToMove = "Ala";
        assertEquals(str1,map.objectAt(0));
        assertEquals(str2,map.objectAt(1));
        assertEquals(str3,map.objectAt(2));
        map.move(strToMove,MoveDirection.BACKWARD);
        assertEquals(str1,map.objectAt(0));
        assertEquals(str2,map.objectAt(1));
        assertEquals(str3,map.objectAt(2));
    }
    @Test
    public void testAnimalMovementRightBound(){
        TextMap map = new TextMap();
        String str1 = "Ala";
        String str2 = "ma";
        String str3 = "sowoniedźwiedzia";
        map.place(str1);
        map.place(str2);
        map.place(str3);
        System.out.println(map.lastUsed);
        String strToMove = "sowoniedźwiedzia";
        assertEquals(str1,map.objectAt(0));
        assertEquals(str2,map.objectAt(1));
        assertEquals(str3,map.objectAt(2));
        map.move(strToMove,MoveDirection.FORWARD);
        assertEquals(str1,map.objectAt(0));
        assertEquals(str2,map.objectAt(1));
        assertEquals(str3,map.objectAt(2));
    }

    @Test
    public void toStringTest(){
        TextMap map = new TextMap();
        String str1 = "Ala";
        String str2 = "ma";
        String str3 = "sowoniedźwiedzia";
        map.place(str1);
        map.place(str2);
        map.place(str3);

        String expected = "[Ala, ma, sowoniedźwiedzia]";
        String expectedFromMap = map.getWords().values().toString();

        assertEquals(expected,map.toString());
        assertEquals(expected,expectedFromMap);
        assertEquals(expectedFromMap,map.toString());
    }
}
