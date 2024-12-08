package com.lucaspowered.aoc2024

import com.github.michaelbull.itertools.*
import java.nio.file.Files
import java.nio.file.Path

fun main() = day7()

/*
   This is mega sketchy, but it works (allegedly).
   I had to use kotlin-itertools because I suck at combinatorics and all the stuff
 */
fun day7() {
    val input = Files.readString(Path.of("inputs/Day7.txt"))
    val array = input.split("\n")
        .map { Pair(it.split(':')[0].toLong(), it.split(' ').drop(1).map { it.toLong() }.toTypedArray()) }
        .toTypedArray()
    println("Part 1: ${array.fold(0L) { acc, i -> if (i.isPossible()) acc + i.first else acc }}")
    println("Part 2: ${array.fold(0L) { acc, i -> if (i.isPossible(true)) acc + i.first else acc }}")
}

private fun Pair<Long, Array<Long>>.isPossible(concat: Boolean = false): Boolean {
    var allCombos = sequenceOf<List<String>>()
    allCombos = if (!concat) (listOf("+", "*") * this.second.lastIndex).product()
    else (listOf("*", "+", "|") * this.second.lastIndex).product()
    allCombos.forEach { if ((this.second.joinToString(" ") + '/' + it.joinToString("")).evaluate() == this.first) return true }
    return false
}

private fun String.evaluate(): Long {
    val array = this.split("/").map { it.trim() }
    val operators = array[1].split("").filter { it.isNotEmpty() }
    var numbers = array[0].split(" ").toMutableList().map { it.toLong() }
    var accumulator = numbers[0]
    numbers = numbers.drop(1)
    if (numbers.isEmpty()) return accumulator else operators.forEachIndexed { idx, i ->
        if (i == "*") {
            accumulator *= numbers[idx]
        } else if (i == "+") {
            accumulator += numbers[idx]
        } else {
            accumulator = "${accumulator}${numbers[idx]}".toLong()
        }
    }
    return accumulator
}

private operator fun <T> List<T>.times(n: Int): List<List<T>> {
    val result = mutableListOf<List<T>>()
    (1..n).forEach { result.add(this) }
    return result.toList()
}