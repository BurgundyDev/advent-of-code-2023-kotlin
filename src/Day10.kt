import java.util.ArrayDeque

fun main() {
    fun part1(input: List<String>): Int {
        val pipeMap: List<List<Char>> = input.map { it.toList() }
        val startingPoint: Pair<Int, Int> = pipeMap.withIndex().flatMap { (rowIndex, row) ->
            row.withIndex().mapNotNull { (colIndex, char) -> if (char == 'S') rowIndex to colIndex else null }
        }.first()

        val visited = mutableSetOf<Pair<Int, Int>>()
        val queue: ArrayDeque<Pair<Int, Int>> = ArrayDeque()
        visited.add(startingPoint)
        queue.addLast(startingPoint)
        while (queue.isNotEmpty()) {
            val (row, col) = queue.removeFirst()
            val currSpace = pipeMap[row][col]
            if (row > 0 && "S|JL".contains(currSpace) && "|7F".contains(pipeMap[row - 1][col]) && !visited.contains(row - 1 to col)) {
                visited.add(row - 1 to col)
                queue.addLast(row - 1 to col)
            }
            if (row < pipeMap.size - 1 && "S|7F".contains(currSpace) && "|JL".contains(pipeMap[row + 1][col]) && !visited.contains(row + 1 to col)) {
                visited.add(row + 1 to col)
                queue.addLast(row + 1 to col)
            }
            if (col > 0 && "S-J7".contains(currSpace) && "-LF".contains(pipeMap[row][col - 1]) && !visited.contains(row to col - 1)) {
                visited.add(row to col - 1)
                queue.addLast(row to col - 1)
            }
            if (col < pipeMap[0].size - 1 && "S-LF".contains(currSpace) && "-J7".contains(pipeMap[row][col + 1]) && !visited.contains(row to col + 1)) {
                visited.add(row to col + 1)
                queue.addLast(row to col + 1)
            }
        }
        return (visited.size/2)
    }

    fun part2(input: List<String>): Int {
        val pipeMap: List<MutableList<Char>> = input.map { it.toMutableList() }
        val startingPoint: Pair<Int, Int> = pipeMap.withIndex().flatMap { (rowIndex, row) ->
            row.withIndex().mapNotNull { (colIndex, char) -> if (char == 'S') rowIndex to colIndex else null }
        }.first()
        var potentialStart = "|-JL7F".toSet()

        val loop = mutableSetOf<Pair<Int, Int>>()
        val queue: ArrayDeque<Pair<Int, Int>> = ArrayDeque()
        loop.add(startingPoint)
        queue.addLast(startingPoint)
        while (queue.isNotEmpty()) {
            val (row, col) = queue.removeFirst()
            val currSpace = pipeMap[row][col]
            if (row > 0 && "S|JL".contains(currSpace) && "|7F".contains(pipeMap[row - 1][col]) && !loop.contains(row - 1 to col)) {
                loop.add(row - 1 to col)
                queue.addLast(row - 1 to col)
                if (currSpace == 'S') {
                    potentialStart = potentialStart.intersect("|JL".toSet())
                }
            }
            if (row < pipeMap.size - 1 && "S|7F".contains(currSpace) && "|JL".contains(pipeMap[row + 1][col]) && !loop.contains(row + 1 to col)) {
                loop.add(row + 1 to col)
                queue.addLast(row + 1 to col)
                if (currSpace == 'S') {
                    potentialStart = potentialStart.intersect("|7F".toSet())
                }
            }
            if (col > 0 && "S-J7".contains(currSpace) && "-LF".contains(pipeMap[row][col - 1]) && !loop.contains(row to col - 1)) {
                loop.add(row to col - 1)
                queue.addLast(row to col - 1)
                if (currSpace == 'S') {
                    potentialStart = potentialStart.intersect("-J7".toSet())
                }
            }
            if (col < pipeMap[0].size - 1 && "S-LF".contains(currSpace) && "-J7".contains(pipeMap[row][col + 1]) && !loop.contains(row to col + 1)) {
                loop.add(row to col + 1)
                queue.addLast(row to col + 1)
                if (currSpace == 'S') {
                    potentialStart = potentialStart.intersect("-LF".toSet())
                }
            }
        }

        val startChar = potentialStart.first()

        for (row in pipeMap.indices) {
            for (col in pipeMap[0].indices) {
                if (pipeMap[row][col] == 'S') {
                    pipeMap[row][col] = startChar
                }
                if(!loop.contains(row to col)) {
                    pipeMap[row][col] = '.'
                }
            }
        }

        var total = 0

        for (ri in pipeMap.indices) {
            for (ci in pipeMap[0].indices) {
                if (!loop.contains(ri to ci)) {
                    var count = 0
                    for (i in 0..ci) {
                        if("JL|".contains(pipeMap[ri][i]) && loop.contains(ri to i)) {
                            count++
                        }
                    }
                    if (count % 2 == 1) {
                        total++
                    }
                }
            }
        }

        return total
    }

    // Test input solution
    val testInput0 = readInput("Day10_test")
    print("Test 0 for Part 1: ")
    part1(testInput0).println()
    print("Test 0 for Part 2: ")
    part2(testInput0).println()

    // Real input solution
    val input = readInput("Day10")
    print("Part 1: ")
    part1(input).println()
    print("Part 2: ")
    part2(input).println()
}
