package me.y9san9.aoc24.grid

inline fun <T> Grid2D(bounds: Bounds2D, init: (Point2D) -> T?): Grid2D<T> {
    val map = buildMap<Point2D, T> {
        for ((y, row) in bounds.rows()) {
            for (x in row) {
                val point = Point2D(x, y)
                val value = init(point)
                if (value != null) {
                    put(point, value)
                }
            }
        }
    }
    return Grid2D(bounds, map)
}

fun <T> Grid2D(bounds: Bounds2D?, map: Map<Point2D, T>): Grid2D<T> {
    return MapGrid2D(bounds, map)
}

fun <T> Grid2D(rows: Grid2D.Rows<T>): Grid2D<T> {
    val map = rows.data.flatMapIndexed { y, row ->
        row.mapIndexed { x, element ->
            Point2D(x, y) to element
        }
    }.toMap()

    val bounds = Bounds2D(
        sizeX = rows.data.maxOf { row -> row.size },
        sizeY = rows.size
    )

    return Grid2D(bounds, map)
}

fun <T> Grid2D(columns: Grid2D.Columns<T>): Grid2D<T> {
    val map = columns.data.flatMapIndexed { x, row ->
        row.mapIndexed { y, element ->
            Point2D(x, y) to element
        }
    }.toMap()

    val bounds = Bounds2D(
        sizeX = columns.size,
        sizeY = columns.data.maxOf { column -> column.size }
    )

    return Grid2D(bounds, map)
}

interface Grid2D<out T> {
    val bounds: Bounds2D?
    val points: Set<Point2D>
    fun getOrNull(point: Point2D): T?

    fun getOrNull(x: Int, y: Int): T? {
        return getOrNull(Point2D(x, y))
    }

    operator fun get(point: Point2D): T {
        if (point !in this) error("Coordinate is out of bounds")
        return getOrNull(point) ?: error("No such element at $point")
    }

    operator fun get(x: Int, y: Int): T {
        return get(Point2D(x, y))
    }

    operator fun contains(point: Point2D): Boolean {
        return point in points
    }

    operator fun iterator(): Iterator<T> {
        return iterator {
            for (coordinate in points) {
                yield(get(coordinate))
            }
        }
    }

    class Rows<out T>(val data: List<List<T>>) {
        val size get() = data.size

        operator fun component1(): List<T> = data[0]
        operator fun component2(): List<T> = data[1]
        operator fun component3(): List<T> = data[2]
        operator fun component4(): List<T> = data[3]
        operator fun component5(): List<T> = data[4]

        operator fun get(i: Int): List<T> = data[i]
        operator fun iterator(): Iterator<List<T>> = data.iterator()
    }

    class Columns<out T>(val data: List<List<T>>) {
        val size get() = data.size

        operator fun component1(): List<T> = data[0]
        operator fun component2(): List<T> = data[1]
        operator fun component3(): List<T> = data[2]
        operator fun component4(): List<T> = data[3]
        operator fun component5(): List<T> = data[4]

        operator fun get(i: Int): List<T> = data[i]
        operator fun iterator(): Iterator<List<T>> = data.iterator()
    }
}

fun <T> Grid2D<T>.toMutableGrid(): MutableGrid2D<T> {
    return MutableGrid2D(bounds, points.associateWith(::get))
}
