fun main() {
    fun part1(input: List<String>): Long {
        val times: List<Long> = input[0].removePrefix("Time: ").split(Regex("\\s+")).mapNotNull { it.toLongOrNull() }
        val distances: List<Long> = input[1].removePrefix("Time: ").split(Regex("\\s+")).mapNotNull { it.toLongOrNull() }
        var result: MutableList<Long> = mutableListOf()
        for ((ti, time) in times. withIndex()) {
            var possibilities: Long = 0
            for(i in 1..time) {
                if (distances[ti] < ((time - i) * i)) {
                    possibilities++
                    // println("Possibility found: ${i}")
                }
            }
            result.add(possibilities)
        }

        return result.reduce { acc, i -> acc * i }
    }

    fun part2(input: List<String>): Long {
        val time: Long = input[0].removePrefix("Time: ").replace(Regex("\\s+"), "").toLong()
        val distance: Long = input[1].removePrefix("Distance: ").replace(Regex("\\s+"), "").toLong()
        var possibilities: Long = 0
        var found: Boolean = false
        for(i in 1..time) {
            if (distance <= ((time - i) * i)) {
                // println(message = "Possibility found: ${i}")
                // println("$distance <= (($time - $i) * $i)")
                possibilities++
                found = true
            } else if (found) {
                break
            }
        }
        return possibilities
    }

    // Test input solution
    val testInput = readInput("Day06_test")
    print("Expected Test for Part 1: ")
    part1(testInput).println()
    print("Expected Test for Part 2: ")
    part2(testInput).println()

    // Real input solution
    val input = readInput("Day06")
    print("Part 1: ")
    part1(input).println()
    print("Part 2: ")
    part2(input).println()
}
