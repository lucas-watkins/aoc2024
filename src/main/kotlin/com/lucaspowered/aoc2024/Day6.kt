package com.lucaspowered.aoc2024

import java.nio.file.*

fun main() = day6()

private data class Pair(var i: Int, var j: Int)

private val input = Files.readString(Path.of("inputs/Day6.txt"))
private val array: Array<CharArray>
    get() = input.trim().split('\n').map { it.toCharArray() }.toTypedArray()

fun day6() {
    val p1Array = array.copyOf()
    println("Part 1: ${p1Array.getDistinctPositions() + p1Array.fold(0) { acc, i -> acc + i.count { it == '^' } }}")

    var loops = 0
    array.forEachIndexed { i, _ -> array[i].forEachIndexed { j, _ -> if (array.isLoop(Pair(i, j))) loops++ } }
    println("Part 2: $loops")
}

private fun Array<CharArray>.isLoop(obst: Pair): Boolean {
    if (this[obst.i][obst.j] == '^') return false
    this[obst.i][obst.j] = '#'
    return this.getDistinctPositions() == -1
}

private fun Array<CharArray>.getDistinctPositions(): Int {
    val startingRow = this.find { it.contains('^') }!!
    val guardPosition = Pair(this.indexOf(startingRow), startingRow.indexOf('^'))

    for (@Suppress("UNUSED_PARAMETER") maxRuntimes in 0..this.fold(0) { acc, i -> acc + i.count { it == '#' } }) {
        while (guardPosition.i - 1 >= 0) {
            if (this[guardPosition.i - 1][guardPosition.j] == '#') break
            else {
                guardPosition.i--
                this[guardPosition.i][guardPosition.j] = 'X'
            }
            if (guardPosition.i - 1 < 0) return this.fold(0) { acc, i -> acc + i.count { it == 'X' } }
        }
        while (guardPosition.j + 1 <= this[guardPosition.i].lastIndex) {
            if (this[guardPosition.i][guardPosition.j + 1] == '#') break
            else {
                guardPosition.j++
                this[guardPosition.i][guardPosition.j] = 'X'
            }
            if (guardPosition.j + 1 > this[guardPosition.i].lastIndex) return this.fold(0) { acc, i -> acc + i.count { it == 'X' } }
        }
        while (guardPosition.i + 1 <= this.lastIndex) {
            if (this[guardPosition.i + 1][guardPosition.j] == '#') break
            else {
                guardPosition.i++
                this[guardPosition.i][guardPosition.j] = 'X'
            }
            if (guardPosition.i + 1 > this.lastIndex) return this.fold(0) { acc, i -> acc + i.count { it == 'X' } }
        }
        while (guardPosition.j - 1 >= 0) {
            if (this[guardPosition.i][guardPosition.j - 1] == '#') break
            else {
                guardPosition.j--
                this[guardPosition.i][guardPosition.j] = 'X'
            }
            if (guardPosition.j - 1 < 0) return this.fold(0) { acc, i -> acc + i.count { it == 'X' } }
        }
    }
    return -1
}