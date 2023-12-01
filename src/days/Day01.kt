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
        val numbers = hashMapOf(
            "zero" to 0,
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eighth" to 8,
            "nine" to 9
        )
        return input.sumOf { row ->
            var item = row
            numbers.forEach { (key, value) ->
                item = item.replace(key, value.toString())
            }
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
