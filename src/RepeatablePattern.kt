object RepeatablePattern {

    fun Long.isMirror(): Boolean {
        val number = toString(10).takeIf(String::isEvenLength) ?: return false
        return number.splitInEqualPartsOf(number.length / 2).allItemsAreEqual()
    }

    fun Long.isRepeatedPattern(): Boolean {
        val number = toString(10)
        return number.maxRepeatableLength().downTo(1)
            .asSequence()
            .filter { number.isMultipleOf(it) }
            .map { number.splitInEqualPartsOf(it) }
            .any { it.allItemsAreEqual() }
    }
}

private fun String.isEvenLength(): Boolean = length % 2 == 0

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