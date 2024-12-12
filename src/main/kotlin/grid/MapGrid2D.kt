package me.y9san9.aoc24.grid

class MapGrid2D<out T>(
    override val bounds: Bounds2D?,
    map: Map<Point2D, T>
) : Grid2D<T> {
    private val map = if (bounds == null) map.toMap() else map.filter { (coordinate) -> coordinate in bounds }
    override val points: Set<Point2D> get() = map.keys

    override fun getOrNull(point: Point2D): T? {
        if (bounds != null && point !in bounds) return null
        return map[point]
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Grid2D<*>) return false
        if (this.bounds != other.bounds) return false
        if (this.points != other.points) return false
        return points.all { coordinate -> this[coordinate] == other[coordinate] }
    }

    // todo: hashcode is implemented incorrectly
    //  since it depends on the hashcode of the map and
    //  not on the structural equality
    override fun hashCode(): Int {
        var result = bounds?.hashCode() ?: 0
        result = 31 * result + map.hashCode()
        return result
    }

    override fun toString(): String {
        return "MapGrid(bounds=$bounds, map=$map)"
    }
}

class MutableMapGrid2D<T>(
    bounds: Bounds2D?,
    map: Map<Point2D, T>
) : MutableGrid2D<T> {
    private val map = if (bounds == null) {
        map.toMutableMap()
    } else {
        map.filterTo(mutableMapOf()) { (coordinate) -> coordinate in bounds }
    }

    override val points: Set<Point2D> get() = map.keys

    override var bounds: Bounds2D? = bounds
        set(value) {
            if (value != null) {
                val oldMap = map.toMap()
                map.clear()
                oldMap.filterTo(map) { (coordinate) -> coordinate in value }
            }
            field = value
        }

    override fun getOrNull(point: Point2D): T? {
        val bounds = bounds
        if (bounds != null && point !in bounds) return null
        return map[point]
    }

    override fun trySet(point: Point2D, value: T): Boolean {
        val bounds = bounds
        if (bounds != null && point !in bounds) {
            return false
        }
        map[point] = value
        return true
    }

    override fun remove(point: Point2D) {
        map.remove(point)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Grid2D<*>) return false
        if (this.points != other.points) return false
        if (this.bounds != other.bounds) return false
        return points.all { coordinate -> this[coordinate] == other[coordinate] }
    }

    // todo: hashcode is implemented incorrectly
    //  since it depends on the hashcode of the map and
    //  not on the structural equality
    override fun hashCode(): Int {
        var result = bounds?.hashCode() ?: 0
        result = 31 * result + map.hashCode()
        return result
    }

    override fun toString(): String {
        return "MapGrid(bounds=$bounds, map=$map)"
    }
}
