import kotlin.time.measureTimedValue

interface Day<N : Number> {
    fun part1(input: List<String>): N
    fun part2(input: List<String>): N
    val day: String

    fun test(part1ExpectedValue: N, part2ExpectedValue: N) {
        val testInput = readInput("${day}_test")
        val part1Test = part1(testInput)
        check(part1Test == part1ExpectedValue) { "Example test (1) failed: $part1Test" }
        val part2Test = part2(testInput)
        check(part2Test == part2ExpectedValue) { "Example test (2) failed: $part2Test" }
    }

    fun execute() {
        val input = readInput(day)
        println("PART 1")
        measureTimedValue{ part1(input) }.println()
        println("=====")
        println("PART 2")
        measureTimedValue{ part2(input) }.println()
    }
}