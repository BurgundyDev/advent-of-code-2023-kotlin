fun main() {
    fun getAdjacentCoords(coord: Pair<Int, Int>, schematic: List<List<Char>>): List<Pair<Int, Int>> {
        val list: MutableList<Pair<Int, Int>> = mutableListOf()
        if (coord.first > 0) {
            list.addLast(Pair(coord.first - 1, coord.second))
        }
        if (coord.first < schematic.size - 1) {
            list.addLast(Pair(coord.first + 1, coord.second))
        }
        if (coord.second > 0) {
            list.addLast(Pair(coord.first, coord.second - 1))
        }
        if (coord.second < schematic[0].size - 1) {
            list.addLast(Pair(coord.first, coord.second + 1))
        }
        if (coord.first > 0 && coord.second > 0) {
            list.addLast(Pair(coord.first - 1, coord.second - 1))
        }
        if (coord.first > 0 && coord.second < schematic[0].size - 1) {
            list.addLast(Pair(coord.first - 1, coord.second + 1))
        }
        if (coord.first < schematic.size - 1 && coord.second > 0) {
            list.addLast(Pair(coord.first + 1, coord.second - 1))
        }
        if (coord.first < schematic.size - 1 && coord.second < schematic[0].size - 1) {
            list.addLast(Pair(coord.first + 1, coord.second + 1))
        }
        return list
    }

    fun checkIfValid(schematic: List<List<Char>>, coords: List<Pair<Int, Int>>): Boolean {
        val coordsToCheck: MutableList<Pair<Int, Int>> = mutableListOf()
        for (coord in coords) {
            coordsToCheck.addAll(getAdjacentCoords(coord, schematic))
        }
        for(coord in coordsToCheck) {
            if (!schematic[coord.first][coord.second].isDigit() && schematic[coord.first][coord.second] != '.') {
                // println("Valid: " + schematic[coord.first][coord.second])
                return true
            }
        }
        return false
    }

    fun checkIfValid2(schematic: List<List<Char>>, coords: List<Pair<Int, Int>>): Boolean {
        val coordsToCheck: MutableList<Pair<Int, Int>> = mutableListOf()
        for (coord in coords) {
            coordsToCheck.addAll(getAdjacentCoords(coord, schematic))
        }
        for(coord in coordsToCheck) {
            if (schematic[coord.first][coord.second] == '*') {
                // println("Valid: " + schematic[coord.first][coord.second])
                return true
            }
        }
        return false
    }

    fun part1(input: List<String>): Int {
        val schematic: List<List<Char>> = input.map { it.toList() }
        var total = 0
        for ((ri, row) in schematic.withIndex()) {
            var currentNumber = 0
            var currentCoords: MutableList<Pair<Int, Int>> = mutableListOf()
            for ((ci, cell) in row.withIndex()) {
                if (cell.isDigit())
                {
                    if (currentNumber == 0)
                    {
                        currentNumber = cell.digitToInt()
                        currentCoords.addLast(Pair(ri, ci))
                    }
                    else
                    {
                        currentNumber = currentNumber * 10 + cell.digitToInt()
                        currentCoords.addLast(Pair(ri, ci))
                    }
                }
                if ((!cell.isDigit() && currentNumber != 0) || (ci == row.size - 1 && currentNumber != 0)) {
                    if (checkIfValid(schematic, currentCoords)) {
                        // println(currentNumber.toString() + " ")
                        total += currentNumber
                    }
                    currentNumber = 0
                    currentCoords = mutableListOf()
                }
            }
        }
        return total
    }

    fun part2(input: List<String>): Int {
        val schematic: List<List<Char>> = input.map { it.toList() }
        val parts: MutableList<Pair<Int, List<Pair<Int, Int>>>> = mutableListOf()
        val gears: MutableList<Pair<Int, Int>> = mutableListOf()
        var total = 0
        for ((ri, row) in schematic.withIndex()) {
            var currentNumber = 0
            var currentCoords: MutableList<Pair<Int, Int>> = mutableListOf()
            for ((ci, cell) in row.withIndex()) {
                if (cell == '*') {
                    gears.addLast(Pair(ri, ci))
                }
                if (cell.isDigit())
                {
                    if (currentNumber == 0)
                    {
                        currentNumber = cell.digitToInt()
                        currentCoords.addLast(Pair(ri, ci))
                    }
                    else
                    {
                        currentNumber = currentNumber * 10 + cell.digitToInt()
                        currentCoords.addLast(Pair(ri, ci))
                    }
                }
                if ((!cell.isDigit() && currentNumber != 0) || (ci == row.size - 1 && currentNumber != 0)) {
                    if (checkIfValid2(schematic, currentCoords)) {
                        // println(currentNumber.toString() + " ")
                        parts.addLast(Pair(currentNumber, currentCoords))
                    }
                    currentNumber = 0
                    currentCoords = mutableListOf()
                }
            }
        }

        for (gear in gears) {
            val coordinatesToCheck: MutableList<Pair<Int, Int>> = mutableListOf()
            val coordinatesBlocked: MutableList<Pair<Int, Int>> = mutableListOf()
            coordinatesToCheck.addAll(getAdjacentCoords(gear, schematic))
            val adjacentParts: MutableList<Int> = mutableListOf()
            for (coord in coordinatesToCheck) {
                if(!coordinatesBlocked.contains(coord)){
                    for (part in parts) {
                        if (part.second.contains(coord)) {
                            adjacentParts.addLast(part.first)
                            coordinatesBlocked.addAll(part.second)
                        }
                    }
                }

            }
            if (adjacentParts.size == 2) {
                // println(adjacentParts)
                total += adjacentParts.reduce { acc, i -> acc * i }
            }
        }
        return total
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    part1(testInput).println()
    part2(testInput).println()

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
