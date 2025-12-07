import java.util.TreeSet

fun main() {
    Day05().run {
        test(3, 0)
        execute()
    }
}

class Day05 : Day<Int> {

    override fun part1(input: List<String>): Int {
        val ranges: TreeSet<LongRange> = TreeSet(compareBy<LongRange> { it.first }.thenBy { it.last })
        val addStringAsRange = { s: String -> ranges.add(s.toRange()) }

        val ingredients: MutableSet<Long> = mutableSetOf()
        val addStringsAsIngredient  = { s: String -> ingredients.add(s.toLong()) }

        var forEachFn = addStringAsRange
        input.asSequence().forEach { line ->
            if (line.isBlank()) {
                forEachFn = addStringsAsIngredient
                return@forEach
            }
            forEachFn(line)
        }

//        val numberOfFresh1 = ingredients.count { ingredient ->
//            ranges.floor(ingredient..ingredient)?.let { ingredient in it } ?: false
//        }

        val mergedRanges = ranges.mergeNeighbors()
        val numberOfFresh = ingredients.count { ingredient ->
            mergedRanges.floor(ingredient..ingredient)?.let { ingredient in it } ?: false
        }


        return numberOfFresh
    }

    override fun part2(input: List<String>): Int {
        return 0
    }
}

fun TreeSet<LongRange>.mergeNeighbors(): TreeSet<LongRange> {
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
