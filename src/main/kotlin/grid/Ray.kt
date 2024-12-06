package me.y9san9.aoc24.grid

fun <T> Grid<T>.ray(
    coordinate: Coordinate,
    deltaX: Int,
    deltaY: Int,
    size: Int? = null,
    step: Int = 1
): Sequence<T> {
    val grid = this

    return sequence {
        var currentX = coordinate.x
        var currentY = coordinate.y

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

fun Grid<Char>.rayString(
    coordinate: Coordinate,
    deltaX: Int,
    deltaY: Int,
    size: Int? = null,
    step: Int = 1
): String {
    return ray(coordinate, deltaX, deltaY, size, step).joinToString(separator = "")
}
