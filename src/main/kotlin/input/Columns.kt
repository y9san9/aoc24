package me.y9san9.aoc24.input

import me.y9san9.aoc24.ProgramContext

class Columns(val list: List<List<Int>>) {
    operator fun component1(): List<Int> = list[0]
    operator fun component2(): List<Int> = list[1]
    operator fun component3(): List<Int> = list[2]
    operator fun component4(): List<Int> = list[3]
    operator fun component5(): List<Int> = list[4]
}

fun ProgramContext<Columns>.inputColumns(split: String) {
    inputColumns(Regex.fromLiteral(split))
}

fun ProgramContext<Columns>.inputColumns(by: Regex = Regex("\\s+")) {
    inputLines { lines ->
        val result = mutableListOf<MutableList<Int>>()

        for (line in lines) {
            for ((i, number) in line.split(by).withIndex()) {
                if (result.size == i) result.add(mutableListOf())
                result[i].add(number.toInt())
            }
        }

        if (result.isNotEmpty()) {
            if (!result.all { list -> list.size == result[0].size }) {
                error("Malformed input for columns. List size differs: $result")
            }
        }

        Columns(result.map { list -> list.toList() })
    }
}
