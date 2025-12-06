class Day02 : Day<Long> {
    override val day = "Day02"
    override fun part1(input: List<String>): Long {
        return input.joinToString("")
            .split(",")
            .asSequence()
            .map { it.split("-").validateRange() }
            .map { (lower, upper) -> toRange(lower, upper) }
            .flatMap { range ->
                range.asSequence()
                    .filter { it.hasEvenNumberOfDigits() }
                    .filter { it.splitInMiddle().isMirrored() }
            }
            .sum()
    }

    override fun part2(input: List<String>): Long {
        return 0
    }

}

fun main() {
    Day02().run {
        test(1227775554, 0)
        execute()
    }
}

private fun List<String>.validateRange(): List<String> = also {
    require(size == 2) { "Must be exactly 2 numbers: $this" }
}

private fun toRange(lower: String, upper: String): LongRange =
    (lower.toLongOrNull() ?: error("Not a number: $lower"))..(upper.toLongOrNull() ?: error("Not a number: $upper"))

private fun Long.hasEvenNumberOfDigits(): Boolean = toString().length % 2 == 0


private fun Long.splitInMiddle(): Pair<Long, Long> = toString(10).run {
    substring(0, length / 2).toLong() to substring(length / 2).toLong()
}

private fun Pair<Long, Long>.isMirrored() = (first == second)
