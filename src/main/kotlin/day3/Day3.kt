package me.y9san9.aoc24.day3

import me.y9san9.aoc24.program

sealed interface Expression {
    data class Mul(
        val a: Int,
        val b: Int
    ) : Expression
    data object Dont : Expression
    data object Do : Expression
}

val regex = Regex("""mul\((\d+),(\d+)\)|don't|do""")

fun main() = program<List<Expression>>(day = 3) {
    input { file ->
        file.readLines().flatMap(regex::findAll).map { match ->
            when (match.value) {
                "do" -> Expression.Do
                "don't" -> Expression.Dont
                else -> {
                    val (_, a, b) = match.groupValues
                    Expression.Mul(a.toInt(), b.toInt())
                }
            }
        }
    }

    first {
        example = 161

        compute { input ->
            input
                .filterIsInstance<Expression.Mul>()
                .sumOf { (a, b) -> a * b }
        }
    }

    second {
        example = 48

        compute { input ->
            var acc = 0
            var enabled = true

            for (expression in input) when (expression) {
                is Expression.Do -> enabled = true
                is Expression.Dont -> enabled = false
                is Expression.Mul -> acc += if (enabled) expression.a * expression.b else 0
            }

            acc
        }
    }
}
