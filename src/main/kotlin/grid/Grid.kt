package me.y9san9.aoc24.grid

class Grid<out T> {
    val rows: Rows<T>
    val columns: Columns<T>

    constructor(rows: Rows<T>) {
        this.rows = rows

        val columns = List(rows[0].size) { x ->
            List(rows.size) { y ->
                rows[y][x]
            }
        }

        this.columns = Columns(columns)
    }

    constructor(columns: Columns<T>) {
        this.columns = columns

        val rows = List(columns[0].size) { x ->
            List(columns.size) { y ->
                columns[x][y]
            }
        }

        this.rows = Rows(rows)
    }

    val sizeX get() = columns.size
    val sizeY get() = rows.size

    operator fun get(x: Int, y: Int): T {
        return columns[x][y]
    }

    fun withIndex(): Grid<Indexed<T>> {
        val data = List(sizeY) { y ->
            List(sizeX) { x ->
                Indexed(x, y, get(x, y))
            }
        }
        return Grid(Rows(data))
    }

    inline fun <R> map(transform: (T) -> R): Grid<R> {
        val data = List(sizeY) { y ->
            List(sizeX) { x ->
                transform(get(x, y))
            }
        }
        return Grid(Rows(data))
    }

    fun transpose(): Grid<T> {
        return Grid(Rows(columns.data))
    }

    fun pretty(): String = buildString {
        for (row in rows) {
            for (element in row) {
                append(element)
                append('\t')
            }
            appendLine()
        }
    }

    data class Indexed<out T>(
        val x: Int,
        val y: Int,
        val data: T
    )

    class Rows<out T>(val data: List<List<T>>) {
        val size get() = data.size

        operator fun component1(): List<T> = data[0]
        operator fun component2(): List<T> = data[1]
        operator fun component3(): List<T> = data[2]
        operator fun component4(): List<T> = data[3]
        operator fun component5(): List<T> = data[4]

        operator fun get(i: Int): List<T> = data[i]
        operator fun iterator(): Iterator<List<T>> = data.iterator()
    }

    class Columns<out T>(val data: List<List<T>>) {
        val size get() = data.size

        operator fun component1(): List<T> = data[0]
        operator fun component2(): List<T> = data[1]
        operator fun component3(): List<T> = data[2]
        operator fun component4(): List<T> = data[3]
        operator fun component5(): List<T> = data[4]

        operator fun get(i: Int): List<T> = data[i]
        operator fun iterator(): Iterator<List<T>> = data.iterator()
    }
}
