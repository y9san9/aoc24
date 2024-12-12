package me.y9san9.aoc24.grid

fun <T> Grid2D<T>.asSequence(): Sequence<T> {
    return Iterable { iterator() }.asSequence()
}
