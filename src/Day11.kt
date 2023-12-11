import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val galaxyMap: MutableList<MutableList<Char>> = input.map { it.toMutableList() }.toMutableList()
        val emptyRows = galaxyMap.indices.toMutableList()
        val emptyColumns = galaxyMap[0].indices.toMutableList()
        val galaxies = mutableListOf<Pair<Int, Int>>()
        for (i in galaxyMap.indices) {
            for (j in galaxyMap[i].indices) {
                if (galaxyMap[i][j] == '#') {
                    emptyColumns.remove(j)
                    emptyRows.remove(i)
                }
            }
        }
        for ((ri, row) in emptyRows.withIndex()) {
            galaxyMap.add(row+ri, MutableList(galaxyMap[0].size) { '.' })
        }

        for ((ci, column) in emptyColumns.withIndex()) {
            for (row in galaxyMap.indices) {
                galaxyMap[row].add(column+ci, '.')
            }
        }

        println()
        var currID = 0
        for (i in galaxyMap.indices) {
            for (j in galaxyMap[i].indices) {
                if (galaxyMap[i][j] == '#') {
                    print(currID)
                    currID++
                    galaxies.add(i to j)
                } else {
                    print('.')
                }
            }
            println()
        }

        val distances: MutableMap<HashSet<Int>, Int> = mutableMapOf()
        for(i in galaxies.indices) {
            for (j in i+1 until galaxies.size) {
                val distance = abs(galaxies[i].first - galaxies[j].first) + abs(galaxies[i].second - galaxies[j].second)
                distances[HashSet(hashSetOf(i, j))] = distance
            }
        }
        for (distance in distances) {
            println(distance)
        }

        return distances.values.sum()
    }

    fun part2(input: List<String>): Long {
        val galaxyMap: MutableList<MutableList<Char>> = input.map { it.toMutableList() }.toMutableList()
        val emptyRows = galaxyMap.indices.toMutableList()
        val emptyColumns = galaxyMap[0].indices.toMutableList()
        val galaxies = mutableListOf<Pair<Long, Long>>()
        for (i in galaxyMap.indices) {
            for (j in galaxyMap[i].indices) {
                if (galaxyMap[i][j] == '#') {
                    emptyColumns.remove(j)
                    emptyRows.remove(i)
                }
            }
        }

        for (i in galaxyMap.indices) {
            for (j in galaxyMap[i].indices) {
                if (galaxyMap[i][j] == '#') {
                    galaxies.add(i.toLong() to j.toLong())
                }
            }
        }

        val distances: MutableMap<HashSet<Long>, Long> = mutableMapOf()
        for(i in galaxies.indices) {
            for (j in i+1 until galaxies.size) {
                val additionalVert = emptyRows.count { it in galaxies[i].first..galaxies[j].first || it in galaxies[j].first..galaxies[i].first } * (1000000 - 1)
                val additionalHor = emptyColumns.count { it in galaxies[i].second..galaxies[j].second || it in galaxies[j].second..galaxies[i].second} * (1000000 - 1)
                val distance = abs(galaxies[i].first - galaxies[j].first) + abs(galaxies[i].second - galaxies[j].second) + additionalVert + additionalHor
                distances[HashSet(hashSetOf(i.toLong(), j.toLong()))] = distance
            }
        }

        return distances.values.sum()
    }

    // Test input solution
    val testInput = readInput("Day11_test")
    print("Test for Part 1: ")
    part1(testInput).println()
    print("Test for Part 2: ")
    part2(testInput).println()

    // Real input solution
    val input = readInput("Day11")
    print("Part 1: ")
    part1(input).println()
    print("Part 2: ")
    part2(input).println()
}
