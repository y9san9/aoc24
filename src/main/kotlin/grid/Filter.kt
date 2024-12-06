package me.y9san9.aoc24.grid

fun <T> Grid<T>.filter(block: (T) -> Boolean): List<T> {
    return Iterable { iterator() }.filter(block)
}
