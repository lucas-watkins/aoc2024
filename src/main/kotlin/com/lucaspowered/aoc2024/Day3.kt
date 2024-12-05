package com.lucaspowered.aoc2024

import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Path

//fun main() = day3()

fun day3() {
     /*
       Well I should probably write something each day. Today I found out that regex is pretty cool.
       I also really need to improve my sleep schedule. I'll write another comment tomorrow as an update to that.
     */

    val input = Files.readAllBytes(Path.of("inputs/Day3.txt")).toString(Charset.defaultCharset())
    val p1Input = Regex("mul\\([0-9]+,[0-9]+\\)").findAll(input)
    val p2Input = Regex("mul\\([0-9]+,[0-9]+\\)|do\\(\\)|don't\\(\\)").findAll(input)
    val numRegex = Regex("[0-9]+")

    // Part 1
    println("Part 1: ${
        p1Input.fold(0) { acc, i ->
            acc + numRegex.findAll(i.value).fold(1) { a, j -> a * j.value.toInt() }
        }
    }")

    // Part 2
    var includeResult = true
    println("Part 2: ${
        p2Input.fold(0) { acc, i ->
            if (i.value == "do()") {
                includeResult = true
                acc + 0
            } else if (i.value == "don't()") {
                includeResult = false
                acc + 0
            } else if (includeResult) {
                acc + numRegex.findAll(i.value).fold(1) { a, j -> a * j.value.toInt() }
            } else {
                acc + 0
            }
        }
    }")
}