package me.y9san9.aoc24.day7

import me.y9san9.aoc24.permutations
import me.y9san9.aoc24.program

fun interface MathOperation {
    fun apply(left: Long, right: Long): Long

    companion object {
        val Plus = MathOperation { left, right -> left + right}
        val Times = MathOperation { left, right -> left * right }
        val Concat = MathOperation { left, right -> "$left$right".toLong() }
    }
}

data class TestableEquation(val result: Long, val members: List<Long>) {
    fun test(operations: List<MathOperation>): Boolean {
        require(operations.size == members.size - 1)
        val operation = operations.iterator()
        return members.reduce { acc, number -> operation.next().apply(acc, number) } == result
    }
}

fun main() = program<List<TestableEquation>>(day = 7) {
    input { file ->
        file.readLines().map { line ->
            val (result, members) = line.split(": ", limit = 2)
            val membersLong = members.split(" ").map(String::toLong)
            TestableEquation(result.toLong(), membersLong)
        }
    }

    first {
        example = 3749L

        compute { equations ->
            equations.filter { equation ->
                val operationsSize = equation.members.size - 1
                val operations = setOf(MathOperation.Plus, MathOperation.Times)
                val permutations = permutations(operations, operationsSize)

                permutations.any(equation::test)
            }.sumOf { equation -> equation.result }
        }
    }

    second {
        example = 11387L

        compute { equations ->
            equations.filter { equation ->
                val operationsSize = equation.members.size - 1
                val operations = setOf(MathOperation.Plus, MathOperation.Times, MathOperation.Concat)
                val permutations = permutations(operations, operationsSize)

                permutations.any(equation::test)
            }.sumOf { equation -> equation.result }
        }
    }
}
