package agh.ics.oop.model;

public class Animal {
    public static final MapDirection INITIAL_MAP_DIRECTION = MapDirection.NORTH;
    private MapDirection orientation=INITIAL_MAP_DIRECTION;
    private Vector2d position;

    //na potrzeby testów integracyjnych
    public MapDirection getOrientation() {
        return orientation;
    }
    public Vector2d getPosition() {
        return position;
    }

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
        return "{" + position.toString() + ", " + orientation.toString() + "}";
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public void move(MoveDirection direction){
        switch (direction){
            case RIGHT -> this.orientation=this.orientation.next();
            case LEFT -> this.orientation=this.orientation.previous();
            case FORWARD ->{
                Vector2d tempPosition = this.position.add(orientation.toUnitVector());
                setTempPositionAsCurrent(tempPosition);
            }
            case BACKWARD ->{
                Vector2d tempPosition = this.position.add(orientation.toUnitVector().opposite());
                setTempPositionAsCurrent(tempPosition);
            }
        }
    }

    private void setTempPositionAsCurrent(Vector2d tempPosition) {
        if(checkValidPosition(tempPosition)){
            this.position = tempPosition;
        }
    }

    private boolean checkValidPosition(Vector2d position){
        Vector2d leftBottomCorner = new Vector2d(0,0);
        Vector2d rightUpperCorner = new Vector2d(4,4);

        return leftBottomCorner.precedes(position) && rightUpperCorner.follows(position);
    }
}
