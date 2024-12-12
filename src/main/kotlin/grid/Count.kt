package me.y9san9.aoc24.grid

inline fun <T> Grid2D<T>.count(block: (T) -> Boolean): Int {
    return Iterable { iterator() }.count(block)
}
