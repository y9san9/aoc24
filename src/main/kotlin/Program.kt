package me.y9san9.aoc24

import java.io.File

class ProgramContext<T> {
    private var input: ((File) -> T)? = null

    fun input(block: (File) -> T) {
        this.input = block
    }

    private var first: Program.Part<T>? = null

    fun first(block: PartContext<T>.() -> Unit) {
        val context = PartContext<T>()
        context.apply(block)
        this.first = context.build()
    }

    private var second: Program.Part<T>? = null

    fun second(block: PartContext<T>.() -> Unit) {
        val context = PartContext<T>()
        context.apply(block)
        this.second = context.build()
    }

    fun build(): Program<T> {
        return Program(
            input = input ?: error("Please setup a way to parse input"),
            first = first,
            second = second
        )
    }

    class PartContext<T> {
        var example: Any? = null

        private var compute: ((T) -> Any)? = null

        fun compute(block: (T) -> Any) {
            this.compute = block
        }

        fun build(): Program.Part<T> {
            return Program.Part(
                example = example ?: error("Provide an example value"),
                compute = compute ?: error("Provide a way to compute part")
            )
        }
    }
}

data class Program<T>(
    val input: (File) -> T,
    val first: Part<T>? = null,
    val second: Part<T>? = null
) {
    data class Part<T>(
        val example: Any,
        val compute: (T) -> Any
    )
}

inline fun <T> program(
    day: Int,
    block: ProgramContext<T>.() -> Unit
) {
    val context = ProgramContext<T>()
    context.apply(block)

    val program = context.build()

    val dayDir = File(System.getProperty("user.dir"), "src/main/kotlin/day$day")
    val inputFile = File(dayDir, "input.txt")

    val exampleFile = File(dayDir, "example.txt")
    if (!exampleFile.exists()) {
        error("Provide an example file")
    }

    val first = program.first
    if (first != null) {
        val example = program.input(exampleFile)
        val exampleActual = first.compute(example)
        if (exampleActual != first.example) {
            silentExit("Part 1: Example calculation didn't match!\nExpected: ${first.example}\nActual: $exampleActual")
        }
        val input = if (inputFile.exists() && inputFile.length() != 0L) {
            program.input(inputFile)
        } else {
            null
        }
        if (input == null) {
            println("Part 1: $exampleActual (Example)")
        } else {
            val result = first.compute(input)
            println("Part 1: $result")
        }
    }

    val second = program.second
    if (second != null) {
        val example = program.input(exampleFile)
        val exampleActual = second.compute(example)
        if (exampleActual != second.example) {
            silentExit("Part 2: Example calculation didn't match!\nExpected: ${second.example}\nActual: $exampleActual")
        }
        val input = if (inputFile.exists() && inputFile.length() != 0L) {
            program.input(inputFile)
        } else {
            null
        }
        if (input == null) {
            println("Part 2: $exampleActual (Example)")
        } else {
            val result = second.compute(input)
            println("Part 2: $result")
        }
    }

    if (!inputFile.exists()) {
        println("For the actual data, please put input.txt near the Kotlin file")
        println("Note that distributing inputs publicly is forbidden by AoC License: https://www.reddit.com/r/adventofcode/wiki/faqs/copyright/inputs/")
    }
}
