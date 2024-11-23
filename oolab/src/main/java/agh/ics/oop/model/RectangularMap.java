package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public class RectangularMap extends AbstractWorldMap {
    private int width;
    private int height;

    public RectangularMap(int width, int height) {
        if (width>=0 && height>=0){
            this.width = width;
            this.height = height;
            //mapa 7x7 to mapa [0,6]x[0,6]
            this.leftBottomCorner=new Vector2d(0,0);
            this.rightUpperCorner = new Vector2d(width-1,height-1);
        }
    }

    @Override
    public Boundary getCurrentBounds(){
        return new Boundary(leftBottomCorner,rightUpperCorner);
    }
}
