package me.y9san9.aoc24.grid

fun Grid<*>.pretty(): String = buildString {
    val bounds = bounds ?: error("Can't print infinite grid")

    for ((y, row) in bounds.rows()) {
        for (x in row) {
            append(getOrNull(x, y))
            append('\t')
        }
        appendLine()
    }
}
