package agh.ics.oop.model;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap{

    protected Vector2d leftBottomCorner=new Vector2d(Integer.MIN_VALUE,Integer.MIN_VALUE);
    protected Vector2d rightUpperCorner=new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE);;
    protected Map<Vector2d, Animal> animals = new HashMap<>();

    protected Map<Vector2d, Animal> getAnimals(){
        return animals;
    }
    protected boolean inBounds(Vector2d position){
        return this.leftBottomCorner.precedes(position) && this.rightUpperCorner.follows(position);
    }
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }
    public boolean canMoveTo(Vector2d position) {
        return inBounds(position) && !isOccupied(position);
    }
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(),animal);
            return true;
        }
        return false;
    }
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
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    public abstract String toString();

    public Collection<WorldElement> getElements(){
        Collection<WorldElement> elements = new HashSet<>();
        elements.addAll(animals.values());
        return elements;
    }


}
