package days

import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { value ->
            val numbers = value.filter { pre -> pre.isDigit() }
            "${numbers.first()}${numbers.last()}".toInt()
        }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
