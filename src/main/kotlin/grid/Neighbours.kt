package me.y9san9.aoc24.grid

fun <T> Grid<T>.plusNeighbours(coordinate: Coordinate): List<T> {
    val (x, y) = coordinate

    return listOfNotNull(
        getOrNull(x = x - 1, y = y),
        getOrNull(x = x + 1, y = y),
        getOrNull(x = x, y = y - 1),
        getOrNull(x = x, y = y + 1),
    )
}
