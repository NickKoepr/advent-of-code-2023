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
        return input.mapIndexed { index, row ->
            val gears = getIndexFromGear(row)
            gears.map { gearLocation ->
                val numbersAroundGear = mutableListOf<Int>()
                numbersAroundGear.addAll(getNumbersFromRowAroundGear(row, gearLocation))

                if (index - 1 >= 0) numbersAroundGear.addAll(
                    getNumbersFromRowAroundGear(input[index - 1], gearLocation)
                )
                if (index + 1 <= input.lastIndex) numbersAroundGear.addAll(
                    getNumbersFromRowAroundGear(input[index + 1], gearLocation)
                )

                if (numbersAroundGear.size == 2) {
                    numbersAroundGear[0] * numbersAroundGear[1]
                } else {
                    0
                }
            }.sumOf { it }
        }.sumOf { it }
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day03_test")
    check(part1(testInput1) == 4361)

    val testInput2 = readInput("Day03_test")
    check(part2(testInput2) == 467835)

    val input1 = readInput("Day03")
    part1(input1).println()
    val input2 = readInput("Day03")
    part2(input2).println()
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

/**
 * Check if there is a gear (* character, based on the gear location) in the given num range.
 */
fun checkForGearInNumRange(numRange: IntRange, gearLocation: Int): Boolean {
    return (numRange.first - 1..numRange.last + 1).contains(gearLocation)
}

/**
 * Get all the numbers that have a gear (* character) inside the number range
 * (see [checkForGearInNumRange] for that check).
 */
fun getNumbersFromRowAroundGear(row: String, gearLocation: Int): List<Int> {
    return getNumberRangeFromRow(row).filter { numRange ->
        checkForGearInNumRange(numRange, gearLocation)
    }.map { row.substring(it).toInt() }
}

/**
 * Get all the gear indexes from a given row.
 */
fun getIndexFromGear(row: String): List<Int> {
    return "[*]".toRegex().findAll(row).map { it.range.first }.toList()
}

fun checkForSymbol(c: Char?): Boolean {
    if (c == null) return false
    return !"[0-9]|[.]".toRegex().matches(c.toString())
}