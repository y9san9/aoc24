package me.y9san9.aoc24.input

import me.y9san9.aoc24.grid.Grid
import java.io.File

fun File.readCharGrid(separator: Regex? = Regex("\\s+")): Grid<Char> {
    return readGrid(separator).map { word -> word.toCharArray().single() }
}

fun File.readIntGrid(separator: Regex? = Regex("\\s+")): Grid<Int> {
    return readGrid(separator).map { word -> word.toInt() }
}

fun File.readGrid(separator: Regex? = Regex("\\s+")): Grid<String> {
    return useLines { lines ->
        val rows = Grid.Rows(
            data = lines.map { line ->
                if (separator != null) {
                    line.split(separator)
                } else {
                    line.map(Char::toString)
                }
            }.toList()
        )

        Grid(rows)
    }
}
