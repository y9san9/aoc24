package me.y9san9.aoc24.grid

import me.y9san9.aoc24.vector.Vector2D

fun <T> Grid2D<T>.plusNeighbours(point: Point2D): List<T> {
    return point.plusNeighbours()
        .filter(this::contains)
        .map(this::get)
}

fun Point2D.plusNeighbours(): List<Point2D> {
    return listOf(
        this + Vector2D.Left,
        this + Vector2D.Top,
        this + Vector2D.Right,
        this + Vector2D.Bottom,
    )
}

fun <T> Grid2D<T>.crossNeighbours(point: Point2D): List<T> {
    return point.crossNeighbours()
        .filter(this::contains)
        .map(this::get)
}

fun Point2D.crossNeighbours(): List<Point2D> {
    return listOf(
        this + Vector2D.TopLeft,
        this + Vector2D.TopRight,
        this + Vector2D.BottomRight,
        this + Vector2D.BottomLeft,
    )
}
