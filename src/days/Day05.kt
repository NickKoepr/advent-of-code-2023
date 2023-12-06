package days

import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        var t = ""
        var a = false
        input.forEach { row ->
            if (row.contains("map")) {
                a = true
                t += row
            }
            if (a) {
                if (row.isEmpty()) {
                    a = false
                    t.toMap()
                } else {
                    t += row
                }
            }
        }
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day05_test")
    check(part1(testInput1) == 35)

    val testInput2 = readInput("Day05_test")
    check(part2(testInput2) == 0)

    val input1 = readInput("Day05")
    part1(input1).println()
    val input2 = readInput("Day05")
    part2(input2).println()
}

data class Map(val sourceRange: IntRange, val destinationRange: IntRange, val rangeLength: Int)

fun String.toMap(): Map {
    println(this)
    val numbers = split(":")[1].split(" ").map { it }
    println(numbers)
    return Map(1..2, 1..2, 1)
}

fun getSeeds() {

}