fun main() {
    fun part1(input: List<String>): Int {
        var total = 0
        for(card in input) {
            // println(card.replace(Regex("Card \\d+: "), ""))
            val winningNumbers = card.replace(Regex("Card \\d+: "), "").split(" | ")[0].split(" ").mapNotNull { it.toIntOrNull() }
            val cardNumbers = card.replace(Regex("Card \\d+: "), "").split(" | ")[1].split(" ").mapNotNull { it.toIntOrNull() }
//            println("Winning numbers: $winningNumbers")
//            println("Card numbers: $cardNumbers")
            var cardValue = 0
            for(number in cardNumbers) {
                if(winningNumbers.contains(number)) {
                    if(cardValue==0) {
                        cardValue = 1
                    } else {
                        cardValue *= 2
                    }
                }
            }
            total += cardValue
        }
        return total
    }

    fun part2(input: List<String>): Int {
        val cards = mutableListOf<Pair<Int, Int>>()
        for(card in input) {
            // println(card.replace(Regex("Card \\d+: "), ""))
            val winningNumbers = card.replace(Regex("Card \\d+: "), "").split(" | ")[0].split(" ").mapNotNull { it.toIntOrNull() }
            val cardNumbers = card.replace(Regex("Card \\d+: "), "").split(" | ")[1].split(" ").mapNotNull { it.toIntOrNull() }
//            println("Winning numbers: $winningNumbers")
//            println("Card numbers: $cardNumbers")
            var cardValue = 0
            for(number in cardNumbers) {
                if(winningNumbers.contains(number)) {
                    cardValue += 1
                }
            }
            cards.add(Pair(1, cardValue))
        }
        var total = 0
        for((ci, card) in cards.withIndex()) {
//            println("Copies of card ${ci+1}: ${card.first}")
            for(x in 1..card.first) {
                if(card.second>0) {
                    for(i in 1..card.second) {
                        if(ci+i < cards.size) {
                            cards[ci+i] = Pair(cards[ci+i].first+1, cards[ci+i].second)
                        }
                    }
                }
            }
            total += card.first
        }

        return total
    }

    // Test input solution
    val testInput = readInput("Day04_test")
    val expect1 = 13
    println("Expected Test for Part 1: $expect1")
    part1(testInput).println()
    val expect2 = 30
    println("Expected Test for Part 2: $expect2")
    part2(testInput).println()

    // Real input solution
    val input = readInput("Day04")
    println("Part 1: ")
    part1(input).println()
    println("Part 2: ")
    part2(input).println()
}
