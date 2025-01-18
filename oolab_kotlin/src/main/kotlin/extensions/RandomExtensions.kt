package extensions

import model.WorldMap
import model.Vector2d

fun WorldMap.randomPosition(mapSize: Vector2d): Vector2d {
    val emptyPositions = mutableListOf<Vector2d>()
    for (x in 0 until mapSize.x) {
        for (y in 0 until mapSize.y) {
            emptyPositions.add(Vector2d(x, y))
        }
    }
    return emptyPositions.random()
}

fun WorldMap.randomFreePosition(mapSize: Vector2d): Vector2d? {
    val emptyPositions = mutableListOf<Vector2d>()
    for (x in 0 until mapSize.x) {
        for (y in 0 until mapSize.y) {
            val position = Vector2d(x, y)
            if (!this.isOccupied(position)) {
                emptyPositions.add(position)
            }
        }
    }
    return emptyPositions.randomOrNull()
}