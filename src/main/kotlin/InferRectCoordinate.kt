package me.y9san9.aoc24

import me.y9san9.aoc24.grid.Point2D

fun inferRectCoordinate(
    first: Point2D,
    second: Point2D,
    third: Point2D
): Point2D {
    val y = if (first.y == second.y) {
        third.y
    } else {
        first.y
    }

    val x = if (first.x == second.x) {
        third.x
    } else {
        first.x
    }

    return Point2D(x, y)
}
