package me.y9san9.aoc24.day2

import me.y9san9.aoc24.deprecatedgrid.Grid
import me.y9san9.aoc24.input.readIntGridDeprecated
import me.y9san9.aoc24.program
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.sign

fun main() = program<Grid.Rows<Int>>(day = 2) {
    input { file -> file.readIntGridDeprecated().rows }

    fun List<Int>.countErrors(): Int {
        var errors = 0

        var previous = 0
        var previousDiff = 0

        for (i in indices) {
            val element = get(i)

            if (i > 0) {
                val diff = element - previous

                if (abs(diff) !in 1..3) {
                    errors++
                    continue
                }

                if (i > 1 && previousDiff.sign != diff.sign) {
                    errors++
                    continue
                }

                previousDiff = diff
            }

            previous = element
        }

        return errors
    }

    first {
        example = 2

        compute { rows ->
            rows.data.count { row -> row.countErrors() == 0 }
        }
    }

    second {
        example = 4

        compute { rows ->
            rows.data.count { row -> min(row.countErrors(), row.asReversed().countErrors()) <= 1 }
        }
    }
}