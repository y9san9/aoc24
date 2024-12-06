package me.y9san9.aoc24.grid

data class Bounds(
    val topLeft: Coordinate,
    val bottomRight: Coordinate
) {
    constructor(sizeX: Int, sizeY: Int, relative: Coordinate = Coordinate(0, 0)) : this(
        topLeft = relative,
        bottomRight = Coordinate(x = relative.x + sizeX, y = relative.y + sizeY)
    )

    val sizeX: Int get() = bottomRight.x - topLeft.x
    val sizeY: Int get() = bottomRight.y - topLeft.y

    operator fun contains(coordinate: Coordinate): Boolean {
        return coordinate.x in topLeft.x..bottomRight.y &&
            coordinate.y in topLeft.y..bottomRight.y
    }
}
