package me.y9san9.aoc24.day1

import me.y9san9.aoc24.deprecatedgrid.Grid
import me.y9san9.aoc24.input.readIntGridDeprecated
import me.y9san9.aoc24.program
import kotlin.math.abs
import kotlin.math.max

fun main() = program<Grid.Columns<Int>>(day = 1) {
    input { file -> file.readIntGridDeprecated().columns }

    first {
        example = 11

        compute { (left, right) ->
            left.sorted()
                .zip(right.sorted())
                .sumOf { (left, right) -> abs(left - right) }
        }
    }

    second {
        example = 31

        compute { (left, right) ->
            val count = IntArray(size = max(left.max(), right.max()) + 1)
            for (element in right) {
                count[element] += 1
            }
            left.sumOf { element -> element * count[element] }
        }
    }
}
