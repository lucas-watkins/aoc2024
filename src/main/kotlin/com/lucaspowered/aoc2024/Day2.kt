package com.lucaspowered.aoc2024

import java.nio.file.*
import kotlin.math.abs

fun main() = day2()

fun day2() {
    val input = Files.readAllLines(Path.of("inputs/Day2.txt")).map { i -> i.split(" ").map { it.toInt() } }

    // Part 1
    println("Part 1: ${input.fold(0) { acc, report -> if (isSafe(report)) acc + 1 else acc }}")

    // Part 2
    println("Part 2: ${input.fold(0) { acc, report -> if (isSafe(report, dampener = true)) acc + 1 else acc }}")
}

private fun List<Int>.isIncreasing(): Boolean {
    this.forEachIndexed { index, _ ->
        if (index < this.lastIndex) {
            if (this[index] < this[index + 1]) return false
        }
    }
    return true
}

private fun List<Int>.isDecreasing(): Boolean {
    this.forEachIndexed { index, _ ->
        if (index < this.lastIndex) {
            if (this[index] > this[index + 1]) return false
        }
    }
    return true
}

private fun List<Int>.exclude(index: Int): List<Int> {
    if (index == -1) return this
    return this.filterIndexed { idx, _ -> idx != index }
}

private fun isSafe(list: List<Int>, range: IntRange = 1..3, counter: Int = -1, dampener: Boolean = false): Boolean {
    val l = list
    if ((l.exclude(counter).isIncreasing() || l.exclude(counter).isDecreasing()) && l.exclude(counter)
            .filterIndexed { idx, _ ->
                (idx < l.exclude(counter).lastIndex && !range.contains(
                    abs(
                        l.exclude(counter)[idx] - l.exclude(counter)[idx + 1]
                    )
                ))
            }.isEmpty()
    ) return true
    if (!dampener) return false
    return if (counter == l.lastIndex) false else isSafe(list = l, counter = counter + 1, dampener = dampener)
}