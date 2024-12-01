package me.y9san9.aoc24

import kotlin.system.exitProcess

fun silentExit(
    message: String,
    status: Int = 1
): Nothing {
    System.err.println(message)
    exitProcess(status)
}
