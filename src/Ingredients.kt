import java.util.TreeSet

class Ingredients(input: List<String>) {
    private val ranges: TreeSet<LongRange>
    private val ingredients: MutableSet<Long> = mutableSetOf()

    init {
        val sortedRanges: TreeSet<LongRange> = TreeSet(compareBy<LongRange> { it.first }.thenBy { it.last })
        val addStringAsRange = { s: String -> sortedRanges.add(s.toRange()) }
        val addStringsAsIngredient  = { s: String -> ingredients.add(s.toLong()) }

        var forEachFn = addStringAsRange
        input.asSequence().forEach { line ->
            if (line.isBlank()) {
                forEachFn = addStringsAsIngredient
                return@forEach
            }
            forEachFn(line)
        }

        ranges = sortedRanges.mergeNeighbors()
    }

    fun countFreshIngredients(): Int = ingredients.count { ingredient ->
        ranges.floor(ingredient..ingredient)?.let { ingredient in it } ?: false
    }
}

private fun TreeSet<LongRange>.mergeNeighbors(): TreeSet<LongRange> {
    val result = TreeSet(compareBy<LongRange> { it.first }.thenBy { it.last })
    val iterator = this.iterator()

    var prev = iterator.next()
    while (iterator.hasNext()) {
        val cur = iterator.next()
        if (cur.first in prev) {
            prev = prev.first..maxOf(cur.last, prev.last)
        } else {
            result.add(prev)
            prev = cur
        }
    }
    result.add(prev)

    return result
}
