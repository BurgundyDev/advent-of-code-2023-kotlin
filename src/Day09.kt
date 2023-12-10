fun main() {
    fun part1(input: List<String>): Int {
        var total = 0
        for (line in input) {
            val sequences: MutableList<MutableList<Int>> = mutableListOf()
            val sequence: MutableList<Int> = line.split(" ").map { it.toInt() }.toMutableList()
            sequences.add(sequence)
            //println(sequence)
            var lastSequence = sequence
            while (lastSequence.any{ it != 0 }) {
                val newSequence: MutableList<Int> = mutableListOf()
                for(i in 0 until lastSequence.size-1) {
                    newSequence.add(lastSequence[i+1] - lastSequence[i])
                }
                //println(newSequence)
                sequences.add(newSequence)
                lastSequence = newSequence
            }
            val lastElements: List<Int> = sequences.map { it.last() }
            val finalElements: MutableList<Int> = mutableListOf()
            for (element in lastElements.reversed()) {
                if (element == 0) {
                    finalElements.add(element)
                }
                else {
                    finalElements.add(element + finalElements.last())
                }
            }
            // println(finalElements)
            total += finalElements.last()
        }
        return total
    }

    fun part2(input: List<String>): Int {
        var total = 0
        for (line in input) {
            val sequences: MutableList<MutableList<Int>> = mutableListOf()
            val sequence: MutableList<Int> = line.split(" ").map { it.toInt() }.toMutableList()
            sequences.add(sequence)
            //println(sequence)
            var lastSequence = sequence
            while (lastSequence.any{ it != 0 }) {
                val newSequence: MutableList<Int> = mutableListOf()
                for(i in 0 until lastSequence.size-1) {
                    newSequence.add(lastSequence[i+1] - lastSequence[i])
                }
                //println(newSequence)
                sequences.add(newSequence)
                lastSequence = newSequence
            }
            val firstElements: List<Int> = sequences.map { it.first() }
            val finalElements: MutableList<Int> = mutableListOf()
            // println(firstElements)
            for (element in firstElements.reversed()) {
                if(finalElements.isEmpty()){
                    finalElements.add(element)
                }
                else {
                    finalElements.add(element - finalElements.last())
                }
            }
            total += finalElements.last()
        }
        return total
    }

    // Test input solution
    val testInput = readInput("Day09_test")
    print("Test for Part 1: ")
    part1(testInput).println()
    print("Expected Test for Part 2: ")
    part2(testInput).println()

    // Real input solution
    val input = readInput("Day09")
    print("Part 1: ")
    part1(input).println()
    print("Part 2: ")
    part2(input).println()
}
