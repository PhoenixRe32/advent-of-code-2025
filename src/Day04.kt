fun main() {
    Day04().run {
        test(13, 0)
        execute()
    }
}

class Day04 : Day<Int> {

    override fun part1(input: List<String>): Int {
        val paperRollMap = PaperRollMap(input)
        return paperRollMap.countAccessibleRollsOfPaper()
    }

    override fun part2(input: List<String>): Int {
        return 0
    }
}