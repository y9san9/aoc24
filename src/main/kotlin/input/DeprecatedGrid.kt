package me.y9san9.aoc24.input

import me.y9san9.aoc24.deprecatedgrid.Grid
import java.io.File

fun File.readCharGridDeprecated(separator: Regex? = Regex("\\s+")): Grid<Char> {
    return readGridDeprecated(separator).map { word -> word.toCharArray().single() }
}

fun File.readIntGridDeprecated(separator: Regex? = Regex("\\s+")): Grid<Int> {
    return readGridDeprecated(separator).map { word -> word.toInt() }
}

fun File.readGridDeprecated(separator: Regex? = Regex("\\s+")): Grid<String> {
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
