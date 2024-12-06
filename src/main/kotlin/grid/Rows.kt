package me.y9san9.aoc24.grid

fun Bounds.rows(): Iterable<IndexedValue<Iterable<Int>>> {
    return List(sizeY) { List(sizeX) { x -> x } }.withIndex()
}

fun Bounds.columns(): Iterable<IndexedValue<Iterable<Int>>> {
    return List(sizeX) { List(sizeY) { y -> y } }.withIndex()
}
