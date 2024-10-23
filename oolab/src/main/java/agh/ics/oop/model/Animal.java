package agh.ics.oop.model;

public class Animal {
    public static final MapDirection INITIAL_MAP_DIRECTION = MapDirection.NORTH;
    private MapDirection orientation=INITIAL_MAP_DIRECTION;
    private Vector2d position;

    public Animal(Vector2d position){
        this.position=position;
    }

    public Animal(int x, int y){
        this(new Vector2d(x,y));
    }

    public Animal(){
        this(2,2);
    }

    @Override
    public String toString() {
        return "Animal={" +
                "position=" + position +
                ", orientation=" + orientation +
                '}';
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public void move(MoveDirection direction){
        Vector2d tempPosition = new Vector2d(this.position.getX(),this.position.getY());
        boolean moved=false;
        switch (direction){
            case RIGHT -> orientation.next();
            case LEFT -> orientation.previous();
            case FORWARD ->{
                tempPosition = this.position.add(orientation.toUnitVector());
                moved = true;
            }
            case BACKWARD ->{
                tempPosition = this.position.add(orientation.toUnitVector().opposite());
                moved = true;
            }
        }
        if (moved && checkValidPosition(tempPosition)){
            this.position=tempPosition;
        }
    }
    private boolean checkValidPosition(Vector2d position){
        Vector2d leftBottomCorner = new Vector2d(0,0);
        Vector2d rightUpperCorner = new Vector2d(4,4);

        return leftBottomCorner.precedes(position) && rightUpperCorner.follows(position);
    }
}
