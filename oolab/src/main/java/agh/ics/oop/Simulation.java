package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation <T,P>{
    private List<T> elements = new ArrayList<>();
    private List<MoveDirection> directions;
    private WorldMap<T, P> map;

    public Simulation(List<P> initialList,List<MoveDirection> directions, WorldMap <T, P> map){
        this.directions=directions;
        this.map = map;
        for (P position : initialList) {
            T element = createElement(position);
            if (element != null && map.place(element)) {
                elements.add(element);
            }
        }
    }

    private T createElement(P position) {
        if (map != null && position instanceof Vector2d) {
            return (T) new Animal((Vector2d) position);
        } else if (map != null && position instanceof String) {
            return (T) position;
        }
        //element niezgodny z żadną dotychczasową implementacją
        return null;
    }

    public void run(){
        for (int i = 0; i<directions.size(); i++){
            int elementIndex = i%elements.size();
            T element = elements.get(elementIndex);
            map.move((T) element,directions.get(i));
            System.out.println(map);
        }
    }
    public List<T> getElements(){
        return this.elements;
    }
}
