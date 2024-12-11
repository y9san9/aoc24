package me.y9san9.aoc24.day11

import me.y9san9.aoc24.CachedFunction
import me.y9san9.aoc24.program

data class Stone(val number: Long, val ttl: Int) {
    fun next(): List<Stone> = when {
        number == 0L -> singleNext(number = 1)

        number.toString().length % 2 == 0 -> {
            val halfLength = number.toString().length / 2

            listOf(
                next(number.toString().take(halfLength).toLong()),
                next(number.toString().takeLast(halfLength).toLong())
            )
        }

        else -> singleNext(number = number * 2024)
    }

    private fun singleNext(number: Long): List<Stone> {
        return listOf(next(number))
    }

    private fun next(number: Long): Stone {
        return Stone(number, ttl = ttl - 1)
    }

    init {
        require(number >= 0)
    }
}

fun main() = program<List<Long>>(day = 11) {
    input { file ->
        file.readLines().first().split(" ").map(String::toLong)
    }

    val cached = CachedFunction<Long>()
    fun blink(stone: Stone): Long = cached(stone) {
        if (stone.ttl == 0) return@cached 1
        stone.next().sumOf { stone -> blink(stone) }
    }

    first {
        example = 55312L

        compute { input ->
            input.sumOf { number -> blink(Stone(number, ttl = 25)) }
        }
    }

    second {
        example = 65601038650482L

        compute { input ->
            input.sumOf { number -> blink(Stone(number, ttl = 75)) }
        }
    }
}
