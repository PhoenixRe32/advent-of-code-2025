import kotlin.time.measureTimedValue

interface Day<N : Number> {
    fun part1(input: List<String>): N
    fun part2(input: List<String>): N
    val day: String get() = this::class.simpleName!!

    fun test(part1ExpectedValue: N, part2ExpectedValue: N) {
        val testInput = readInput("${day}_test")
        measureTimedValue{ part1(testInput) }.run {
            println()
            check(value == part1ExpectedValue) { "Example test (1) failed: $value" }
        }
        measureTimedValue{ part2(testInput) }.run {
            println()
            check(value == part2ExpectedValue) { "Example test (2) failed: $value" }
        }
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