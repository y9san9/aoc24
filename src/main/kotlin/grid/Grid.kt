package me.y9san9.aoc24.grid

inline fun <T> Grid(bounds: Bounds, init: (Coordinate) -> T?): Grid<T> {
    val map = buildMap<Coordinate, T> {
        for ((y, row) in bounds.rows()) {
            for (x in row) {
                val coordinate = Coordinate(x, y)
                val value = init(coordinate)
                if (value != null) {
                    put(coordinate, value)
                }
            }
        }
    }
    return Grid(bounds, map)
}

fun <T> Grid(bounds: Bounds?, map: Map<Coordinate, T>): Grid<T> {
    return MapGrid(bounds, map)
}

fun <T> Grid(rows: Grid.Rows<T>): Grid<T> {
    val map = rows.data.flatMapIndexed { y, row ->
        row.mapIndexed { x, element ->
            Coordinate(x, y) to element
        }
    }.toMap()

    val bounds = Bounds(
        sizeX = rows.data.maxOf { row -> row.size },
        sizeY = rows.size
    )

    return Grid(bounds, map)
}

fun <T> Grid(columns: Grid.Columns<T>): Grid<T> {
    val map = columns.data.flatMapIndexed { x, row ->
        row.mapIndexed { y, element ->
            Coordinate(x, y) to element
        }
    }.toMap()

    val bounds = Bounds(
        sizeX = columns.size,
        sizeY = columns.data.maxOf { column -> column.size }
    )

    return Grid(bounds, map)
}

interface Grid<out T> {
    val bounds: Bounds?
    val coordinates: Set<Coordinate>
    fun getOrNull(coordinate: Coordinate): T?

    fun getOrNull(x: Int, y: Int): T? {
        return getOrNull(Coordinate(x, y))
    }

    operator fun get(coordinate: Coordinate): T {
        if (coordinate !in this) error("Coordinate is out of bounds")
        return getOrNull(coordinate) ?: error("No such element at $coordinate")
    }

    operator fun get(x: Int, y: Int): T {
        return get(Coordinate(x, y))
    }

    operator fun contains(coordinate: Coordinate): Boolean {
        return bounds?.contains(coordinate) ?: true
    }

    operator fun iterator(): Iterator<T> {
        return iterator {
            for (coordinate in coordinates) {
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

fun <T> Grid<T>.toMutableGrid(): MutableGrid<T> {
    return MutableGrid(bounds, coordinates.associateWith(::get))
}
