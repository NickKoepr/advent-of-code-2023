package days

import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { row ->
            val card = row.toCard()
            calculatePoints(card.getNumberOfWonPoints())
        }
    }

    fun part2(input: List<String>): Int {
        val cards = input.map { it.toCard() }
        return cards.sumOf { card ->
            val x = calcNumOfScratchCards(cards, card)
            x
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day04_test")
    check(part1(testInput1) == 13)

    val testInput2 = readInput("Day04_test")
    check(part2(testInput2) == 30)

    val input1 = readInput("Day04")
    part1(input1).println()
    val input2 = readInput("Day04")
    part2(input2).println()
}

data class Card(val id: Int, val cardNumbers: List<Int>, val winningNumbers: List<Int>)

fun String.toCard(): Card {
    val numbers = this.split(":")[1].split("|")
    return Card(
        id = getCardId(this),
        cardNumbers = getNumRowAsList(numbers[0].trim()),
        winningNumbers = getNumRowAsList(numbers[1].trim())
    )
}

fun getNumRowAsList(numRow: String): List<Int> {
    return numRow.split(" ").filter { it.isNotBlank() }.map { it.toInt() }
}

fun getCardId(row: String): Int {
    return row.split(":")[0].filter { it.isDigit() }.toInt()
}

fun Card.getNumberOfWonPoints(): Int {
    return cardNumbers.count { cardNum -> winningNumbers.contains(cardNum) }
}

fun calculatePoints(wonNumbers: Int): Int {
    if (wonNumbers <= 1) return wonNumbers
    return (2..wonNumbers).reduce { acc, _ -> acc + acc }
}


fun calcNumOfScratchCards(allCards: List<Card>, selectedCard: Card): Int {
    val cardId = selectedCard.id
    val numWonPoints = selectedCard.getNumberOfWonPoints()
    var amountOfCards = 0
    if (numWonPoints >= 1) {
        amountOfCards = (cardId + 1..cardId + numWonPoints).sumOf { cardNum ->
            calcNumOfScratchCards(allCards, allCards[cardNum - 1])
        }
    }
    return amountOfCards + 1
}