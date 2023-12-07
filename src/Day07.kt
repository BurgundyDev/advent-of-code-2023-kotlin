import kotlin.math.max

fun main() {
    var part2 = false
    fun getCardValue(card: Char): Int {
        return when (card) {
            'A' -> 14
            'Q' -> 12
            'K' -> 13
            'J' -> { if (part2) 1 else 11}
            'T' -> 10
            else -> card.digitToInt()
        }
    }

    val cardsList = listOf('A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2')
    data class Hand(val cards: String, val value: Int, val bet: Int) : Comparable<Hand> {
        override fun compareTo(other: Hand): Int {
            if (this.value.compareTo(other.value) == 0) {
                for (i in 0..4) {
                    if (getCardValue(this.cards[i]) > getCardValue(other.cards[i])) {
                        return 1
                    } else if (getCardValue(this.cards[i]) < getCardValue(other.cards[i])) {
                        return -1
                    }
                }
            }
            return this.value.compareTo(other.value)
        }
    }

    fun getHandValue(hand: String): Int {
        val valuation = hand.toList().groupingBy { it }.eachCount().values.filter { it > 1 }.toList()
        // println(valuation)
        return when(valuation) {
            listOf(5) -> 7
            listOf(4) -> 6
            listOf(3, 2), listOf(2, 3) -> 5
            listOf(3) -> 4
            listOf(2, 2) -> 3
            listOf(2) -> 2
            else -> 1
        }
    }
    fun getHandValue2(hand: String): Int {
        val jokers = hand.count { it == 'J' }
        if(jokers > 3) {
            return 7
        }
        if (jokers == 1) {
            var max = getHandValue(hand)
            for(a in cardsList){
                max = max(max, getHandValue(hand.replace('J', a)))
            }
            return max
        }
        if (jokers == 2) {
            var max = getHandValue(hand)
            for(a in cardsList){
                for(b in cardsList){
                    max = max(max, getHandValue(hand.replaceFirst('J', a).replaceFirst('J', b)))
                }
            }
            return max
        }
        if (jokers == 3) {
            var max = getHandValue(hand)
            for(a in cardsList){
                for(b in cardsList){
                    for(c in cardsList){
                        max = max(max, getHandValue(hand.replaceFirst('J', a).replaceFirst('J', b).replaceFirst('J', c)))
                    }
                }
            }
            return max
        }
        return getHandValue(hand)
    }
    fun part1(input: List<String>): Int {
        var hands: MutableList<Hand> = mutableListOf<Hand>()
        for(line in input) {
            val cards = line.split(" ")
            val hand = Hand(cards[0], getHandValue(cards[0]), cards[1].toInt())
            hands.add(hand)
        }
        hands = hands.sorted().toMutableList()
        var total = 0
        for((hi, hand) in hands.withIndex()){
            total += hand.bet * (hi + 1)
        }
        return total
    }

    fun part2(input: List<String>): Int {
        part2 = true
        var hands: MutableList<Hand> = mutableListOf<Hand>()
        for(line in input) {
            val cards = line.split(" ")
            val hand = Hand(cards[0], getHandValue2(cards[0]), cards[1].toInt())
            hands.add(hand)
        }
        hands = hands.sorted().toMutableList()
        var total = 0
        for((hi, hand) in hands.withIndex()){
            total += hand.bet * (hi + 1)
        }
        return total
    }

    // Test input solution
    val testInput = readInput("Day07_test")
//    print("Expected Test for Part 1: ")
    part1(testInput).println()
    print("Expected Test for Part 2: ")
    part2(testInput).println()

    // Real input solution
    val input = readInput("Day07")
    print("Part 1: ")
    part1(input).println()
    print("Part 2: ")
    part2(input).println()
}
