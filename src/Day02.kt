class Day02 : Day<Long> {
    override val day = "Day02"
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

fun main() {
    Day02().run {
        test(1227775554, 4174379265)
        execute()
    }
}

private fun List<String>.validateRange(): List<String> = also {
    require(size == 2) { "Must be exactly 2 numbers: $this" }
}

private fun toRange(lower: String, upper: String): LongRange =
    (lower.toLongOrNull() ?: error("Not a number: $lower"))..(upper.toLongOrNull() ?: error("Not a number: $upper"))

private fun Long.isMirror(): Boolean {
    val number = toString(10).takeIf(String::isEvenLength) ?: return false
    return number.splitInMiddle().isMirrored()
}

private fun String.isEvenLength(): Boolean = length % 2 == 0

private fun String.splitInMiddle(): Pair<Long, Long> =
    substring(0, length / 2).toLong() to substring(length / 2).toLong()

private fun Pair<Long, Long>.isMirrored() = (first == second)

private fun Long.isRepeatedPattern(): Boolean {
    val number = toString(10)
    return number.maxRepeatableLength().downTo(1)
        .asSequence()
        .filter { number.isMultipleOf(it) }
        .map { number.splitInEqualPartsOf(it) }
        .filter { it.allItemsAreEqual() }
        .any()
}

private fun List<String>.allItemsAreEqual(): Boolean = distinct().size == 1

private fun String.maxRepeatableLength(): Int = length / 2

private fun String.isMultipleOf(n: Int): Boolean = length % n == 0

private fun String.splitInEqualPartsOf(substringLength: Int): List<String> {
    require(length % substringLength == 0) { "String length $length must be exactly divisible by $substringLength" }
    return buildList {
        repeat(length / substringLength) { idx ->
            add(substring(idx * substringLength, (idx + 1) * substringLength))
        }
    }
}
