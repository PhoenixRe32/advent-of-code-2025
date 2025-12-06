import DigitPlace.TENS
import DigitPlace.UNITS

class Day03 : Day<Long> {
    override val day = "Day03"
    override fun part1(input: List<String>): Long {
        return input.sumOf { it.findBiggestJoltage() }
    }

    override fun part2(input: List<String>): Long {
        return 0
    }
}

private fun String.findBiggestJoltage(): Long {
    return findSubstringsForBiggest(TENS)
        .flatMap { (tensPrefix, substring) ->
            substring.findSubstringsForBiggest(UNITS).map { it.copy(prefix = tensPrefix + it.prefix) }
        }
        .maxOf { it.prefix.toLong() }
}


fun main() {
    Day03().run {
        test(357, 0)
        execute()
    }
}