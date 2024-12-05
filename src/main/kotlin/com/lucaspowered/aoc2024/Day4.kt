package com.lucaspowered.aoc2024

import java.nio.file.*

fun main() = day4()

fun day4() {
    val input = Files.readAllLines(Path.of("inputs/Day4.txt")).joinToString("\n")
    val array = input.split('\n').map { it.toCharArray() }
    var foundP1 = 0
    var foundP2 = 0
    for (i in array.indices) {
        for (j in array[i].indices) {
            if (array[i][j] == 'X') {
                // Down
                if (i + 1 <= array.lastIndex && array[i + 1][j] == 'M') if (i + 2 <= array.lastIndex && array[i + 2][j] == 'A') if (i + 3 <= array.lastIndex && array[i + 3][j] == 'S') foundP1++

                // Right
                if (j + 1 <= array[i].lastIndex && array[i][j + 1] == 'M') if (j + 2 <= array[i].lastIndex && array[i][j + 2] == 'A') if (j + 3 <= array[i].lastIndex && array[i][j + 3] == 'S') foundP1++

                // Left
                if (j - 1 >= 0 && array[i][j - 1] == 'M') if (j - 2 >= 0 && array[i][j - 2] == 'A') if (j - 3 >= 0 && array[i][j - 3] == 'S') foundP1++
                // Up
                if (i - 1 >= 0 && array[i - 1][j] == 'M') if (i - 2 >= 0 && array[i - 2][j] == 'A') if (i - 3 >= 0 && array[i - 3][j] == 'S') foundP1++

                // Diagonal Right Down
                if (i + 1 <= array.lastIndex && j + 1 <= array[i].lastIndex && array[i + 1][j + 1] == 'M') if (i + 2 <= array.lastIndex && j + 2 <= array[i].lastIndex && array[i + 2][j + 2] == 'A') if (i + 3 <= array.lastIndex && j + 3 <= array[i].lastIndex && array[i + 3][j + 3] == 'S') foundP1++

                // Diagonal Right Up
                if (i - 1 >= 0 && j + 1 <= array[i].lastIndex && array[i - 1][j + 1] == 'M') if (i - 2 >= 0 && j + 2 <= array[i].lastIndex && array[i - 2][j + 2] == 'A') if (i - 3 >= 0 && j + 3 <= array[i].lastIndex && array[i - 3][j + 3] == 'S') foundP1++

                // Diagonal Left Down
                if (i + 1 <= array.lastIndex && j - 1 >= 0 && array[i + 1][j - 1] == 'M') if (i + 2 <= array.lastIndex && j - 2 >= 0 && array[i + 2][j - 2] == 'A') if (i + 3 <= array.lastIndex && j - 3 >= 0 && array[i + 3][j - 3] == 'S') foundP1++

                // Diagonal Left Up
                if (i - 1 >= 0 && j - 1 >= 0 && array[i - 1][j - 1] == 'M') if (i - 2 >= 0 && j - 2 >= 0 && array[i - 2][j - 2] == 'A') if (i - 3 >= 0 && j - 3 >= 0 && array[i - 3][j - 3] == 'S') foundP1++
            }

            if (array[i][j] == 'A') {
                if (i + 1 <= array.lastIndex && j + 1 <= array[i].lastIndex && array[i + 1][j + 1] in arrayOf(
                        'M', 'S'
                    )
                ) if (i + 1 <= array.lastIndex && j - 1 >= 0 && array[i + 1][j - 1] in arrayOf(
                        'M', 'S'
                    )
                ) if (i - 1 >= 0 && array[i - 1][j - 1] in arrayOf(
                        'M', 'S'
                    )
                ) if (j + 1 <= array[i].lastIndex && array[i - 1][j + 1] in arrayOf(
                        'M', 'S'
                    )
                ) if (i + 1 <= array.lastIndex && j + 1 <= array[i].lastIndex && array[i - 1][j + 1] == array[i - 1][j - 1] && array[i + 1][j - 1] == array[i + 1][j + 1] && array[i - 1][j + 1] != array[i + 1][j - 1] && array[i - 1][j - 1] != array[i + 1][j + 1]) foundP2++
                else if (i + 1 <= array.lastIndex && j + 1 <= array[i].lastIndex && array[i + 1][j - 1] == array[i - 1][j - 1] && array[i + 1][j + 1] == array[i - 1][j + 1] && array[i - 1][j + 1] != array[i + 1][j - 1] && array[i - 1][j - 1] != array[i + 1][j + 1]) foundP2++
            }
        }
    }
    // I hate my life
    println("Part 1: $foundP1")
    println("Part 2: $foundP2")
}