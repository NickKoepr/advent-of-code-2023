package days

import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { row ->
            val index = input.indexOf(row)
            val numbers = getNumberRangeFromRow(row)
            val result = numbers.filter { range ->
                ((checkForSymbolOnRow(row, range)) ||
                        (if (index - 1 >= 0) checkForSymbolOnRow(input[index - 1], range) else false) ||
                        (if (index + 1 <= input.lastIndex) checkForSymbolOnRow(input[index + 1], range) else false))
            }
            result.sumOf { row.substring(it).toInt() }
        }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day03_test")
    check(part1(testInput1) == 4361)

//    val testInput2 = readInput("Day03_test")
//    check(part2(testInput2) == 0)

    val input1 = readInput("Day03")
    part1(input1).println()
//    val input2 = readInput("Day03")
//    part2(input2).println()
}

/**
 * Get the index range of all numbers inside the given row.
 */
fun getNumberRangeFromRow(row: String): List<IntRange> {
    return "[0-9]+".toRegex().findAll(row).map { it.range }.toList()
}

/**
 * Check for symbols on the given row, at the start and end of the given int range.
 */
fun checkForSymbolOnRow(row: String, range: IntRange): Boolean {
    for (index in (range.first - 1..range.last + 1)) {
        if (checkForSymbol(row.getOrNull(index))) return true
    }
    return false
}

fun checkForSymbol(c: Char?): Boolean {
    if (c == null) return false
    return !"[0-9]|[.]".toRegex().matches(c.toString())
}