package me.y9san9.aoc24.grid

inline fun <T> Grid2D<T>.any(block: (T) -> Boolean): Boolean {
    return Iterable { iterator() }.any(block)
}
