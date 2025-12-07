import java.util.TreeSet

class FreshRanges(input: List<String>) {
    private val ranges = TreeSet(compareBy<LongRange> { it.first }.thenBy { it.last }).apply {
        input.asSequence()
            .takeWhile { it.isNotBlank() }
            .forEach { line -> add(line.toRange()) }
    }.mergeNeighbors()

    fun countFreshIngredientIds(): Long = ranges.fold(0L) { acc, range ->
        acc + (range.last - range.first + 1)
    }
}