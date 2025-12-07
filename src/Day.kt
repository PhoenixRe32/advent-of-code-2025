import kotlin.time.measureTime

interface Day<N : Number> {
    fun part1(input: List<String>): N
    fun part2(input: List<String>): N
    val day: String get() = this::class.simpleName!!

    fun test(part1ExpectedValue: N, part2ExpectedValue: N) {
        val testInput = readInput("${day}_test")
        runAndTime(testInput, ::part1, part1ExpectedValue, "Test Part 1")
        runAndTime(testInput, ::part2, part2ExpectedValue, "Test Part 2")
    }

    fun execute() {
        val input = readInput(day)
        println("=====")
        runAndTime(input, ::part1, null, "Proper Part 1")
        runAndTime(input, ::part2, null, "Proper Part 2")
    }

    private fun runAndTime(input: List<String>, fn: (input: List<String>) -> N, expectedValue: N?, label: String) {
        // throwaway run
        val part1ActualValue = fn(input).also { println("$label result: $it") }
        if (expectedValue != null) check(part1ActualValue == expectedValue) { "$label failed: $expectedValue" }
        timeItInMicros(input, fn).also { println("$label average run time: ${it.pretty()}") }
    }

    private fun timeItInMicros(input: List<String>, function: (input: List<String>) -> N): Double = (1..100)
        .map { measureTime { function(input) }.inWholeMicroseconds }
        .average()
}

private const val MICROS = "μs"
private const val MILLIS = "ms"
private const val SECS = "s"
private fun Double.pretty(): String = when {
    this >= 1_000_000 -> String.format("%.3f", this / 1_000_000) + SECS
    this >= 1_000 -> String.format("%.3f", this / 1_000) + MILLIS
    else -> String.format("%.3f", this) + MICROS
}
