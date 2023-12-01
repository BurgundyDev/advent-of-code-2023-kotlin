fun main() {
    fun part1(input: List<String>): Int {
        var values = 0
        for (line in input) {
            var num = -1
            var last_num = 0
            for (char in line) {
                if(char.isDigit()) {
                    if(num == -1){
                        num = char.digitToInt() * 10
                    }
                    num -= last_num
                    last_num = char.digitToInt()
                    num += last_num
                }
            }
            values += num
        }
        println(values)
        return values
    }

    val numberMap = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9",
        "zero" to "0"
    )

    fun part2(input: List<String>): Int {
        var values = 0
        for (line in input) {
            val nums: MutableList<Int> = mutableListOf()
            var i = 0
            for (char in line) {
                val slice = line.slice(i..<line.length)
                for((key, value) in numberMap) {
                    if(slice.startsWith(key)) {
                        nums.add(value.toInt())
                    }
                }
                if (char.isDigit()) {
                    nums.add(char.digitToInt())
                }
                i++
            }
            values += (nums[0] * 10 + nums[nums.size-1])
        }
        println(values)
        return values
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    // check(part1(testInput) == 142)
    check(part2(testInput) == 281)

    val input = readInput("Day01")
    part1(input)
    part2(input)
}
