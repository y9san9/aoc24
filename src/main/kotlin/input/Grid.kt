package me.y9san9.aoc24.input

import me.y9san9.aoc24.ProgramContext
import me.y9san9.aoc24.grid.Grid

fun ProgramContext<Grid<Int>>.inputIntGrid(
    by: Regex = Regex("\\s+"),
) {
    inputIntGrid(by) { it }
}

inline fun <T> ProgramContext<T>.inputIntGrid(
    by: Regex = Regex("\\s+"),
    crossinline block: (Grid<Int>) -> T
) {
    inputLines { lines ->
        val rows = Grid.Rows(
            data = lines.map { line ->
                line.split(by).map { number ->
                    number.toInt()
                }
            }.toList()
        )

        val grid = Grid(rows)

        block(grid)
    }
}
