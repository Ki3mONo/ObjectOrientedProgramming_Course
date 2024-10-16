package agh.ics.oop.model;

public enum MapDirection {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    @Override
    public String toString() {
        return switch(this){
            case NORTH -> "Polnoc";
            case SOUTH -> "Poludnie";
            case WEST -> "Zachod";
            case EAST -> "Wschod";
        };
    }
}
