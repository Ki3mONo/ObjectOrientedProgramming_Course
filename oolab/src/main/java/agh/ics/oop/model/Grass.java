package agh.ics.oop.model;

public class Grass implements WorldElement{
    private Vector2d position;

    public Grass(Vector2d position){
        this.position=position;
    }

    public Vector2d getPosition(){
        return this.position;
    }

    @Override
    public String toString() {
        return "*";
    }

    @Override
    public String getResourceName() {
        return "grass.png";
    }
}
