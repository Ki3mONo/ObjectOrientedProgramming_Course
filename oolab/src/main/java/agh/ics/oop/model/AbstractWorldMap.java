package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap{

    protected Vector2d leftBottomCorner=new Vector2d(Integer.MIN_VALUE,Integer.MIN_VALUE);
    protected Vector2d rightUpperCorner=new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE);
    protected Map<Vector2d, Animal> animals = new HashMap<>();
    private Map<Animal, Integer> animalIndices = new HashMap<>();

    //do testów
    public List<MapChangeListener> getObserving() {
        return observing;
    }

    private List<MapChangeListener> observing = new ArrayList<>();

    public void addObserver(MapChangeListener mapChangeListener) {
        observing.add(mapChangeListener);
    }
    public void removeObserver(MapChangeListener mapChangeListener) {
        observing.remove(mapChangeListener);
    }
    public void mapChanged(String message) {
        for (MapChangeListener mapChangeListener : observing) {
            mapChangeListener.mapChanged(this, message);
        }
    }

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
    public void place(Animal animal) throws IncorrectPositionException{
        if (canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(),animal);
            if (!animalIndices.containsKey(animal)) {
                animalIndices.put(animal, animalIndices.size()); // Nadaj nowy indeks
            }
        }
        else {
            throw new IncorrectPositionException(animal.getPosition());
        }
    }
    public void move(Animal animal, MoveDirection direction) throws IncorrectPositionException {
        if (animals.containsValue(animal)) {
            Vector2d oldPosition = animal.getPosition();
            animal.move(this, direction);
            Vector2d newPosition = animal.getPosition();

            // Jeśli pozycja się zmieniła, aktualizujemy mapę
            if (!oldPosition.equals(newPosition)) {
                animals.remove(oldPosition);
                place(animal);
                //moim zdaniem tutaj lepiej jest to wstawić niż w simulation, ponieważ zgodnie z poleceniem mamy pokazywać zmiany
                //mapy tylko, gdy "Umieszczenie zwierzęcia na mapie lub jego poruszenie", jeśli dobrze rozumiem polecenie
                //to w przypadku "jedynie" zmiany orientacji mamy nie informować o tym observera
                int animalIndex = animalIndices.get(animal);
                mapChanged("Zwierze "+ animalIndex +": wykonalo ruch na pozycje: "+animal.getPosition() + ", z orientacja: " + animal);
            }
        }
    }
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
        Boundary boundaries = getCurrentBounds();
        return visualizer.draw(boundaries.bottomLeft(),boundaries.upperRight());
    }

    public abstract Boundary getCurrentBounds();

    public Collection<WorldElement> getElements(){
        Collection<WorldElement> elements = new HashSet<>();
        elements.addAll(animals.values());
        return elements;
    }


}
