import RepeatablePattern.isMirror
import RepeatablePattern.isRepeatedPattern

fun main() {
    Day02().run {
        test(1227775554, 4174379265)
        execute()
    }
}

class Day02 : Day<Long> {

    override fun part1(input: List<String>): Long {
        return input.toSequenceOfRanges()
            .flatMap { range ->
                range.asSequence().filter { it.isMirror() }
            }
            .sum()
    }

    override fun part2(input: List<String>): Long {
        return input.toSequenceOfRanges()
            .flatMap { range ->
                range.asSequence().filter { it.isRepeatedPattern() }
            }
            .sum()
    }

    private fun List<String>.toSequenceOfRanges() = asSequence()
        .flatMap { it.split(",") }
        .filter { it.isNotBlank() }
        .map { it.split("-").validateRange() }
        .map { (lower, upper) -> toRange(lower, upper) }
}

private fun List<String>.validateRange(): List<String> = also {
    require(size == 2) { "Must be exactly 2 numbers: $this" }
}

private fun toRange(lower: String, upper: String): LongRange =
    (lower.toLongOrNull() ?: error("Not a number: $lower"))..(upper.toLongOrNull() ?: error("Not a number: $upper"))
