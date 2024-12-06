package me.y9san9.aoc24.grid

data class MapGrid<out T>(
    override val bounds: Bounds?,
    private val map: Map<Coordinate, T>
) : Grid<T> {
    override val coordinates = map.keys.filterTo(mutableSetOf(), this::contains)

    override fun getOrNull(coordinate: Coordinate): T? {
        if (coordinate !in this) {
            return null
        }
        return map[coordinate]
    }
}

data class MutableMapGrid<T>(
    override var bounds: Bounds?,
    private val map: MutableMap<Coordinate, T>
) : MutableGrid<T> {
    override val coordinates get() = map.keys.filterTo(mutableSetOf(), this::contains)

    override fun getOrNull(coordinate: Coordinate): T? {
        if (coordinate !in this) {
            return null
        }
        return map[coordinate]
    }

    override fun trySet(coordinate: Coordinate, value: T): Boolean {
        val bounds = bounds
        if (bounds != null && coordinate !in bounds) {
            return false
        }
        map[coordinate] = value
        return true
    }
}
