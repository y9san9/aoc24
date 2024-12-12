package me.y9san9.aoc24.grid

inline fun <T> Grid2D<T>.filter(block: (T) -> Boolean): List<T> {
    return Iterable { iterator() }.filter(block)
}
