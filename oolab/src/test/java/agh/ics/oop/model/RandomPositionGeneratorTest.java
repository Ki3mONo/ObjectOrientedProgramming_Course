package agh.ics.oop.model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
public class RandomPositionGeneratorTest {
    @Test
    void testCorrectNumberOfPositions() {
        RandomPositionGenerator generator = new RandomPositionGenerator(3, 3, 5);
        Set<Vector2d> uniquePositions = new HashSet<>();
        for (Vector2d position : generator) {
            uniquePositions.add(position);
        }
        assertEquals(5, uniquePositions.size());
    }

    @Test
    void testMaxGrassCount() {
        RandomPositionGenerator generator = new RandomPositionGenerator(3, 3, 9); // Full grid
        Set<Vector2d> uniquePositions = new HashSet<>();
        for (Vector2d position : generator) {
            uniquePositions.add(position);
        }
        assertEquals(9, uniquePositions.size());
    }

    @Test
    void testTooLargeGrassCount() {
        assertThrows(IllegalArgumentException.class, () -> {
            new RandomPositionGenerator(3, 3, 10);
        });
    }

    @Test
    void testRandomness() {
        RandomPositionGenerator generator1 = new RandomPositionGenerator(4, 4, 5,new Random(123));
        RandomPositionGenerator generator2 = new RandomPositionGenerator(4, 4, 5,new Random(124));

        Set<Vector2d> positions1 = new HashSet<>();
        for (Vector2d position : generator1) {
            positions1.add(position);
        }

        Set<Vector2d> positions2 = new HashSet<>();
        for (Vector2d position : generator2) {
            positions2.add(position);
        }

        assertNotEquals(positions1, positions2);
    }

    @Test
    void testIteratorFunctionality() {
        RandomPositionGenerator generator = new RandomPositionGenerator(4, 4, 5);
        Iterator<Vector2d> iterator = generator.iterator();

        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }

        assertEquals(5, count);
    }
    @Test
    void testNextThrowsIllegalStateExceptionWhenNoMorePositions() {
        RandomPositionGenerator generator = new RandomPositionGenerator(3, 3, 3);

        int count = 0;
        while (generator.hasNext()) {
            generator.next();
            count++;
        }

        assertEquals(3, count);

        // Próba wywołania next() po zakończeniu iteracji powinna rzucić wyjątek
        assertThrows(IllegalStateException.class, generator::next);
    }
}
