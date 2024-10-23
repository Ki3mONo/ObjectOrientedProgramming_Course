package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.List;

public class Simulation {
    private List<Vector2d> positions;
    private List<MoveDirection> directions;

    public Simulation(List<Vector2d> positions,List<MoveDirection> directions){
        this.positions=positions;
        this.directions=directions;
    }
    public void run(){
        for(int i = 0; i<positions.size(); i++){
            var Animal = new Animal(positions.get(i));
        }
        for(int i = 0; i<directions.size(); ){
            for(int j = 0; j<positions.size(); j++){
                
            }
        }
    }
}
