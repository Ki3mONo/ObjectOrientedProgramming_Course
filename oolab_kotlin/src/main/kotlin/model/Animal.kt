package model

class Animal(
    private var position: Vector2d,
    private var orientation: MapDirection = MapDirection.NORTH
) : WorldElement {


    val xPos: Int
        get() = position.x

    val yPos: Int
        get() = position.y

    fun getOrientation(): MapDirection = orientation

    override fun getPosition(): Vector2d = position

    fun setPosition(position:Vector2d){
        this.position=position
    }

    constructor(x: Int, y: Int) : this(Vector2d(x, y))

    constructor() : this(2, 2)

    override fun toString(): String = orientation.toString()


    fun isAt(position: Vector2d?): Boolean = this.position == position

    fun move(validator: MoveValidator, direction: MoveDirection) {
        var tempPosition = position
        when (direction) {
            MoveDirection.RIGHT -> orientation = orientation.next()
            MoveDirection.LEFT -> orientation = orientation.previous()
            MoveDirection.FORWARD -> tempPosition = position.add(orientation.toUnitVector())
            MoveDirection.BACKWARD -> tempPosition = position.add(orientation.toUnitVector().opposite())
        }
        if (validator.canMoveTo(tempPosition)) {
            position = tempPosition
        }
    }


}
