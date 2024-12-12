package me.y9san9.aoc24.grid

import me.y9san9.aoc24.vector.Vector2D
import kotlin.math.sign

data class Point2D(val x: Int, val y: Int) {
    operator fun rangeTo(point: Point2D): Iterable<Point2D> {
        if (x != point.x && y != point.y) {
            error("Now rangeTo only works for straight lines. Attempt to rangeTo from $this to $point")
        }

        return Iterable {
            val dX = (point.x - x).sign
            val dY = (point.y - y).sign

            var x: Int = x
            var y: Int = y

            iterator {
                while (true) {
                    yield(Point2D(x, y))
                    if (x == point.x && y == point.y) {
                        break
                    }
                    x += dX
                    y += dY
                }
            }
        }
    }

    operator fun rangeUntil(point: Point2D): Iterable<Point2D> {
        if (x != point.x && y != point.y) {
            error("Now rangeTo only works for straight lines. Attempt to rangeTo from $this to $point")
        }

        return Iterable {
            val dX = (point.x - x).sign
            val dY = (point.y - y).sign

            var x: Int = x
            var y: Int = y

            iterator {
                while (true) {
                    if (x == point.x && y == point.y) {
                        break
                    }
                    yield(Point2D(x, y))
                    x += dX
                    y += dY
                }
            }
        }
    }

    operator fun plus(point: Point2D) {

    }

    operator fun plus(direction: Vector2D): Point2D {
        return copy(x = x + direction.dX, y = y + direction.dY)
    }

    override fun toString(): String {
        return "($x, $y)"
    }
}
