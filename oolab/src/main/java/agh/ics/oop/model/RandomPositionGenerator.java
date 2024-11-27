package agh.ics.oop.model;

import java.util.*;

public class RandomPositionGenerator implements Iterable<Vector2d>, Iterator<Vector2d> {
    private List<Vector2d> positions=new ArrayList<>();
    private int grassCount;
    private Random random;

    public RandomPositionGenerator(int maxWidth, int maxHeight, int grassCount, Random random) {
        if (grassCount > maxWidth * maxHeight) {
            throw new IllegalArgumentException("Too many grass positions for the given grid size!"); //możemy też założyć po prostu, że liczba kępek trawy jest mniejsza niż liczba pól mapy
        }
        this.random = random;
        this.grassCount = grassCount;
        for (int x = 0; x < maxWidth; x++) {
            for (int y = 0; y < maxHeight; y++) {
                positions.add(new Vector2d(x, y));
            }
        }

    }

    public RandomPositionGenerator(int maxWidth, int maxHeight, int grassCount){
        this(maxWidth,maxHeight,grassCount,new Random());
    }

    @Override
    public Iterator<Vector2d> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return grassCount >0 && !positions.isEmpty();
    }

    @Override
    public Vector2d next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more positions to generate!");
        }
        Vector2d nextPos = positions.get(random.nextInt(positions.size()));

        positions.remove(nextPos);
        grassCount--;
        return nextPos;
    }
}