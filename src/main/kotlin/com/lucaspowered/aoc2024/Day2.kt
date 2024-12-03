package com.lucaspowered.aoc2024

import java.nio.file.*
import kotlin.math.abs

fun main() = day2()

fun day2() {
    val input = Files.readAllLines(Path.of("inputs/Day2.txt")).map { i -> i.split(" ").map { it.toInt() } }
    //println(input)
    // Part 1
    println(input.fold(0) { acc, report ->
        if ((report.isIncreasing() || report.isDecreasing()) && report.valsWithinTolerance(
                1..3
            )
        ) acc + 1 else acc
    })
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

private fun List<Int>.valsWithinTolerance(range: IntRange): Boolean {
    this.forEachIndexed { index, _ -> if (index < this.lastIndex) if (!range.contains(abs(this[index + 1] - this[index]))) return false }
    return true
}