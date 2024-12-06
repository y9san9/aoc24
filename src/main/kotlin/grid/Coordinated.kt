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

fun <T> Grid<T>.coordinated(): Grid<Coordinated<T>> {
    val map = coordinates.associateWith { coordinate ->
        Coordinated(coordinate, get(coordinate))
    }
    return Grid(bounds, map)
}

fun <T> Grid<Coordinated<T>>.raw(): Grid<T> {
    return map { (_, element) -> element }
}

fun <T> List<Coordinated<T>>.raw(): List<T> {
    return map { (_, element) -> element }
}
