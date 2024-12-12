package me.y9san9.aoc24.grid

inline fun <T, R> Grid2D<T>.map(block: (T) -> R): Grid2D<R> {
    val map = points.associateWith { coordinate -> block(get(coordinate)) }
    return Grid2D(bounds, map)
}
