package me.y9san9.aoc24.grid

fun <T> MutableGrid(bounds: Bounds?, map: Map<Coordinate, T> = emptyMap()): MutableGrid<T> {
    return MutableMapGrid(bounds, map.toMutableMap())
}

interface MutableGrid<T> : Grid<T> {
    override var bounds: Bounds?

    fun trySet(coordinate: Coordinate, value: T): Boolean

    fun trySet(x: Int, y: Int, value: T): Boolean {
        return trySet(Coordinate(x, y), value)
    }

    operator fun set(coordinate: Coordinate, value: T) {
        val bounds = bounds
        if (bounds != null && coordinate !in bounds) error("Cannot set value out of bounds. Bounds: $bounds. Coordinate: $coordinate")
        trySet(coordinate, value)
    }

    operator fun set(x: Int, y: Int, value: T) {
        set(Coordinate(x, y), value)
    }
}

fun <T> MutableGrid<T>.toGrid(): Grid<T> {
    return Grid(bounds, coordinates.associateWith(::get))
}
