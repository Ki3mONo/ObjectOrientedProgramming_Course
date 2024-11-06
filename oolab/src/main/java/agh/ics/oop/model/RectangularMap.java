package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public class RectangularMap extends AbstractWorldMap implements WorldMap {
    private int width;
    private int height;


    //przydane w implementacji
    private Vector2d leftBottomCorner=new Vector2d(0,0);
    private Vector2d rightUpperCorner;

    public RectangularMap(int width, int height) {
        if (width>=0 && height>=0){
            this.width = width;
            this.height = height;
            //mapa 7x7 to mapa [0,6]x[0,6]
            this.rightUpperCorner = new Vector2d(width-1,height-1);
        }
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(),animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return inBounds(position) && !isOccupied(position);
    }

    public boolean inBounds(Vector2d position){
        return this.leftBottomCorner.precedes(position) && this.rightUpperCorner.follows(position);
    }
    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (animals.containsValue(animal)) {
            Vector2d oldPosition = animal.getPosition();
            animal.move(this, direction);
            Vector2d newPosition = animal.getPosition();

            // Jeśli pozycja się zmieniła, aktualizujemy mapę
            if (!oldPosition.equals(newPosition)) {
                animals.remove(oldPosition);
                place(animal);
            }
        }
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }


    @Override
    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(this.leftBottomCorner,this.rightUpperCorner);
    }
}
