package me.y9san9.aoc24.input

import me.y9san9.aoc24.grid.Grid
import java.io.File

fun File.readCharGrid(by: Regex = Regex("\\s+")): Grid<Char> {
    return readGrid(by).map { word -> word.toCharArray().single() }
}

fun File.readIntGrid(by: Regex = Regex("\\s+")): Grid<Int> {
    return readGrid(by).map { word -> word.toInt() }
}

fun File.readGrid(by: Regex = Regex("\\s+")): Grid<String> {
    return useLines { lines ->
        val rows = Grid.Rows(
            data = lines.map { line ->
                line.split(by).map { word -> word }
            }.toList()
        )

        Grid(rows)
    }
}
