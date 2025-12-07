import DigitPlace.DECA
import DigitPlace.UNITS

fun main() {
    Day03().run {
        test(357, 3121910778619)
        execute()
    }
}

class Day03 : Day<Long> {

    override fun part1(input: List<String>): Long {
        return input.sumOf { it.findBiggestJoltage2() }
    }
    override fun part2(input: List<String>): Long {
        val joltages = input.map { it.findBiggestJoltage12() }
        return joltages.sum()
    }
}

private fun String.findBiggestJoltage2(): Long {
    return findSubstringsForBiggest(DECA)
        .flatMap { (tensPrefix, substring) ->
            substring.findSubstringsForBiggest(UNITS).map { it.copy(prefix = tensPrefix + it.prefix) }
        }
        .maxOf { it.prefix.toLong() }
}


private fun String.findBiggestJoltage12(): Long {
    var substrsWithPrefix = listOf(SubstringWithPrefix("", this))
    DigitPlace.entries.forEach { digitPlace ->
        substrsWithPrefix = substrsWithPrefix
            .map { currSubWPref ->
                val (prefix, substring) = currSubWPref

                if (currSubWPref.prefix.length == 12)
                    return@forEach

                substring.findSubstringsForBiggest(digitPlace).map {
                    it.copy(prefix = prefix + it.prefix)
                }.maxBy { it.prefix }
            }
    }
    return substrsWithPrefix.maxOf { it.prefix.toLong() }
}
