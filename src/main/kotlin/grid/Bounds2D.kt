package me.y9san9.aoc24.grid

data class Bounds2D(
    val topLeft: Point2D,
    val bottomRight: Point2D
) {
    constructor(sizeX: Int, sizeY: Int, relative: Point2D = Point2D(0, 0)) : this(
        topLeft = relative,
        bottomRight = Point2D(x = relative.x + sizeX, y = relative.y + sizeY)
    )

    val sizeX: Int get() = bottomRight.x - topLeft.x
    val sizeY: Int get() = bottomRight.y - topLeft.y

    operator fun contains(point: Point2D): Boolean {
        return point.x in topLeft.x..bottomRight.y &&
            point.y in topLeft.y..bottomRight.y
    }
}
