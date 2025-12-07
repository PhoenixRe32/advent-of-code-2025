import ColumnOperationsInText.operateOnColumn
import ColumnOperationsInText.operateOnColumnCelaphodStyle

fun main() {
    Day06().run {
        test(4277556, 3263827)
        execute()
    }
}

class Day06 : Day<Long> {

    override fun part1(input: List<String>): Long {
        var updatingInput = input
        val results = mutableListOf<Long>()

        while (updatingInput.all { it.isNotBlank() }) {
            val (result, nextInput) = operateOnColumn(updatingInput)
            results.add(result)
            updatingInput = nextInput
        }

        return results.sum()
    }

    override fun part2(input: List<String>): Long {
        var updatingInput = input
        val results = mutableListOf<Long>()

        while (updatingInput.all { it.isNotBlank() }) {
            val (result, nextInput) = operateOnColumnCelaphodStyle(updatingInput)
            results.add(result)
            updatingInput = nextInput
        }

        return results.sum()
    }
}
