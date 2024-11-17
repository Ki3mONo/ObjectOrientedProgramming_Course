package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

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

        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(size, size, grassCount,random);
        for(Vector2d grassPosition : randomPositionGenerator) {
            grassMap.put(grassPosition, new Grass(grassPosition));
        }
    }

    public Map<Vector2d, Grass> getGrassMap() {
        return grassMap;
    }
    @Override
    public Collection<WorldElement> getElements(){
        Collection<WorldElement> elements = super.getElements();
        elements.addAll(this.getGrassMap().values());
        return elements;
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        Animal animal = (Animal) super.objectAt(position);
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
