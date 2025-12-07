private const val ROLL_OF_PAPER = '@'
private const val REMOVED_PAPER = 'x'
private const val SPACE = '.'

class PaperRollMap(input: List<String>) {
    private val map: Array<Array<Char>> = Array(input.size) { Array(input[0].length) { '.' } }
    private val toiletPaperCoOrds: MutableList<Pair<Int, Int>> = ArrayList(input.size * input[0].length)

    init {
        input.forEachIndexed { row, line ->
            line.toCharArray().forEachIndexed { col, char ->
                if (char == ROLL_OF_PAPER) {
                    map[row][col] = ROLL_OF_PAPER
                    toiletPaperCoOrds.add(row to col)
                }
            }
        }
    }

    fun countAccessibleRollsOfPaper(): Int {
        return toiletPaperCoOrds.count { coords ->
            countEnclosingSquareOf(coords.first, coords.second) { it == ROLL_OF_PAPER } < 5
        }
    }

    fun removeRollsOfPaperRec(): Int {
        var totalRemovedRolls = 0
        var removedRolls = removeRollsOfPaper().also { totalRemovedRolls += it }
        while (removedRolls > 0) {
            removedRolls = removeRollsOfPaper().also { totalRemovedRolls += it }
        }
        return totalRemovedRolls
    }

    private fun removeRollsOfPaper(): Int {
        var removedRolls = 0
        val iter = toiletPaperCoOrds.iterator()
        while (iter.hasNext()) {
            val coords = iter.next()
            if (countEnclosingSquareOf(coords.first, coords.second) { it == ROLL_OF_PAPER } < 5) {
                map[coords.first][coords.second] = REMOVED_PAPER
                removedRolls++
                iter.remove()
            }
        }
        return removedRolls
    }

    private fun countEnclosingSquareOf(row: Int, col: Int, predicate: (Char) -> Boolean): Int =
        map.rangeOfNeighbouringRows(row).sumOf { row ->
            map[row].rangeOfNeighbouringColumns(col).count { col -> predicate(map[row][col]) }
        }

    private fun Array<Array<Char>>.rangeOfNeighbouringRows(row: Int): IntRange {
        return (row - 1).coerceAtLeast(0)..(row + 1).coerceAtMost(lastIndex)
    }

    private fun Array<Char>.rangeOfNeighbouringColumns(col: Int): IntRange {
        return (col - 1).coerceAtLeast(0)..(col + 1).coerceAtMost(lastIndex)
    }
}