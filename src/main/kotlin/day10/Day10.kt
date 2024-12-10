package me.y9san9.aoc24.day10

import me.y9san9.aoc24.grid.Coordinated
import me.y9san9.aoc24.grid.CoordinatedGrid
import me.y9san9.aoc24.grid.Grid
import me.y9san9.aoc24.grid.coordinated
import me.y9san9.aoc24.grid.filter
import me.y9san9.aoc24.grid.map
import me.y9san9.aoc24.grid.plusNeighbours
import me.y9san9.aoc24.input.readCharGrid
import me.y9san9.aoc24.program

fun main() = program<Grid<Int>>(day = 10) {
    input { file ->
        file.readCharGrid().map(Char::digitToInt)
    }

    tailrec fun followPaths(
        grid: CoordinatedGrid<Int>,
        paths: List<List<Coordinated<Int>>>,
        i: Int = 0,
        distinctBy: ((List<Coordinated<Int>>) -> Any?)?
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
            val trailheadStarts = input.coordinated()
                .filter { (_, height) -> height == 0 }
                .map(::listOf)

            followPaths(
                grid = input.coordinated(),
                paths = trailheadStarts,
                distinctBy = { path -> path.first() to path.last() }
            )
        }
    }

    second {
        example = 81

        compute { input ->
            val trailheadStarts = input.coordinated()
                .filter { (_, height) -> height == 0 }
                .map(::listOf)

            followPaths(
                grid = input.coordinated(),
                paths = trailheadStarts,
                distinctBy = null
            )
        }
    }
}
