package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<Animal> animals = new ArrayList<>();
    private List<MoveDirection> directions;

    public Simulation(List<Vector2d> positions,List<MoveDirection> directions){
        this.directions=directions;

        for (int i = 0; i<positions.size(); i++){
            animals.add(new Animal(positions.get(i)));
        }
    }
    public void run(){
        for (int i = 0; i<directions.size(); i++){
            int animalIndex = i%animals.size();
            Animal animal = animals.get(animalIndex);
            animal.move(directions.get(i));
            System.out.println("Zwierzak " + animalIndex + " : " + animal);
        }
    }
    public List<Animal> getAnimals(){
        return this.animals;
    }
}
