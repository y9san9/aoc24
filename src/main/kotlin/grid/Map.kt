package me.y9san9.aoc24.grid

inline fun <T, R> Grid<T>.map(block: (T) -> R): Grid<R> {
    val map = coordinates.associateWith { coordinate -> block(get(coordinate)) }
    return Grid(bounds, map)
}
