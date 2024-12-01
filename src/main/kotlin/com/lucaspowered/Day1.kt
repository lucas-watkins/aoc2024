package com.lucaspowered

import java.nio.file.Files
import java.nio.file.Path
import kotlin.math.abs

fun main() {
   val input = mutableListOf<List<String>>()
    Files.readAllLines(Path.of("Day1.txt")).forEach {input.add(it.split("   "))}
    val list1 = mutableListOf<Int>()
    val list2 = mutableListOf<Int>()

    input.forEach {list1.add(it[0].trim().toInt()); list2.add(it[1].trim().toInt())}
    list1.sort()
    list2.sort()

    // Solution 1
    println("Total Distance: ${list1.zip(list2).map{abs(it.first - it.second)}.sum()}")

    // Solution 2
    var simularity = 0
    list1.forEach {e -> simularity += (e * list2.count{it == e})}
    println("Similarity: $simularity")
}
