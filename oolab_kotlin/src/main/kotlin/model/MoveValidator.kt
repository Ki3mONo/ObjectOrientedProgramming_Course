package model

interface MoveValidator {
    /**
     * Informuje, czy jakikolwiek obiekt może przejść do podanej pozycji.
     *
     * @param position pozycja, którą sprawdzamy.
     * @return true, jeśli obiekt może przejść do tej pozycji.
     */
    fun canMoveTo(position: Vector2d): Boolean
}
