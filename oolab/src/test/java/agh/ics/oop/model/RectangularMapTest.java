package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import agh.ics.oop.model.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {
    @Test
    public void testAnimalPlacement() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 2)); // Próba umieszczenia w tym samym miejscu

        assertTrue(map.place(animal1)); // Pierwsze zwierzę powinno być umieszczone
        assertTrue(map.isOccupied(new Vector2d(2,2)));
        assertFalse(map.place(animal2)); // Drugie zwierzę nie powinno być umieszczone na tej samej pozycji
    }

    @Test
    public void testAnimalMovementWithinBounds() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(new Vector2d(2, 2));
        map.place(animal);

        // Ruchy w obrębie granic
        map.move(animal, MoveDirection.FORWARD); // W kierunku północnym
        assertEquals(new Vector2d(2, 3), animal.getPosition());

        map.move(animal, MoveDirection.RIGHT); // Obrót na wschód
        map.move(animal, MoveDirection.FORWARD); // Ruch na wschód
        assertTrue(animal.isAt(new Vector2d(3,3)));

        // Sprawdzenie, czy zwierzę jest na poprawnej pozycji na mapie
        assertTrue(map.isOccupied(new Vector2d(3, 3)));
    }
    @Test
    public void testAnimalMovementOutOfBounds() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(new Vector2d(0, 0));
        map.place(animal);

        map.move(animal, MoveDirection.BACKWARD); // Próba ruchu na południe poza granice mapy
        assertTrue(animal.isAt(new Vector2d(0,0))); // Pozycja nie powinna się zmienić
        assertTrue(map.isOccupied(new Vector2d(0, 0)));


        map.move(animal, MoveDirection.LEFT); // Obrót na zachód
        map.move(animal, MoveDirection.FORWARD); // Próba ruchu na zachód poza granice mapy
        assertTrue(animal.isAt(new Vector2d(0,0))); // Pozycja nie powinna się zmienić
        assertTrue(map.isOccupied(new Vector2d(0, 0)));
    }


}
