package me.y9san9.aoc24.grid

data class Coordinated<out T>(
    val coordinate: Coordinate,
    val element: T
) {
    val x: Int get() = coordinate.x
    val y: Int get() = coordinate.y

    operator fun component3(): Int {
        return coordinate.x
    }

    operator fun component4(): Int {
        return coordinate.y
    }

    override fun toString(): String {
        return "$coordinate=$element"
    }
}

fun <T> Grid<T>.coordinated(): CoordinatedGrid<T> {
    return CoordinatedGrid(raw = this)
}

fun <T> Grid<Coordinated<T>>.raw(): Grid<T> {
    return if (this is CoordinatedGrid<T>) raw else map { (_, element) -> element }
}

fun <T> List<Coordinated<T>>.raw(): List<T> {
    return map { (_, element) -> element }
}

fun <T> List<Coordinated<T>>.coordinates(): List<Coordinate> {
    return map { (coordinate) -> coordinate }
}


class CoordinatedGrid<T>(val raw: Grid<T>) : Grid<Coordinated<T>> {
    override val bounds: Bounds? get() = raw.bounds
    override val coordinates: Set<Coordinate> get() = raw.coordinates
    override fun getOrNull(coordinate: Coordinate): Coordinated<T>? {
        val element = raw.getOrNull(coordinate) ?: return null
        return Coordinated(coordinate, element)
    }
}
