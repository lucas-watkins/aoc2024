package com.lucaspowered.aoc2024

import java.io.Serializable
import java.nio.charset.Charset
import java.nio.file.*
import kotlin.io.path.Path

fun main() = day5()

fun day5() {
    val input = Files.readAllBytes(Path("inputs/Day5.txt")).toString(Charset.defaultCharset())

    //val input = "7|53\n 97|13\n97|61\n97|47\n75|29\n61|13\n75|53\n29|13\n97|29\n53|29\n61|53\n97|53\n61|29\n47|13\n75|47\n97|75\n47|61\n75|61\n47|29\n75|13\n53|13\n\n75,47,61,53,29\n97,61,53,29,13\n75,29,13\n75,97,47,61,53\n61,13,29\n97,13,75,29,47\n"
    val order = Regex("\\d+\\|\\d+").findAll(input).map {Pair(it.value.split('|')[0], it.value.split('|')[1])}
    val updates = input.split('\n').filter {it.contains(',')}.map {it.split(',')}
    //order.forEach { println("[${it.first}, ${it.second}]") }
    //updates.forEach {println(it)}

    val ruleBreakers = mutableListOf<List<String>>()
    for (i in updates){
        i.forEach { j ->
            val forbiddenBefore = order.filter { j == it.first }.map {it.second}
            forbiddenBefore.forEach {
                k ->
                if (i.indexOf(k) >= 0  && i.indexOf(j) >= 0 && i.indexOf(k) < i.indexOf(j)){
                    ruleBreakers.add(i)
                }
            }
        }
    }
    //println(ruleBreakers.toSet())
    val obedient = updates.filterNot {ruleBreakers.contains(it)}
    //println(obedient)
    println(obedient.fold(0) {acc, e -> acc + e[e.lastIndex / 2].toInt()})
}