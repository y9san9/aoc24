package me.y9san9.aoc24.grid

fun Bounds2D.rows(): Iterable<IndexedValue<Iterable<Int>>> {
    return List(sizeY) { List(sizeX) { x -> x } }.withIndex()
}

fun Bounds2D.columns(): Iterable<IndexedValue<Iterable<Int>>> {
    return List(sizeX) { List(sizeY) { y -> y } }.withIndex()
}
