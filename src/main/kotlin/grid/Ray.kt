package me.y9san9.aoc24.grid

fun <T> Grid2D<T>.ray(
    point: Point2D,
    deltaX: Int,
    deltaY: Int,
    size: Int? = null,
    step: Int = 1
): Sequence<T> {
    val grid = this

    return sequence {
        var currentX = point.x
        var currentY = point.y

        if (size != null) {
            for (i in 0..<size) {
                val element = grid.getOrNull(currentX, currentY) ?: break
                yield(element)
                currentX += deltaX * step
                currentY += deltaY * step
            }
        } else {
            while (true) {
                val element = grid.getOrNull(currentX, currentY) ?: break
                yield(element)
                currentX += deltaX * step
                currentY += deltaY * step
            }
        }
    }
}

fun Grid2D<Char>.rayString(
    point: Point2D,
    deltaX: Int,
    deltaY: Int,
    size: Int? = null,
    step: Int = 1
): String {
    return ray(point, deltaX, deltaY, size, step).joinToString(separator = "")
}
