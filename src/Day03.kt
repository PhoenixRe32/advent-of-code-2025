import DigitPlace.DECA
import DigitPlace.UNITS

class Day03 : Day<Long> {
    override val day = "Day03"
    override fun part1(input: List<String>): Long {
        return input.sumOf { it.findBiggestJoltage2() }
    }

    override fun part2(input: List<String>): Long {
        // first 4 for
        return 0
    }
}

private fun String.findBiggestJoltage2(): Long {
    return findSubstringsForBiggest(DECA)
        .flatMap { (tensPrefix, substring) ->
            substring.findSubstringsForBiggest(UNITS).map { it.copy(prefix = tensPrefix + it.prefix) }
        }
        .maxOf { it.prefix.toLong() }
}


fun main() {
    Day03().run {
        test(357, 3121910778619)
        execute()
    }
}