package me.y9san9.aoc24.grid

fun <T> MutableGrid2D(bounds: Bounds2D?, map: Map<Point2D, T> = emptyMap()): MutableGrid2D<T> {
    return MutableMapGrid2D(bounds, map.toMutableMap())
}

interface MutableGrid2D<T> : Grid2D<T> {
    override var bounds: Bounds2D?

    fun trySet(point: Point2D, value: T): Boolean

    fun trySet(x: Int, y: Int, value: T): Boolean {
        return trySet(Point2D(x, y), value)
    }

    operator fun set(point: Point2D, value: T) {
        val bounds = bounds
        if (bounds != null && point !in bounds) error("Cannot set value out of bounds. Bounds: $bounds. Coordinate: $point")
        trySet(point, value)
    }

    operator fun set(x: Int, y: Int, value: T) {
        set(Point2D(x, y), value)
    }

    fun remove(point: Point2D)

    fun remove(x: Int, y: Int) {
        remove(Point2D(x, y))
    }
}

fun <T> MutableGrid2D<T>.toGrid(): Grid2D<T> {
    return Grid2D(bounds, points.associateWith(::get))
}
