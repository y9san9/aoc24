package me.y9san9.aoc24.grid

import kotlin.math.sign

data class Coordinate(val x: Int, val y: Int) {
    operator fun rangeTo(coordinate: Coordinate): Iterable<Coordinate> {
        if (x != coordinate.x && y != coordinate.y) {
            error("Now rangeTo only works for straight lines. Attempt to rangeTo from $this to $coordinate")
        }

        return Iterable {
            val dX = (coordinate.x - x).sign
            val dY = (coordinate.y - y).sign

            var x: Int = x
            var y: Int = y

            iterator {
                while (true) {
                    yield(Coordinate(x, y))
                    if (x == coordinate.x && y == coordinate.y) {
                        break
                    }
                    x += dX
                    y += dY
                }
            }
        }
    }

    operator fun rangeUntil(coordinate: Coordinate): Iterable<Coordinate> {
        if (x != coordinate.x && y != coordinate.y) {
            error("Now rangeTo only works for straight lines. Attempt to rangeTo from $this to $coordinate")
        }

        return Iterable {
            val dX = (coordinate.x - x).sign
            val dY = (coordinate.y - y).sign

            var x: Int = x
            var y: Int = y

            iterator {
                while (true) {
                    if (x == coordinate.x && y == coordinate.y) {
                        break
                    }
                    yield(Coordinate(x, y))
                    x += dX
                    y += dY
                }
            }
        }
    }


    override fun toString(): String {
        return "($x, $y)"
    }
}
