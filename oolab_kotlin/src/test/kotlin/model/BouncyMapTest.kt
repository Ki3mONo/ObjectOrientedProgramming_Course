package model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class BouncyMapTest : BehaviorSpec({

    given("a BouncyMap with dimensions 5x5") {
        val bouncyMap = BouncyMap(height = 5, width = 5)

        `when`("checking if a valid position is movable") {
            then("canMoveTo should return true") {
                bouncyMap.canMoveTo(Vector2d(2, 3)) shouldBe true
            }
        }

        `when`("checking if an out-of-bounds position is movable") {
            then("canMoveTo should return false") {
                bouncyMap.canMoveTo(Vector2d(6, 3)) shouldBe false
                bouncyMap.canMoveTo(Vector2d(-1, 0)) shouldBe false
            }
        }

        `when`("placing an animal at a free position") {
            val animal = Animal(Vector2d(2, 3))
            val placed = bouncyMap.place(animal)

            then("the animal should be placed on the map") {
                placed shouldBe true
                bouncyMap.objectAt(Vector2d(2, 3)) shouldBe animal
            }
        }

        `when`("placing another animal at an occupied position") {
            val animal1 = Animal(Vector2d(2, 3), MapDirection.NORTH)
            val animal2 = Animal(Vector2d(2, 3), MapDirection.SOUTH)
            bouncyMap.place(animal1)
            bouncyMap.place(animal2)

            then("both animals should be on the map, but in different positions") {
                bouncyMap.isOccupied(animal1.getPosition()) shouldBe true
                bouncyMap.isOccupied(animal2.getPosition()) shouldBe true
                animal1.getPosition() shouldNotBe animal2.getPosition()
            }
        }

        `when`("placing an animal when the map is full") {
            for (x in 0 until 5) {
                for (y in 0 until 5) {
                    bouncyMap.place(Animal(Vector2d(x, y), MapDirection.NORTH))
                }
            }
            val newAnimal = Animal(Vector2d(2, 2), MapDirection.EAST)
            bouncyMap.place(newAnimal)

            then("it should replace an existing animal") {
                bouncyMap.isOccupied(newAnimal.getPosition()) shouldBe true
            }
        }
    }
})
