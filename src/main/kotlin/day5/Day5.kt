package me.y9san9.aoc24.day5

import me.y9san9.aoc24.program

data class Input(
    val orderingRules: List<Pair<Int, Int>>,
    val updates: List<List<Int>>
) {
    private val rulesMap = orderingRules.groupBy(
        keySelector = { (before) -> before },
        valueTransform = { (_, after) -> after }
    )

    val comparator: Comparator<Int> = Comparator { left, right ->
        val rightRule = rulesMap[right]
        val leftRule = rulesMap[left]

        when {
            rightRule != null && left in rightRule -> 1
            leftRule != null && right in leftRule -> -1
            else -> 0
        }
    }

    private fun isCorrect(left: Int, right: Int): Boolean {
        val rules = rulesMap[right] ?: return true
        return left !in rules
    }

    fun isCorrect(updates: List<Int>): Boolean {
        return updates.zipWithNext().all { (left, right) -> isCorrect(left, right) }
    }
}

fun main() = program<Input>(day = 5) {
    input { file ->
        val (rulesText, updatesText) = file.readText().split("\n\n")

        val rules = rulesText.lines().map { line ->
            val (left, right) = line.split('|').map(String::toInt)
            left to right
        }

        val updates = updatesText.lines().map { line ->
            line.split(',').map(String::toInt)
        }

        Input(rules, updates)
    }

    first {
        example = 143

        compute { input ->
            input.updates.filter(input::isCorrect).sumOf { updates ->
                updates[updates.size / 2]
            }
        }
    }

    second {
        example = 123

        compute { input ->
            input.updates.filterNot(input::isCorrect).map { line ->
                line.sortedWith(input.comparator)
            }.sumOf { updates ->
                updates[updates.size / 2]
            }
        }
    }
}
