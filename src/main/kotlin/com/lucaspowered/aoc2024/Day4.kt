package com.lucaspowered.aoc2024

import java.nio.file.*

fun main() = day4()

fun day4() {
    val sb = StringBuilder()
    //val input = "MMMSXXMASM\nMSAMXMSMSA\nAMXSXMAAMM\nMSAMASMSMX\nXMASAMXAMM\nXXAMMXXAMA\nSMSMSASXSS\nSAXAMASAAA\nMAMMMXMMMM\nMXMXAXMASX"
    //val input = "....XXMAS.\n.SAMXMS...\n...S..A...\n..A.A.MS.X\nXMASAMX.MM\nX.....XA.A\nS.S.S.S.SS\n.A.A.A.A.A\n..M.M.M.MM\n.X.X.XMASX"
    val xmasRegex = Regex("XMAS")
    val input = Files.readAllLines(Path.of("inputs/Day4.txt")).joinToString("\n")
    sb.append(input.replace("\n", ""))
    val array = input.split('\n').map{it.toCharArray()}.toTypedArray()
    sb.append('.')
    array.forEachIndexed{idx1, _ -> (0..array.lastIndex).forEachIndexed{idx2, _ -> sb.append(array[idx2][idx1])}}
    sb.append('.')
    array.diagonallyRight().forEach{r -> r.forEach {sb.append(it)}}
    sb.append('.')
    array.diagonallyLeft().forEach{l -> l.forEach {sb.append(it)}}
    sb.append('.')

    println(xmasRegex.findAll(sb).count() +  xmasRegex.findAll(sb.reversed()).count())
}

private fun Array<CharArray>.diagonallyLeft(): Array<Array<Char>> {
    val result = mutableListOf<Array<Char>>()
    result.addAll(this.diagonalLeftHalfSlice())
    result.addAll(this.reversedArray().map{it.reversedArray()}.toTypedArray().diagonalLeftHalfSlice().map{it.reversedArray()}.toTypedArray().reversedArray().drop(1).toTypedArray())
    return result.toTypedArray()
}

private fun Array<CharArray>.diagonalLeftHalfSlice(): Array<Array<Char>> {
    val result = mutableListOf<Array<Char>>()
    for (i in this.indices) {
        val m = mutableListOf<Char>()
        for (j in this[i].indices) {
            if (j <= i)
                m.add(this[i - j][j])
            else
                break
        }
        result.add(m.toTypedArray())
    }
    return result.toTypedArray()
}

private fun Array<CharArray>.diagonallyRight(): Array<Array<Char>> {
    return this.map{it.reversedArray()}.toTypedArray().reversedArray().diagonallyLeft().map{it.reversedArray()}.toTypedArray()
}