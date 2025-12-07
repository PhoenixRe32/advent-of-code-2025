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
                if (map[row][col]=='.') return@sumOf 0
                val numberOfRolls = map.countEnclosingSquareOf(row, col) { it==ROLL_OF_PAPER }
                // we are counting the square itself, so we add 1 to the rule of the quiz
                if (numberOfRolls < 5) 1 else 0
            }
        }
    }

    override fun part2(input: List<String>): Int {
        return 0
    }
}

fun Array<Array<Char>>.countEnclosingSquareOf(row: Int, col: Int, predicate: (Char) -> Boolean): Int =
    rangeOfNeighbouringRows(row).sumOf { row ->
        this[row].rangeOfNeighbouringColumns(col).count { col -> predicate(this[row][col]) }
    }

private fun Array<Array<Char>>.rangeOfNeighbouringRows(row: Int): IntRange {
    return (row - 1).coerceAtLeast(0)..(row + 1).coerceAtMost(lastIndex)
}

private fun Array<Char>.rangeOfNeighbouringColumns(col: Int): IntRange {
    return (col - 1).coerceAtLeast(0)..(col + 1).coerceAtMost(lastIndex)
}

fun createAndPopulateMap(input: List<String>): Array<Array<Char>> {
    return createMap(input).apply {
        input.forEachIndexed { row, line ->
            line.toCharArray().forEachIndexed { col, char ->
                if (char == ROLL_OF_PAPER) this[row][col] = ROLL_OF_PAPER
            }
        }
    }
}

// My map ordering is rows and columns
// E.g. [3][5] means 3rd row, 5th column
// Outer index is rows, innner is columns
fun createMap(input: List<String>): Array<Array<Char>> =
    Array(input.size) { Array(input[0].length) { '.' } }
