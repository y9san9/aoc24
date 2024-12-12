package me.y9san9.aoc24.input

import me.y9san9.aoc24.grid.Grid2D
import me.y9san9.aoc24.grid.map
import java.io.File

fun File.readCharGrid(separator: Regex? = null): Grid2D<Char> {
    return readGrid(separator).map { word -> word.single() }
}

fun File.readIntGrid(separator: Regex? = Regex("\\s+")): Grid2D<Int> {
    return readGrid(separator).map { word -> word.toInt() }
}

fun File.readGrid(separator: Regex? = Regex("\\s+")): Grid2D<String> {
    return useLines { lines ->
        val rows = Grid2D.Rows(
            data = lines.map { line ->
                if (separator != null) {
                    line.split(separator)
                } else {
                    line.map(Char::toString)
                }
            }.toList()
        )

        Grid2D(rows)
    }
}
