fun main() {
    Day06().run {
        test(4277556, 0)
        execute()
    }
}

class Day06 : Day<Long> {

    override fun part1(input: List<String>): Long {
        var updatingInput = input.reversed()
        val results = mutableListOf<Long>()

        while (updatingInput.all { it.isNotBlank() }) {
            val (result, nextInput) = operateOnColumn(updatingInput)
            results.add(result)
            updatingInput = nextInput
        }


        return results.sum()
    }

    override fun part2(input: List<String>): Long {
        return 0
    }

}

// list is reversed already and first line is operations
fun operateOnColumn(input: List<String>): Pair<Long, List<String>> {
    val inputForNext = mutableListOf<String>()

    val (operator, remainingOperationsLine) = input[0].extractFirstWordAndReturnRemainder()
    inputForNext.add(0, remainingOperationsLine)

    val op = OP.from(operator)

    val resultOfColumn = (1 until input.size).fold(op.identity) { acc, i ->
        val (operand, remainingOperandsLines) = input[i].extractFirstWordAndReturnRemainder()
        inputForNext.add(i, remainingOperandsLines)
        op.fn(acc, operand.toLong())
    }

    return resultOfColumn to inputForNext
}

fun String.extractFirstWordAndReturnRemainder() =
    trim().run {
        takeWhile { char -> !char.isWhitespace() }
            .let { word -> word to substring(word.length).trim() }
    }

enum class OP(val identity: Long, val fn: (x: Long, y: Long) -> Long) {
    SUM(0, { a, b -> a + b }),
    MUL(1, { a, b -> a * b });

    companion object {
        fun from(operator: String) = when (operator) {
            "+" -> SUM
            "*" -> MUL
            else -> throw IllegalArgumentException("Unknown operator: $operator")
        }
    }
}