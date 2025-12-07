fun main() {
    Day04().run {
        test(13, 0)
        execute()
    }
}

private const val ROLL_OF_PAPER = '@'

class Day04 : Day<Int> {

    override fun part1(input: List<String>): Int {
        val map = createAndPopulateMap(input)
        return map.indices.sumOf { row ->
            map[row].indices.sumOf { col ->
                if (!map[row][col]) return@sumOf 0
                val numberOfRolls = map.countEnclosingSquareOf(row, col) { it }
                // we are counting the square itself, so we add 1 to the rule of the quiz
                if (numberOfRolls < 5 ) 1 else 0
            }
        }
    }

    override fun part2(input: List<String>): Int {
        return 0
    }
}

fun Array<Array<Boolean>>.countEnclosingSquareOf(row: Int, col: Int, predicate: (Boolean) -> Boolean): Int {
//    require(row in indices) { "Row $row is out of bounds" }
//    require(col in first().indices) { "Col $col is out of bounds" }
    val map = this

    return rangeOfNeighbouringRows(row).sumOf { row ->
        map[row].rangeOfNeighbouringColumns(col).count { col -> predicate(map[row][col]) }
    }
}

private fun Array<Array<Boolean>>.rangeOfNeighbouringRows(row: Int): IntRange {
    return (row - 1).coerceAtLeast(0)..(row + 1).coerceAtMost(lastIndex)
}

private fun Array<Boolean>.rangeOfNeighbouringColumns(col: Int): IntRange {
    return (col - 1).coerceAtLeast(0)..(col + 1).coerceAtMost(lastIndex)
}

fun createAndPopulateMap(input: List<String>): Array<Array<Boolean>> {
    return createMap(input).apply {
        input.forEachIndexed { row, line ->
            line.toCharArray().forEachIndexed { col, char ->
                this[row][col] = char == ROLL_OF_PAPER
            }
        }
    }
}

// My map ordering is rows and columns
// E.g. [3][5] means 3rd row, 5th column
// Outer index is rows, innner is columns
fun createMap(input: List<String>): Array<Array<Boolean>> {
//    require(input.isNotEmpty())
//    val cols = input[0].length
//    require(input.all { it.length == cols }) { "All rows must have the same length" }
    return Array(input.size) { Array(input[0].length) { false } }
}
