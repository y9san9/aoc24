package me.y9san9.aoc24.day10

import me.y9san9.aoc24.grid.Pointed2D
import me.y9san9.aoc24.grid.PointedGrid2D
import me.y9san9.aoc24.grid.Grid2D
import me.y9san9.aoc24.grid.pointed
import me.y9san9.aoc24.grid.filter
import me.y9san9.aoc24.grid.map
import me.y9san9.aoc24.grid.plusNeighbours
import me.y9san9.aoc24.input.readCharGrid
import me.y9san9.aoc24.program

fun main() = program<Grid2D<Int>>(day = 10) {
    input { file ->
        file.readCharGrid().map(Char::digitToInt)
    }

    tailrec fun followPaths(
        grid: PointedGrid2D<Int>,
        paths: List<List<Pointed2D<Int>>>,
        i: Int = 0,
        distinctBy: ((List<Pointed2D<Int>>) -> Any?)?
    ): Int {
        if (i == 9) return paths.size

        var nextPaths = paths.flatMap { path ->
            val (coordinate, height) = path.last()
            val neighbours = grid.plusNeighbours(coordinate).filter { (_, neighbourHeight) ->
                neighbourHeight == height + 1
            }
            neighbours.map { neighbour ->
                path + neighbour
            }
        }

        if (distinctBy != null) {
            nextPaths = nextPaths.distinctBy(distinctBy)
        }

        val nextI = i + 1

        return followPaths(grid, nextPaths, nextI, distinctBy)
    }

    first {
        example = 36

        compute { input ->
            val trailheadStarts = input.pointed()
                .filter { (_, height) -> height == 0 }
                .map(::listOf)

            followPaths(
                grid = input.pointed(),
                paths = trailheadStarts,
                distinctBy = { path -> path.first() to path.last() }
            )
        }
    }

    second {
        example = 81

        compute { input ->
            val trailheadStarts = input.pointed()
                .filter { (_, height) -> height == 0 }
                .map(::listOf)

            followPaths(
                grid = input.pointed(),
                paths = trailheadStarts,
                distinctBy = null
            )
        }
    }
}
