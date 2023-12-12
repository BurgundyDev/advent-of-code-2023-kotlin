fun main() {
    fun part1(input: List<String>): Int {
        val records: List<Pair<String, List<Int>>> = input.map {
            val (record, values) = it.split(" ")
            record to values.split(",").map { it.toInt() }
        }
        var total = 0
        for(rec in records) {
            var record = rec.first
            var values: List<Int> = rec.second
        }
        return total
    }
    fun part2(input: List<String>): Int {
        return 0
    }

    // Test input solution
    val testInput = readInput("Day12_test")
    print("Test for Part 1: ")
    part1(testInput).println()
    // print("Test for Part 2: ")
    // part2(testInput).println()

    // Real input solution
    // val input = readInput("Day12")
    // print("Part 1: ")
    // part1(input).println()
    // print("Part 2: ")
    // part2(input).println()
}