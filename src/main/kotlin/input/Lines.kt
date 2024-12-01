package me.y9san9.aoc24.input

import me.y9san9.aoc24.ProgramContext

inline fun <T> ProgramContext<T>.inputLines(
    crossinline block: (Sequence<String>) -> T
) {
    input { file ->
        file.useLines(block = block)
    }
}

inline fun <T> ProgramContext<T>.inputNumberLines(
    crossinline block: (Sequence<Int>) -> T
) {
    inputLines { sequence ->
        block(sequence.map { string -> string.toInt() })
    }
}
