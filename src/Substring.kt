data class SubstringWithPrefix(val prefix: String, val substring: String) {
    val length = prefix.length + substring.length
    val fullString = prefix + substring
}

// position (from left) is equivalent to the exponential of the base
// if we start counting from 0, like all true devs
enum class DigitPlace(val position: Int) {
    GIGAS2(11),
    GIGAS1(10),
    GIGAS(9),
    MEGA2(8),
    MEGA1(7),
    MEGA(6),
    KILO2(5),
    KILO1(4),
    KILO(3),
    HECTO(2),
    DECA(1),
    UNITS(0),
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
