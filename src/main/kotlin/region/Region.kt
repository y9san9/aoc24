package me.y9san9.aoc24.region

interface Region<T> {
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
}

fun <T> Region(bounds: Bounds?, map: Map<Coordinate, T>): Region<T> {
    return MapRegion(bounds, map)
}

private class MapRegion<T>(
    override val bounds: Bounds?,
    private val map: Map<Coordinate, T>
) : Region<T> {
    override val coordinates = map.keys.filterTo(mutableSetOf(), this::contains)

    override fun getOrNull(coordinate: Coordinate): T? {
        if (coordinate !in this) {
            return null
        }
        return map[coordinate]
    }
}
