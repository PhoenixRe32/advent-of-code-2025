private const val START = 'S'
private const val SPLITTER = '^'
private const val SPACE = '.'
private const val BEAM = '|'

private val BEAMERS = setOf(START, BEAM)

class TachyonManifolds(input: List<String>) {

    val diagram: Array<Array<Char>> = Array(input.size) { Array(input[0].length) { SPACE } }
    private var currentBeams = listOf<Pair<Int, Int>>()
    var splits = 0L
    var splittersEncountered = 0L
    var timelines = 0L

    init {
        input.forEachIndexed { row, line ->
            line.toCharArray().forEachIndexed { col, char ->
                this.diagram[row][col] = char
                if (char == START) currentBeams = listOf(row to col)
            }
        }
    }

    fun propagateBeams() {
        while (currentBeams.isNotEmpty()) {
            val currentTimelines = currentBeams.size
            val currentCols = currentBeams.map { it.second }
            currentBeams = propagateBeams(currentBeams)
            if (currentTimelines != currentBeams.size) {
                timelines += (currentBeams.map { it.second }.toSet() - currentCols.toSet()).size
            }

        }
    }

    fun propagateBeams(currentBeams: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
        return currentBeams.flatMap { propagateBeam(it) }
    }

    fun propagateBeam(coords: Pair<Int, Int>): List<Pair<Int, Int>> {
        val (row, col) = coords
        val newBeams = mutableListOf<Pair<Int, Int>>()
        if (row + 1 > diagram.lastIndex) return newBeams

        if (diagram[row + 1][col] == SPACE) {
            diagram[row + 1][col] = BEAM
            newBeams.add(row + 1 to col)
        }

        if (diagram[row + 1][col] == SPLITTER) {
            val s = row + 1 + 1 to col + 1
            splittersEncountered++
            if (col + 1 <= diagram[0].lastIndex) {
                diagram[row + 1][col + 1] = BEAM
                newBeams.add(row + 1 to col + 1)

            }
            if (col - 1 >= 0) {
                diagram[row + 1][col - 1] = BEAM
                newBeams.add(row + 1 to col - 1)
            }
        }
        if (newBeams.size > 1) splits++
        return newBeams
    }

    fun propagateBeam(line: String) {
        line.forEachIndexed { col, char ->
            if (char in BEAMERS)
                diagram[line.indexOf(char)][col] = BEAM
        }
    }
}