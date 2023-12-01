package days

import println
import readInput

fun main() {

    fun getNumberFromString(value: String): Int {
        val numbers = value.filter { pre -> pre.isDigit() }
        return "${numbers.first()}${numbers.last()}".toInt()
    }

    /**
     * Function calculates the number based on the given string. The number can be represented as
     * a normal number (1, 2, 3), or as a string (like zero, one, two). When there is no number found, null will be
     * returned.
     */
    fun calcNumber(row: String, startIndex: Int, endIndex: Int): String? {
        val numbers = hashMapOf(
            "zero" to 0,
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9
        )

        val sub = row.substring(startIndex, endIndex)
        val did = sub.filter { it.isDigit() }
        if (did.length == 1) {
            return did[0].toString()
        } else {
            val x = numbers.keys.filter { sub.contains(it) }
            if (x.size == 1) {
                return numbers[x[0]].toString()
            }
        }
        return null
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { value ->
            getNumberFromString(value)
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { row ->
            var firstNumber = ""
            var secondNumber = ""

            for (index in 1..row.lastIndex) {
                if (firstNumber == "") calcNumber(row, 0, index)?.let {
                    firstNumber = it
                }

                if (secondNumber == "") calcNumber(row, row.length - index, row.length)?.let {
                    secondNumber = it
                }
            }
            getNumberFromString(firstNumber + secondNumber)
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day01_1_test")
    check(part1(testInput1) == 142)

    val testInput2 = readInput("Day01_2_test")
    check(part2(testInput2) == 281)

    val input1 = readInput("Day01_1")
    part1(input1).println()
    val input2 = readInput("Day01_2")
    part2(input2).println()
}
