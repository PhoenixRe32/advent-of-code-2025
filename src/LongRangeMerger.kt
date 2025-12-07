import java.util.TreeSet

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
