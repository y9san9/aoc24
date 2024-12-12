package me.y9san9.aoc24.grid

data class Pointed2D<out T>(
    val point: Point2D,
    val element: T
) {
    val x: Int get() = point.x
    val y: Int get() = point.y

    operator fun component3(): Int {
        return point.x
    }

    operator fun component4(): Int {
        return point.y
    }

    override fun toString(): String {
        return "$point=$element"
    }
}

fun <T> Grid2D<T>.pointed(): PointedGrid2D<T> {
    return PointedGrid2D(raw = this)
}

fun <T> Grid2D<Pointed2D<T>>.raw(): Grid2D<T> {
    return if (this is PointedGrid2D<T>) raw else map { (_, element) -> element }
}

fun <T> List<Pointed2D<T>>.raw(): List<T> {
    return map { (_, element) -> element }
}

fun <T> List<Pointed2D<T>>.points(): List<Point2D> {
    return map { (coordinate) -> coordinate }
}


class PointedGrid2D<T>(val raw: Grid2D<T>) : Grid2D<Pointed2D<T>> {
    override val bounds: Bounds2D? get() = raw.bounds
    override val points: Set<Point2D> get() = raw.points
    override fun getOrNull(point: Point2D): Pointed2D<T>? {
        val element = raw.getOrNull(point) ?: return null
        return Pointed2D(point, element)
    }
}
