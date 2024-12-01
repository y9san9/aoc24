package me.y9san9.aoc24.day1

import me.y9san9.aoc24.input.Columns
import me.y9san9.aoc24.input.inputColumns
import me.y9san9.aoc24.program
import kotlin.math.abs
import kotlin.math.max

fun main() = program(day = 1) {
    inputColumns()

    first {
        example = 11

        compute { (left, right): Columns ->
            left.sorted()
                .zip(right.sorted())
                .sumOf { (left, right) -> abs(left - right) }
        }
    }

    second {
        example = 31

        compute { (left, right): Columns ->
            val count = IntArray(size = max(left.max(), right.max()) + 1)
            for (element in right) {
                count[element] += 1
            }
            left.sumOf { element -> element * count[element] }
        }
    }
}
