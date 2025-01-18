package model

interface WorldElement {
    fun getPosition(): Vector2d

    override fun toString(): String
}
