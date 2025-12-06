data class SubstringWithPrefix(val prefix: String, val substring: String)

// position (from left) is equivalent to the exponential of the base
// if we start counting from 0, like all true devs
enum class DigitPlace(val position: Int) {
    UNITS(0),
    DECA(1),
    HECTO(2),
}

fun String.findSubstringsForBiggest(digitPlace: DigitPlace): Collection<SubstringWithPrefix> {
    val chars = toCharArray()
    require(chars.size > digitPlace.position) { "Not enough digits: $this" }
    require(chars.all { it.isDigit() }) { "Not all digits: $this" }

    val interestingChars = chars.dropLast(digitPlace.position)
    val maxDigit = interestingChars.max()

    return interestingChars
        .findIndexesFor(maxDigit)
        .map { SubstringWithPrefix(maxDigit.toString(), substring(it+1)) }
}

private fun List<Char>.findIndexesFor(maxDigit: Char): List<Int> =
    mapIndexedNotNull { idx, char -> if (char == maxDigit) idx else null }
