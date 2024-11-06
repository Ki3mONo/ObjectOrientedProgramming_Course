package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public class GrassField extends AbstractWorldMap implements WorldMap {

    private Vector2d leftBottomCorner=new Vector2d(Integer.MIN_VALUE,Integer.MIN_VALUE);
    private Vector2d rightUpperCorner=new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE);

    private Map<Vector2d,Animal> animals = new HashMap<>();

    private Map<Vector2d,Grass> grassMap = new HashMap<>();


    public GrassField(int grassCount){
        int size = (int)Math.sqrt(grassCount*10);
        Random random = new Random();

        while (grassMap.size() < size) {
            int x = random.nextInt(size+1);  // współrzędna x w zakresie od 0 do size
            int y = random.nextInt(size+1);  // współrzędna y w zakresie od 0 do size
            Vector2d position = new Vector2d(x, y);
            if (!grassMap.containsKey(position)){
                Grass grassToPut = new Grass(position);
                grassMap.put(position,grassToPut);
            }
        }
    }
    public Map<Vector2d, Animal> getAnimals() {
        return animals;
    }

    public Map<Vector2d, Grass> getGrassMap() {
        return grassMap;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return inBounds(position) && !isOccupied(position);
    }

    public boolean inBounds(Vector2d position){
        return this.leftBottomCorner.precedes(position) && this.rightUpperCorner.follows(position);
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
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position) || grassMap.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        Animal animal = animals.get(position);
        //animal ma "pierwszeństwo"
        if(animal!=null){
            return animal;
        }
        //albo zwrócimy grass, albo nulla jak nie ma tam grass więc nie potrzebujemy dodatkowej logiki
        return grassMap.get(position);
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);

        Vector2d dynamicLeftBottomCorner = findLeftBottom();
        Vector2d dynamicRightUpperCorner = findRightUpper();

        return visualizer.draw(dynamicLeftBottomCorner,dynamicRightUpperCorner);
    }

    private Vector2d findLeftBottom(){
        Vector2d result = null;
        for(Vector2d position : this.getAnimals().keySet()){
            if (result==null) {
                result = position;
            }
            else if (position.precedes(result)){
                result=position;
            }
        }
        for(Vector2d position : this.getGrassMap().keySet()){
            if (result==null) {
                result = position;
            }
            else if (position.precedes(result)){
                result=position;
            }
        }
        return result;
    }

    private Vector2d findRightUpper(){
        Vector2d result = null;
        for(Vector2d position : this.getAnimals().keySet()){
            if ((result==null) || (position.follows(result))){
                result = position;
            }
        }
        for(Vector2d position : this.getGrassMap().keySet()){
            if (result==null) {
                result = position;
            }
            else if (position.follows(result)){
                result=position;
            }
        }
        return result;
    }
}
