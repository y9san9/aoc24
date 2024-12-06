package me.y9san9.aoc24.day6

import me.y9san9.aoc24.grid.Coordinate
import me.y9san9.aoc24.grid.Coordinated
import me.y9san9.aoc24.grid.Grid
import me.y9san9.aoc24.grid.asSequence
import me.y9san9.aoc24.grid.coordinated
import me.y9san9.aoc24.grid.count
import me.y9san9.aoc24.grid.firstNotNullOf
import me.y9san9.aoc24.grid.map
import me.y9san9.aoc24.grid.pretty
import me.y9san9.aoc24.grid.raw
import me.y9san9.aoc24.grid.ray
import me.y9san9.aoc24.grid.toGrid
import me.y9san9.aoc24.grid.toMutableGrid
import me.y9san9.aoc24.input.readCharGrid
import me.y9san9.aoc24.program

enum class Guard(val dX: Int, val dY: Int) {
    Top(0, -1) {
        override fun rotate(): Guard = Right
    },
    Right(1, 0) {
        override fun rotate(): Guard = Bottom
    },
    Bottom(0, 1) {
        override fun rotate(): Guard = Left
    },
    Left(-1, 0) {
        override fun rotate(): Guard = Top
    };

    abstract fun rotate(): Guard
}

data class Lab(val grid: Grid<Coordinated<Char>>) {
    val guard: Coordinated<Guard> = grid.firstNotNullOf { (coordinate, char) ->
        val guard = when (char) {
            '^' -> Guard.Top
            '>' -> Guard.Right
            'v' -> Guard.Bottom
            '<' -> Guard.Left
            else -> null
        }
        if (guard != null) Coordinated(coordinate, guard) else null
    }

    val guardPath: Sequence<Coordinate> = sequence {
        var (coordinate, guard) = guard
        yield(coordinate)

        while (true) {
            val ray = grid.ray(coordinate, guard.dX, guard.dY).toList()
            val obstacle = ray.takeWhile { (_, char) -> char != '#' }
            guard = guard.rotate()
            if (obstacle.size == 1) continue
            coordinate = obstacle.last().coordinate
            yield(coordinate)
            if (ray.size == obstacle.size) {
                break
            }
        }
    }

    override fun toString(): String {
        return grid.map { (_, char) -> char }.pretty()
    }
}

fun main() = program<Lab>(day = 6) {
    input { file ->
        Lab(file.readCharGrid().coordinated())
    }

    first {
        example = 41

        compute { input ->
            val visitedGrid = input.grid.raw().toMutableGrid()

            input.guardPath.toList().zipWithNext { start, end ->
                for (coordinate in start..end) {
                    visitedGrid[coordinate] = 'X'
                }
            }

            visitedGrid.count { char -> char == 'X' }
        }
    }

    // Indeed, this is not a clever solution. I spent 6 hours trying to find the normal one. I am so stupid.
    // This was written in 10 mins and shouldn't exist
    second {
        example = 6

        compute { input ->
            val grids = input.grid.asSequence().filter { (_, char) -> char == '.' }.map { (coordinate) ->
                val grid = input.grid.raw().toMutableGrid()
                grid[coordinate] = '#'
                grid.toGrid()
            }

            grids.count { grid ->
                val lab = Lab(grid.coordinated())

                val acc = mutableListOf<Coordinate>()

                for (coordinate in lab.guardPath) {
                    if (coordinate in acc) {
                        return@count true
                    }
                    acc += coordinate
                }

                false
            }
        }
    }
}
