import java.util.TreeSet

class IngredientsAndFreshRanges(input: List<String>) {
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

    fun countFreshIngredients(): Long = ingredients.count { ingredient ->
        ranges.floor(ingredient..ingredient)?.let { ingredient in it } ?: false
    }.toLong()
}