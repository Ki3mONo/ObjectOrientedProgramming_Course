package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<Animal> animals = new ArrayList<>();
    private List<MoveDirection> directions;
    private WorldMap map;

    public Simulation(List<Vector2d> positions,List<MoveDirection> directions, WorldMap map){
        this.directions=directions;
        this.map = map;
        for (int i = 0; i<positions.size(); i++){
            Animal animal = new Animal(positions.get(i));
            if (map.place(animal)){
                animals.add(animal);
            }
        }
    }
    public void run(){
        for (int i = 0; i<directions.size(); i++){
            int animalIndex = i%animals.size();
            Animal animal = animals.get(animalIndex);
            map.move(animal,directions.get(i));
            System.out.println("Zwierzak " + animalIndex + " : " + animal.getPosition() + ", " + animal);
            System.out.println(map);
        }
    }
    public List<Animal> getAnimals(){
        return this.animals;
    }
}
