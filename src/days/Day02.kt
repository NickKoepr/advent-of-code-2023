package days

import println
import readInput

data class Game(val gameId: Int, val rounds: List<Round>)
data class Round(val items: List<Item>)
data class Item(val value: Int, val color: String)

/**
 * Generate a [Game] data class from the given string.
 */
fun gameFromString(row: String): Game {
    val game = row.split(":")
    val gameId = game[0].split(" ")[1].toInt()

    val rounds = game[1].split(";")
    val roundObj = mutableListOf<Round>()

    rounds.forEach { round ->
        val items = round.split(",")
        val itemsObj = mutableListOf<Item>()

        roundObj.add(Round(itemsObj))
        items.forEach { item ->
            val numbersAndColors = item.trim().split(" ")
            val value = numbersAndColors[0].toInt()
            val color = numbersAndColors[1]

            itemsObj.add(Item(value = value, color = color))
        }
    }

    return Game(gameId, roundObj)
}

fun getGamesFromInput(input: List<String>): List<Game> {
    val games = mutableListOf<Game>()
    input.forEach { row ->
        games.add(gameFromString(row))
    }
    return games
}

fun main() {
    fun part1(input: List<String>): Int {
        val maxCubes = hashMapOf(
            "red" to 12,
            "green" to 13,
            "blue" to 14
        )

        val games = getGamesFromInput(input)
        val filteredGames = games.filter { game ->
            game.rounds.all { round ->
                round.items.all { item ->
                    item.value <= maxCubes.getOrDefault(item.color, -1)
                }
            }
        }

        return filteredGames.sumOf { game ->
            game.gameId
        }
    }

    fun part2(input: List<String>): Int {
        val games = getGamesFromInput(input)

        return games.sumOf { game ->

            val minimumValues = mutableMapOf<String, Int>()

            game.rounds.forEach { round ->
                round.items.forEach { item ->
                    if (!minimumValues.containsKey(item.color) ||
                        minimumValues.getOrDefault(item.color, 0) < item.value
                    ) {
                        minimumValues[item.color] = item.value
                    }
                }
            }
            if (!minimumValues.values.isEmpty()) minimumValues.values.reduce { acc, value -> acc * value } else 0
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day02_test")
    check(part1(testInput1) == 8)

    val testInput2 = readInput("Day02_test")
    check(part2(testInput2) == 2286)

    val input1 = readInput("Day02")
    part1(input1).println()
    val input2 = readInput("Day02")
    part2(input2).println()
}
