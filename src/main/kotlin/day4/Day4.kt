package me.y9san9.aoc24.day4

import me.y9san9.aoc24.deprecatedgrid.Grid
import me.y9san9.aoc24.deprecatedgrid.rayString
import me.y9san9.aoc24.input.readCharGridDeprecated
import me.y9san9.aoc24.program

fun main() = program<Grid<Char>>(day = 4) {
    input { file ->
        file.readCharGridDeprecated(separator = null)
    }

    first {
        example = 18

        compute { input ->
            input.withIndex().flatten().sumOf { (x, y) ->
                (-1..1).sumOf { dX ->
                    (-1..1).count { dY ->
                        input.rayString(x, y, dX, dY, length = 4) == "XMAS"
                    }
                }
            }
        }
    }

    val mas = "MAS"

    fun checkMAS(
        grid: Grid<Char>,
        x: Int,
        y: Int,
        directionX: Int,
        directionY: Int
    ): Boolean {
        return (-1..1).all { i ->
            grid.getOrNull(x + directionX * i, y + directionY * i) == mas[i + 1]
        }
    }

    second {
        example = 9

        compute { input ->
            input.withIndex().flatten().count { (x, y) ->
                if (!checkMAS(input, x, y, directionX = 1, directionY = 1)) {
                    if (!checkMAS(input, x, y, directionX = -1, directionY = -1)) {
                        return@count false
                    }
                }
                if (!checkMAS(input, x, y, directionX = 1, directionY = -1)) {
                    if (!checkMAS(input, x, y, directionX = -1, directionY = 1)) {
                        return@count false
                    }
                }
                true
            }
        }
    }
}
