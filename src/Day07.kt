fun main() {
    Day07().run {
        test(21, 0)
        execute()
    }
}

class Day07 : Day<Long> {

    override fun part1(input: List<String>): Long {
        val tachyonManifolds = TachyonManifolds(input)
        tachyonManifolds.propagateBeams()
        return tachyonManifolds.splits
    }

    override fun part2(input: List<String>): Long {
        return 0
    }
}
