package me.y9san9.aoc24.day9

import me.y9san9.aoc24.program
import me.y9san9.aoc24.stub

sealed interface Entity {
    val index: Int
    val size: Int
}

data class Space(
    override val index: Int,
    override val size: Int
) : Entity

data class File(
    override val index: Int,
    val id: FileId,
    override val size: Int
) : Entity

data class FileId(val int: Int)

class FileSystem(entities: List<Entity>) {
    val data: MutableList<FileId?> = entities.flatMap { entity ->
        List(entity.size) {
            if (entity is File) entity.id else null
        }
    }.toMutableList()

    fun move(from: Int, to: Int) {
        val toId = data[to]
        data[to] = data[from]
        data[from] = toId
    }

    fun move(entity: Entity, to: Int) {
        repeat(entity.size) { i ->
            move(from = entity.index + i, to + i)
        }
    }

    fun tryMove(entity: Entity, to: Int): Boolean {
        repeat(entity.size) { i ->
            if (to + i >= data.size) return false
            if (data[to + i] != null) return false
        }
        move(entity, to)
        return true
    }

    fun checksum(): Long {
        return data.withIndex().sumOf { (i, fileIndex) ->
            if (fileIndex == null) {
                0
            } else {
                (i * fileIndex.int).toLong()
            }
        }
    }

    override fun toString(): String {
        return data.joinToString("") { it?.int?.toString() ?: '.'.toString() }
    }
}

fun main() = program<List<Entity>>(day = 9) {
    input { file ->
        var currentSize = 0

        file.readLines().first().mapIndexed { i, string ->
            val size = string.digitToInt()
            if (i % 2 == 0) {
                File(index = currentSize, id = FileId(i / 2), size = size)
            } else {
                Space(index = currentSize, size = size)
            }.apply {
                currentSize += size
            }
        }
    }

    fun findFilePointer(iterator: Iterator<IndexedValue<FileId?>>): Int {
        while (iterator.hasNext()) {
            val (i, value) = iterator.next()
            if (value != null) return i
        }
        stub()
    }

    first {
        example = 1928L

        compute { entities ->
            val fs = FileSystem(entities)
            val rtlIterator = fs.data.withIndex().reversed().iterator()

            for (i in fs.data.indices) {
                val entity = fs.data[i]
                if (entity != null) continue
                val rightIndex = findFilePointer(rtlIterator)
                if (rightIndex < i) break
                fs.move(rightIndex, i)
            }

            fs.checksum()
        }
    }

    second {
        example = 2858L

        compute { entities ->
            val fs = FileSystem(entities)
            val files = entities.filterIsInstance<File>()

            for (file in files.asReversed()) {
                for (i in fs.data.indices) {
                    if (i >= file.index) break
                    if (fs.tryMove(file, i)) break
                }
            }

            fs.checksum()
        }
    }
}
