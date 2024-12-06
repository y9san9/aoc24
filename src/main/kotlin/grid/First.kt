package me.y9san9.aoc24.grid

inline fun <T> Grid<T>.first(block: (T) -> Boolean): T {
    return Iterable { iterator() }.first(block)
}

inline fun <T> Grid<T>.firstOrNull(block: (T) -> Boolean): T? {
    return Iterable { iterator() }.firstOrNull(block)
}

inline fun <T, R> Grid<T>.firstNotNullOf(block: (T) -> R?): R {
    return Iterable { iterator() }.firstNotNullOf(block)
}

inline fun <T, R> Grid<T>.firstNotNullOfOrNull(block: (T) -> R?): R? {
    return Iterable { iterator() }.firstNotNullOfOrNull(block)
}
