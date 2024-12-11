package me.y9san9.aoc24

class CachedFunction<T>(map: Map<List<Any?>, Result<T>> = emptyMap()) {
    // Later I might want to make it LRU cache
    private val map = map.toMutableMap()

    operator fun contains(arguments: List<Any?>): Boolean {
        return arguments in map
    }

    operator fun get(arguments: List<Any?>): Result<T> {
        return map.getValue(arguments)
    }

    fun put(arguments: List<Any?>, value: Result<T>) {
        map[arguments] = value
    }

    @Deprecated(
        message = "Please provide at least a single parameter in order to cache function result",
        level = DeprecationLevel.ERROR
    )
    @Suppress("UNUSED_PARAMETER")
    operator fun invoke(block: () -> T): Nothing {
        error("Please provide at least a single parameter in order to cache function result")
    }

    inline operator fun invoke(vararg arguments: Any?, block: () -> T): T {
        val argumentsList = arguments.toList()
        if (argumentsList in this) return get(argumentsList).getOrThrow()

        var localReturn = false

        val result = try {
            runCatching(block).apply {
                localReturn = true
            }
        } finally {
            if (!localReturn) error("Non-local returns are not allowed! Otherwise, result will not be cached")
        }

        put(argumentsList, result)
        return result.getOrThrow()
    }

    fun toMap(): Map<List<Any?>, Result<T>> {
        return map.toMap()
    }
}

inline fun <K, V> cached(
    map: MutableMap<K, V>,
    key: K,
    block: () -> V
): V {
    val value = map.getOrPut(key, block)
    return value
}
