package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation<T, P> {
    private final List<T> objects = new ArrayList<>();
    private final List<MoveDirection> directions;
    private final WorldMap<T, P> map;

    // Konstruktor przyjmuje listę obiektów, kierunki ruchu i dowolną mapę WorldMap
    public Simulation(List<T> entities, List<MoveDirection> directions, WorldMap<T, P> map) {
        this.directions = directions;
        this.map = map;

        // Umieszczamy obiekty na mapie
        for (T entity : entities) {
            if (map.place(entity)) {
                this.objects.add(entity);
            }
        }
    }
    public void run(){
        for (int i = 0; i<directions.size(); i++){
            int objectIndex = i%objects.size();
            T object = objects.get(animalIndex);
            map.move(animal,directions.get(i));
            //nie jestem pewny czy ten pierwszy sout jest potrzebny, ale zostawię go bo nigdzie nie kazali go usuwać
            System.out.println("Zwierzak " + animalIndex + " : " + animal.getPosition() + ", " + animal);
            //tutaj wypisanie stanu mapy z zadania 6.
            System.out.println(map);
        }
    }
    public List<Animal> getAnimals(){
        return this.animals;
    }
}
