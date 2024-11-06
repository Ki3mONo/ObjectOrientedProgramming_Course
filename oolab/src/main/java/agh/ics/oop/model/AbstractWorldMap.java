package agh.ics.oop.model;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap{

    protected Vector2d leftBottomCorner;
    protected Vector2d rightUpperCorner;
    protected Map<Vector2d, Animal> animals = new HashMap<>();


}
