fun main() {
    fun part1(input: List<String>): Int {
        val instructions: List<Char> = input[0].toList()
        val map: MutableMap<String, Pair<String, String>> = mutableMapOf()
        val regex = """(\w{3}) = \((\w{3}), (\w{3})\)""".toRegex()
        for (line in input.drop(2)) {
            regex.matchEntire(line)?.destructured?.let { (key, value1, value2) ->
                map[key] = Pair(value1, value2)
            }
        }
        var currentNode = "AAA"
        val instructionIterator = CircularIterator(instructions)
        var steps = 0
        while(currentNode != "ZZZ") {
            val currentInstruction = instructionIterator.next()
            when (currentInstruction) {
                'L' -> currentNode = map[currentNode]!!.first
                'R' -> currentNode = map[currentNode]!!.second
            }
            steps++
        }
        return steps
    }

    fun part2(input: List<String>): Long {
        val instructions: List<Char> = input[0].toList()
        val map: MutableMap<String, Pair<String, String>> = mutableMapOf()
        val regex = """(\w{3}) = \((\w{3}), (\w{3})\)""".toRegex()
        for (line in input.drop(2)) {
            regex.matchEntire(line)?.destructured?.let { (key, value1, value2) ->
                map[key] = Pair(value1, value2)
            }
        }
        val nodes = input.drop(2).map { it.split(" ")[0] }.filter { it.endsWith("A") }
        val steps: MutableList<Long> = mutableListOf()
        for (node in nodes) {
            var currNode = node
            var stepsForNode = 0L
            val instructionIterator = CircularIterator(instructions)
            while (!currNode.endsWith("Z")) {
                val currentInstruction = instructionIterator.next()
                when (currentInstruction) {
                    'L' -> currNode = map[currNode]!!.first
                    'R' -> currNode = map[currNode]!!.second
                }
                stepsForNode++
            }
            steps.add(stepsForNode)
        }

        return steps.reduce(::lcm)
    }

    // Test input solution
    val testInput0 = readInput("Day08_test0")
    val testInput1 = readInput("Day08_test1")
    println("Expected Test for Part 1-0: 2")
    part1(testInput0).println()
    println("Expected Test for Part 1-1: 6")
    part1(testInput1).println()
    val testInput2 = readInput("Day08_test2")
    println("Expected Test for Part 2-2: 6")
    part2(testInput2).println()

    // Real input solution
    val input = readInput("Day08")
    println("Part 1: ")
    part1(input).println()
    println("Part 2: ")
    part2(input).println()
}
