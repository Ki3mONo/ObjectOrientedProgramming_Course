package model

data class Vector2d(val x: Int, val y: Int) : Comparable<Vector2d>{

    override fun toString(): String = "($x,$y)"

    fun precedes(other: Vector2d): Boolean =
        this.x <= other.x && this.y <= other.y


    fun follows(other: Vector2d): Boolean =
        this.x >= other.x && this.y >= other.y

    override operator fun compareTo(other: Vector2d): Int =
        when {
            this.precedes(other) -> -1
            this.follows(other) -> 1
            else -> 0
        }


    fun add(other: Vector2d): Vector2d =
        Vector2d(x + other.x, y + other.y)

    operator fun plus(other: Vector2d): Vector2d = add(other)

    fun subtract(other: Vector2d): Vector2d =
        Vector2d(x - other.x, y - other.y)

    operator fun minus(other: Vector2d): Vector2d = subtract(other)

    fun opposite(): Vector2d =
        Vector2d(-this.x, -this.y)
}

fun MapDirection.toUnitVector(): Vector2d = when (this) {
    MapDirection.NORTH -> Vector2d(0, 1)
    MapDirection.SOUTH -> Vector2d(0, -1)
    MapDirection.EAST -> Vector2d(1, 0)
    MapDirection.WEST -> Vector2d(-1, 0)
}
