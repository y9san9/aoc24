package me.y9san9.aoc24.region

data class Bounds(
    val topLeft: Coordinate,
    val bottomRight: Coordinate
) {
    operator fun contains(coordinate: Coordinate): Boolean {
        return coordinate.x in topLeft.x..bottomRight.y &&
            coordinate.y in bottomRight.y..topLeft.y
    }
}
