package agh.ics.oop.model;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrassField extends AbstractWorldMap{

    private Map<Vector2d,Grass> grassMap = new HashMap<>();
    @Override
    public boolean canMoveTo(Vector2d position) {
        return inBounds(position) && !super.isOccupied(position);
    }
    @Override
    public boolean isOccupied(Vector2d position) {
        return isOccupiedByGrass(position) || super.isOccupied(position); //możnaby było uzyc elements
    }
    private boolean isOccupiedByGrass(Vector2d position){
        return grassMap.containsKey(position);
    }

    public GrassField(int grassCount){
        this(grassCount, new Random());
    }
    //tylko do testu toString zostały zrobione dwa konstruktory
    public GrassField(int grassCount, Random random){
        int size = (int)Math.sqrt(grassCount*10);
        this.mapID = UUID.randomUUID();
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

    public Map<Vector2d, Grass> getGrassMap() {
        return grassMap;
    }
    @Override
    public Collection<WorldElement> getElements(){
            return Stream
                    .concat(animals.values().stream(), grassMap.values().stream())
                    .collect(Collectors.toList());
    }

    @Override
    public Optional<WorldElement> objectAt(Vector2d position) {
        Optional<WorldElement> animal = super.objectAt(position);
        // animal ma "pierwszeństwo"
        if (animal.isPresent()) {
            return animal;
        }
        // Jeżeli nie ma zwierzęcia, sprawdzamy, czy jest trawa
        return Optional.ofNullable(grassMap.get(position));
    }

    @Override
    public Boundary getCurrentBounds(){
        return new Boundary(findLeftBottom(),findRightUpper());
    }

    Vector2d findLeftBottom(){
        int resX=Integer.MAX_VALUE;
        int resY=Integer.MAX_VALUE;
        for(WorldElement element : this.getElements()){
            Vector2d position = element.getPosition();
            int currentX = position.getX();
            int currentY = position.getY();
            if (currentX<resX){
                resX = currentX;
            }
            if (currentY<resY){
                resY = currentY;
            }
        }
        return new Vector2d(resX,resY);
    }

    Vector2d findRightUpper(){
        int resX=Integer.MIN_VALUE;
        int resY=Integer.MIN_VALUE;

        for(WorldElement element : this.getElements()){
            Vector2d position = element.getPosition();
            int currentX = position.getX();
            int currentY = position.getY();
            if (currentX>resX){
                resX = currentX;
            }
            if (currentY>resY){
                resY = currentY;
            }
        }
        return new Vector2d(resX,resY);
    }
}
