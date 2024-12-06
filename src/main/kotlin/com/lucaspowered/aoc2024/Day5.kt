package com.lucaspowered.aoc2024

import java.nio.charset.Charset
import java.nio.file.*
import kotlin.io.path.Path

//fun main() = day5()
private val input = Files.readAllBytes(Path("inputs/Day5.txt")).toString(Charset.defaultCharset())
private val order = Regex("\\d+\\|\\d+").findAll(input).map { Pair(it.value.split('|')[0], it.value.split('|')[1]) }
private val updates = input.split('\n').filter { it.contains(',') }.map { it.split(',').toTypedArray() }.toTypedArray()

fun day5() {
    // Part 1
    val good = updates.getGoodUpdates()
    println("Part 1: ${good.fold(0) { acc, e -> acc + e[e.lastIndex / 2].toInt() }}")

    // Part 2
    val corrected = updates.getBadUpdates()
    corrected.correctUpdates()
    println("Part 2: ${corrected.fold(0) { acc, e -> acc + e[e.lastIndex / 2].toInt() }}")
}

private fun Array<Array<String>>.getBadUpdates(): Array<Array<String>> {
    val ruleBreakers = mutableListOf<Array<String>>()
    for (i in this) {
        i.forEach { j ->
            val forbiddenBefore = order.filter { j == it.first }.map { it.second }
            forbiddenBefore.forEach { k ->
                if (i.indexOf(k) >= 0 && i.indexOf(j) >= 0 && i.indexOf(k) < i.indexOf(j)) {
                    ruleBreakers.add(i)
                }
            }
        }
    }
    return ruleBreakers.toSet().toTypedArray()
}

// This algorithm is very inefficient but it works :)
private fun Array<Array<String>>.correctUpdates() {
    this.forEach { i ->
        while (i.isBadUpdate()) {
            i.forEach { x ->
                val forbiddenBefore = order.filter { x == it.first }.map { it.second }
                forbiddenBefore.forEach { y ->
                    if (i.indexOf(y) >= 0 && i.indexOf(x) >= 0 && i.indexOf(y) < i.indexOf(x)) {
                        i.swap(x, y)
                    }
                }
            }
        }
    }
}

private fun Array<Array<String>>.getGoodUpdates(): Array<Array<String>> {
    val bad = this.getBadUpdates()
    return updates.filterNot { bad.contains(it) }.toTypedArray()
}

private fun Array<String>.isBadUpdate(): Boolean {
    this.forEach { x ->
        val forbiddenBefore = order.filter { x == it.first }.map { it.second }
        forbiddenBefore.forEach { y ->
            if (this.indexOf(y) >= 0 && this.indexOf(x) >= 0 && this.indexOf(y) < this.indexOf(x)) {
                return true
            }
        }
    }
    return false
}

private fun <T> Array<T>.swap(x: T, y: T) {
    val xidx = this.indexOf(x)
    val yidx = this.indexOf(y)
    this[xidx] = y
    this[yidx] = x
}