private const val START_POS = 50

fun main() {
    Day01().run {
        test(3, 6)
        execute()
    }
}

class Day01 : Day<Int> {

    override fun part1(input: List<String>): Int {
        return input
            .map { Rotation.parse(it) }
            .runningFold(RotationResult(position = START_POS, zeroClicks = 0), Rotary100::rotate)
            .count { it.position == 0 }
    }
    override fun part2(input: List<String>): Int {
        return input
            .map { Rotation.parse(it) }
            .runningFold(RotationResult(position = START_POS, zeroClicks = 0), Rotary100::rotate)
            .sumOf { it.zeroClicks }
    }
}
