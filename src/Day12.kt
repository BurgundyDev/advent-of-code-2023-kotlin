fun main() {
    fun countVariants(arrangement: String, pattern: List<Int>): Long {
        buildMap {
            fun possibleArrangements(arrangement: String, pattern: List<Int>): Long =
                getOrPut(arrangement to pattern) {
                    if (pattern.isEmpty()) return@getOrPut if (arrangement.none { it == '#' }) 1 else 0
                    val firstGroupSize = pattern.first()
                    val match = Regex("([#?]+)").find(arrangement) ?: return@getOrPut 0
                    val firstMatch = match.groupValues[0]
                    if (firstMatch.length == firstGroupSize && firstMatch.all { it == '#' }) {
                        val newArrangement = arrangement.replaceFirst(firstMatch, "").drop(1).dropWhile { it == '.' }
                        return@getOrPut possibleArrangements(newArrangement, pattern.drop(1))
                    } else if (firstMatch.length < firstGroupSize && firstMatch.contains("#")) {
                        return@getOrPut 0
                    } else if (firstMatch.length > firstGroupSize && firstMatch.indexOf("#".repeat(firstGroupSize + 1)) == 0) {
                        return@getOrPut 0
                    }
                    return@getOrPut possibleArrangements(
                        arrangement.replaceFirst("?", "."), pattern
                    ) + possibleArrangements(
                        arrangement.replaceFirst("?", "#"), pattern
                    )
                }
            return possibleArrangements(arrangement, pattern)
        }
    }
    fun part1(input: List<String>): Long {
        val records: List<Pair<String, List<Int>>> = input.map {
            val (record, values) = it.split(" ")
            record to values.split(",").map { it.toInt() }
        }
        var total = 0L
        for (record in records) {
            total += countVariants(record.first, record.second)
        }
        return total
    }
    fun part2(input: List<String>): Long {
        val records: List<Pair<String, List<Int>>> = input.map {
            val (record, values) = it.split(" ")
            val v = values.split(",").map { it.toInt() }
            (record + '?').repeat(5).removeSuffix("?") to List(5) { v }.flatten()
        }
        println(records[0])
        var total = 0L
        for (record in records) {
            total += countVariants(record.first, record.second)
        }
        return total
    }

    // Test input solution
    val testInput = readInput("Day12_test")
    print("Test for Part 1: ")
    part1(testInput).println()
    print("Test for Part 2: ")
    part2(testInput).println()

    // Real input solution
     val input = readInput("Day12")
     print("Part 1: ")
     part1(input).println()
     print("Part 2: ")
     part2(input).println()
}