package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class GrassTest {
    @Test
    public void testGetPosition() {

        Vector2d position = new Vector2d(1, 2);
        Grass grass = new Grass(position);

        assertEquals(position, grass.getPosition());
    }
    @Test
    public void testToString() {
        Vector2d position = new Vector2d(3, 4);
        Grass grass = new Grass(position);

        assertEquals("*", grass.toString(), "Metoda toString powinna zwracać '*'");
    }

    @Test
    public void testDifferentPositions() {
        Vector2d position1 = new Vector2d(1, 2);
        Vector2d position2 = new Vector2d(3, 4);

        Grass grass1 = new Grass(position1);
        Grass grass2 = new Grass(position2);


        assertNotEquals(grass1.getPosition(), grass2.getPosition());
    }
}
