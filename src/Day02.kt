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
        .map { it.toRange() }
}
