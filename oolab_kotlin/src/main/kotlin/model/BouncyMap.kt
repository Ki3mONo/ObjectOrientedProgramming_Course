package model

import extensions.randomFreePosition
import extensions.randomPosition

class BouncyMap(private val height: Int = 0, private val width: Int = 0): WorldMap {
    var animals: HashMap<Vector2d,Animal> = HashMap()

    override fun canMoveTo(position: Vector2d): Boolean {
        return position < Vector2d(width,height) && position > Vector2d(0,0)
    }

    override fun place(animal: Animal): Boolean {
        val position = animal.getPosition()

        if (!canMoveTo(position)) return false

        if (!isOccupied(position)) {
            animals[position] = animal
        } else {
            val newFreePosition = randomFreePosition(Vector2d(width, height))
            if (newFreePosition != null) {
                animal.setPosition(newFreePosition)
                animals[newFreePosition] = animal
            } else {
                val randomOccupiedPosition = randomPosition(Vector2d(width,height))
                animals.remove(randomOccupiedPosition)
                animal.setPosition(randomOccupiedPosition)
                animals[randomOccupiedPosition] = animal
            }
        }
        return true
    }

    override fun isOccupied(position: Vector2d): Boolean {
        return animals.containsKey(position)
    }

    override fun objectAt(position: Vector2d): Any? {
        return animals[position]
    }

}