import kotlin.math.max
import kotlin.math.min

fun main() {
    data class Range(val start: UInt, val end: UInt)

    fun UInt.inRange(min: UInt, max: UInt): Boolean {
        return this in min..max
    }

    fun parseInput(input: List<String>): List<List<List<UInt>>> {
        val mappings: MutableList<List<List<UInt>>> = mutableListOf()
        var currentMapping: MutableList<List<UInt>> = mutableListOf()
        for(line in input) {
            if(line.contains("seeds:")){ continue }
            if(line.contains("map")){
                if(currentMapping.isNotEmpty()){ mappings.add(currentMapping) }
                currentMapping = mutableListOf()
                continue
            }
            if(line.isNotBlank()){ currentMapping.add(line.split(" ").mapNotNull { it.toUIntOrNull() }) }
        }
        mappings.add(currentMapping)

        return mappings
    }

    fun part1(input: List<String>): UInt {
        val seeds: List<UInt> = input[0].removePrefix("seeds: ").split(" ").mapNotNull { it.toUIntOrNull() }
        val mappings: List<List<List<UInt>>> = parseInput(input)
        val finalSeeds: MutableList<UInt> = mutableListOf()
        var seedIn = 0
        for(seed in seeds) {
            seedIn++
            var currSeed = seed
            for( mapping in mappings) {
                for(option in mapping) {
                    if(currSeed.inRange(option[1], option[1] + option[2] - 1u)) {
                        currSeed = option[0] + (currSeed - option[1])
                        break
                    }
                }
            }
            finalSeeds.add(currSeed)
        }

        return finalSeeds.min()
    }

    fun part2(input: List<String>): UInt {
        var seedRanges: ArrayDeque<Range> =
            input[0].removePrefix("seeds: ").split(" ").mapNotNull { it.toUIntOrNull() }.chunked(2).map { Range(it[0], it[0]+it[1]-1u) }.toCollection(ArrayDeque())
        val mappings: List<List<List<UInt>>> = parseInput(input)
        for(mapping in mappings) {
            var newRanges = ArrayDeque<Range>()
            while (seedRanges.isNotEmpty()) {
                var currRange = seedRanges.removeFirst()
                var found = false
                for (option in mapping) {
                    val overlapLeft = max(currRange.start, option[1])
                    val overlapRight = min(currRange.end, option[1] + option[2])
                    if (overlapLeft < overlapRight) {
                        newRanges.addLast(Range(overlapLeft - option[1] + option[0], overlapRight - option[1] + option[0]))
                        if(overlapLeft > currRange.start) {
                            seedRanges.addLast(Range(currRange.start, overlapLeft))
                        }
                        if(overlapRight < currRange.end) {
                            seedRanges.addLast(Range(overlapRight, currRange.end))
                        }
                        found = true
                        break
                    }
                }
                if(!found) {
                    newRanges.addLast(currRange)
                }
            }
            seedRanges = newRanges
        }
        return seedRanges.minOf { it.start }
    }

    // Test input solution
    val testInput = readInput("Day05_test")
    println("Expected Test for Part 1: ")
    part1(testInput).println()
    println("Expected Test for Part 2: ")
    part2(testInput).println()

    // Real input solution
    val input = readInput("Day05")
    print("Part 1: ")
    part1(input).println()
    print("Part 2: ")
    part2(input).println()
}
