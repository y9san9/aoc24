package me.y9san9.aoc24

fun <T> permutations(
    elements: Set<T>,
    targetSize: Int = elements.size
): Sequence<List<T>> {
    if (targetSize == 1) return elements.asSequence().map(::listOf)

    return sequence {
        for (permutation in permutations(elements, targetSize = targetSize - 1)) {
            for (element in elements) {
                yield(permutation + element)
            }
        }
    }
}


fun main() {
    println(permutations("ABC".toSet(), 2).toList())
}
