package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable{
    private List<Animal> animals = new ArrayList<>();
    private List<MoveDirection> directions;
    private WorldMap map;

    public Simulation(List<Vector2d> positions,List<MoveDirection> directions, WorldMap map){
        this.directions=directions;
        this.map = map;
        for (Vector2d position : positions) {
            Animal animal = new Animal(position);
            try {
                map.place(animal);
                animals.add(animal);
                map.mapChanged("Zwierze " + (animals.size() - 1) + ": zostalo dodane na pozycje: " + animal.getPosition() + ", z orientacja: " + animal);
            } catch (IncorrectPositionException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    @Override
    public void run(){
        for (int i = 0; i<directions.size(); i++){
            int animalIndex = i%animals.size();
            Animal animal = animals.get(animalIndex);
            try{
                //to w map.move zostało zrealizowane aktualizowanie stanu mapy-tam dokładniejszy komentarz w metodzie move() klasy AbstractWorldMap
                map.move(animal, directions.get(i));
            }catch (IncorrectPositionException e){
                System.err.println(e.getMessage());
            }
        }
    }
    public List<Animal> getAnimals(){
        return this.animals;
    }
}
