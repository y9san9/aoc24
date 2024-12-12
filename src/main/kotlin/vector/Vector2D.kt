package me.y9san9.aoc24.vector

data class Vector2D(val dX: Int, val dY: Int) {
    companion object {
        val TopLeft: Vector2D = Vector2D(dX = -1, dY = -1)
        val Top: Vector2D = Vector2D(dX = 0, dY = -1)
        val TopRight: Vector2D = Vector2D(dX = 1, dY = -1)
        val Left: Vector2D = Vector2D(dX = -1, dY = 0)
        val Center: Vector2D = Vector2D(dX = 0, dY = 0)
        val Right: Vector2D = Vector2D(dX = 1, dY = 0)
        val BottomLeft: Vector2D = Vector2D(dX = -1, dY = 1)
        val Bottom: Vector2D = Vector2D(dX = 0, dY = 1)
        val BottomRight: Vector2D = Vector2D(dX = 1, dY = 1)
    }
}
