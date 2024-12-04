package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
    public void testGrassPlacementAtOccupiedPosition(){
        GrassField grassField = new GrassField(10);
        Animal animal = new Animal(new Vector2d(2, 2));
        //ten try catch nie jest potrzebny bo zakładam poprawne przypisanie do mapy, ale proforma zeby nie stracic punktów
        try{
            grassField.place(animal);
        }catch (IncorrectPositionException e){
            System.err.println(e.getMessage());
        }
        assertEquals(animal,grassField.objectAt(new Vector2d(2, 2)));
    }
    @Test
    public void testFindLeftBottom() {
        GrassField grassField = new GrassField(10);
        Animal animal = new Animal(new Vector2d(2, 2));
        try{
            grassField.place(animal);
        }catch (IncorrectPositionException e){
            System.err.println(e.getMessage());
        }

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
        try{
            grassField.place(animal);
        }catch (IncorrectPositionException e){
            System.err.println(e.getMessage());
        }

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
        GrassField grassField = new GrassField(4,new Random(123));
        Animal animal = new Animal(new Vector2d(2, 1));
        try{
            grassField.place(animal);
        }catch (IncorrectPositionException e){
            System.err.println(e.getMessage());
        }
        assertInstanceOf(Grass.class , grassField.objectAt(new Vector2d(3,1)));
        assertEquals(animal,grassField.objectAt(new Vector2d(2, 1)));
        try{
            grassField.move(animal, MoveDirection.RIGHT);
            grassField.move(animal, MoveDirection.FORWARD);
        }catch (IncorrectPositionException e){
            System.err.println(e.getMessage());
        }
        assertTrue(animal.isAt(new Vector2d(3, 1)));
        assertInstanceOf(Grass.class , grassField.objectAt(new Vector2d(2,1)));
    }

    @Test
    public void testGetElements() {
        GrassField grassField = new GrassField(10);
        Animal animal1 = new Animal(new Vector2d(0, 0));
        Animal animal2 = new Animal(new Vector2d(1, 1));
        try{
            grassField.place(animal1);
            grassField.place(animal2);
        }catch (IncorrectPositionException e){
            System.err.println(e.getMessage());
        }

        Collection<WorldElement> elements = grassField.getElements();
        Map<Vector2d, Animal> animals = grassField.getAnimals();
        assertTrue(animals.containsValue(animal1));
        assertTrue(animals.containsValue(animal2));
        assertEquals(2,animals.keySet().size());
        assertTrue(elements.contains(animal1));
        assertTrue(elements.contains(animal2));
        assertTrue(elements.size() == 12);
    }

    @Test
    public void testObjectAt() {
        GrassField grassField = new GrassField(10,new Random(123));
        Vector2d position = new Vector2d(0, 10);
        System.out.println(grassField);

        WorldElement element = grassField.objectAt(position);

        assertNotNull(element);
        assertInstanceOf(Grass.class, element);
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
        try {
            grassField.place(animal1);
            grassField.place(animal2);
            grassField.place(animal3);
            grassField.move(animal1, MoveDirection.FORWARD);
        }catch (IncorrectPositionException e){
            System.err.println(e.getMessage());
        }

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
    @Test
    void addObserverTest() {
        AbstractWorldMap map = new GrassField(10);
        ConsoleMapDisplay listener = new ConsoleMapDisplay();

        map.addObserver(listener);
        map.mapChanged("Test message");
        map.mapChanged("Another message");

        // Sprawdzamy, czy licznik aktualizacji został zwiększony o 2
        assertEquals(2, listener.getUpdateCount(), "Licznik aktualizacji powinien wynosić 2 po dwóch wywołaniach.");
    }

    @Test
    public void removeObserverTest(){
        AbstractWorldMap map = new GrassField(10);
        ConsoleMapDisplay listener = new ConsoleMapDisplay();

        map.addObserver(listener);
        map.mapChanged("Test message");
        map.mapChanged("Another message");
        assertEquals(1, map.getObserving().size());

        map.removeObserver(listener);
        assertEquals(0, map.getObserving().size());

    }

    @Test
    void mapChangedPrintOutputTest() {
        // Przygotowanie
        ConsoleMapDisplay display = new ConsoleMapDisplay();
        GrassField map = new GrassField(10);

        // Przechwycenie strumienia wyjściowego
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        try {

            display.mapChanged(map, "First update");
            display.mapChanged(map, "Second update");

            // Sprawdzenie poprawności wyjścia
            String output = outputStream.toString();
            assertTrue(output.contains("Update #1: First update"), "Wyjście powinno zawierać poprawną wiadomość dla pierwszej aktualizacji.");
            assertTrue(output.contains("Update #2: Second update"), "Wyjście powinno zawierać poprawną wiadomość dla drugiej aktualizacji.");
        } finally {
            // Przywrócenie oryginalnego strumienia wyjściowego
            System.setOut(originalOut);
        }
    }

}
