package model

import java.util.*


interface WorldMap : MoveValidator {

    /**
     * Umieszcza zwierzę na mapie.
     *
     * @param animal zwierzę do umieszczenia na mapie.
     * @throws IncorrectPositionException jeśli pozycja jest niepoprawna.
     */
    fun place(animal: Animal): Boolean
    /**
     * Zwraca true, jeśli dana pozycja na mapie jest zajęta.
     *
     * @param position pozycja do sprawdzenia.
     * @return, jeśli pozycja jest zajęta.
     */
    fun isOccupied(position: Vector2d): Boolean

    /**
     * Zwraca obiekt znajdujący się na danej pozycji.
     *
     * @param position pozycja, z której zwracany jest obiekt.
     * @return Object or null
     */
    fun objectAt(position: Vector2d): Any?

}
