package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
public class GrassFieldTest {

    @Test
    public void testGrassFieldCreation() {
        GrassField grassField = new GrassField(10);
        assertEquals(10, grassField.getGrassMap().size());
        Set<Vector2d> uniquePositions = new HashSet<>(grassField.getGrassMap().keySet());
        assertEquals(grassField.getGrassMap().size(), uniquePositions.size());
    }

    @Test
    public void testGrassPlacementAtOccupiedPosition() {
        GrassField grassField = new GrassField(10);
        Animal animal = new Animal(new Vector2d(2, 2));
        assertTrue(grassField.place(animal));
        assertEquals(animal,grassField.objectAt(new Vector2d(2, 2)));
    }
    @Test
    public void testFindLeftBottom() {
        GrassField grassField = new GrassField(10);
        Animal animal = new Animal(new Vector2d(2, 2));
        grassField.place(animal);

        Vector2d leftBottom = grassField.findLeftBottom();

        assertNotNull(leftBottom);
        System.out.println(leftBottom);

        for (WorldElement element : grassField.getElements()) {
            Vector2d elementPosition = element.getPosition();
            assertTrue(elementPosition.getX() >= leftBottom.getX());
            assertTrue(elementPosition.getY() >= leftBottom.getY());
        }
    }

    @Test
    public void testFindRightUpper() {
        GrassField grassField = new GrassField(10);
        Animal animal = new Animal(new Vector2d(2, 2));
        grassField.place(animal);

        Vector2d rightUpper = grassField.findRightUpper();

        assertNotNull(rightUpper);

        for (WorldElement element : grassField.getElements()) {
            Vector2d elementPosition = element.getPosition();
            assertTrue(elementPosition.getX() <= rightUpper.getX());
            assertTrue(elementPosition.getY() <= rightUpper.getY());
        }
    }

    @Test
    public void testAnimalMovementWithGrass() {
        GrassField grassField = new GrassField(10);
        Animal animal = new Animal(new Vector2d(2, 2));
        assertTrue(grassField.place(animal));
        assertEquals(animal,grassField.objectAt(new Vector2d(2, 2)));
        grassField.move(animal, MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(2, 3)));
    }

    @Test
    public void testGetElements() {
        GrassField grassField = new GrassField(10);
        Animal animal1 = new Animal(new Vector2d(0, 0));
        Animal animal2 = new Animal(new Vector2d(1, 1));
        grassField.place(animal1);
        grassField.place(animal2);
        Collection<WorldElement> elements = grassField.getElements();
        assertTrue(elements.contains(animal1));
        assertTrue(elements.contains(animal2));
        assertTrue(elements.size() == 12);
    }

    @Test
    public void testGrassGenerationWithinBounds() {
        GrassField grassField = new GrassField(10);
        for (Vector2d position : grassField.getGrassMap().keySet()) {
            assertTrue(position.getX() >= 0 && position.getX() <= 10);
            assertTrue(position.getY() >= 0 && position.getY() <= 10);
        }
    }
    @Test
    public void testToStringWithSeed() {

        long seed = 1234;
        Random random = new Random(seed);
        GrassField grassField = new GrassField(10,random);
        Animal animal1 = new Animal(new Vector2d(6, 7));
        Animal animal2 = new Animal(new Vector2d(1, 1));
        Animal animal3 = new Animal();
        grassField.place(animal1);
        grassField.place(animal2);
        grassField.place(animal3);
        grassField.move(animal1, MoveDirection.FORWARD);


        String expectedString =
                        " y\\x  0 1 2 3 4 5 6 7 8\r\n" +
                        " 11: -------------------\r\n" +
                        " 10: | | | | | |*| |*| |\r\n" +
                        "  9: | | | | | | | | | |\r\n" +
                        "  8: | | | | | | |N| | |\r\n" +
                        "  7: | | | | | | | | |*|\r\n" +
                        "  6: | | | | | | | | | |\r\n" +
                        "  5: | | | | | | |*| | |\r\n" +
                        "  4: |*| | | | | |*| | |\r\n" +
                        "  3: | | | | | | | | | |\r\n" +
                        "  2: | | |N| | | |*| | |\r\n" +
                        "  1: | |N|*| | | | | | |\r\n" +
                        "  0: | | | |*| | |*| | |\r\n" +
                        " -1: -------------------\r\n";


        assertEquals(expectedString, grassField.toString());
    }
}
