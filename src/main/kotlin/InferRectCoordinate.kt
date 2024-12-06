package me.y9san9.aoc24

import me.y9san9.aoc24.grid.Coordinate

fun inferRectCoordinate(
    first: Coordinate,
    second: Coordinate,
    third: Coordinate
): Coordinate {
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

    return Coordinate(x, y)
}
