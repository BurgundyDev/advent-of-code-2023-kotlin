fun main() {
    fun part1(input: List<String>): Int {
        var sumOfIndices = 0
        for((index, line) in input.withIndex()) {
            val game: MutableList<Int> = mutableListOf(index+1, 0, 0, 0)
            val gameText = line.replace(",", "").replace(";", "").split(" ")
            for(i in gameText.indices)
            {
                when(gameText[i]) {
                    "red" -> if(game[1] < gameText[i-1].toInt()) { game[1] = gameText[i-1].toInt() }
                    "blue" -> if(game[2] < gameText[i-1].toInt()) { game[2] = gameText[i-1].toInt() }
                    "green" -> if(game[3] < gameText[i-1].toInt()) { game[3] = gameText[i-1].toInt() }
                }
            }
            if(game[1] <= 12 && game[2] <= 14 && game[3] <= 13) {
                sumOfIndices += game[0]
            }
        }
        return sumOfIndices
    }

    fun part2(input: List<String>): Int {
        var powers = 0
        for((index, line) in input.withIndex()) {
            val game: MutableList<Int> = mutableListOf(index+1, 0, 0, 0)
            val gameText = line.replace(",", "").replace(";", "").split(" ")
            for(i in gameText.indices)
            {
                when(gameText[i]) {
                    "red" -> if(game[1] < gameText[i-1].toInt()) { game[1] = gameText[i-1].toInt() }
                    "blue" -> if(game[2] < gameText[i-1].toInt()) { game[2] = gameText[i-1].toInt() }
                    "green" -> if(game[3] < gameText[i-1].toInt()) { game[3] = gameText[i-1].toInt() }
                }
            }
            powers += (game[1] * game[2] * game[3])
        }
        return powers
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
