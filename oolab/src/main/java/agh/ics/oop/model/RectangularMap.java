package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public class RectangularMap implements WorldMap {
    private int width;
    private int height;

    private final Vector2d leftBottomCorner=new Vector2d(0,0);
    private Vector2d rightUpperCorner;
    private Map<Vector2d, Animal> animals = new HashMap<>();

    public RectangularMap(int width, int height) {
        if (width>=0 && height>=0){
            this.width = width;
            this.height = height;
            this.rightUpperCorner = new Vector2d(width-1,height-1);
        }
    }

    @Override
    public boolean place(Animal animal) {
        if (!isOccupied(animal.getPosition())){
            animals.put(animal.getPosition(),animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (animals.containsValue(animal)){
            if (canMoveTo(animal.getPosition().add(new Vector2d(1,0)))){//do naprawy
                animal.move(direction);
            }
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return !animals.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return this.leftBottomCorner.precedes(position) && this.rightUpperCorner.follows(position) && !isOccupied(position);
    }

    @Override
    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(this.leftBottomCorner,this.rightUpperCorner);
    }
}
