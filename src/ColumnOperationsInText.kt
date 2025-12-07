// Assuming a text representing multiple vertical operations
// presented next to each other in a horizontal list
// with the operation being the last row (think of elementary school math)
object ColumnOperationsInText {

    fun operateOnColumn(input: List<String>): Pair<Long, List<String>> {
        val inputForNext = mutableListOf<String>()

        val (operator, remainingOperationsLine) = input.last().extractFirstWordAndReturnRemainder()
        inputForNext.add(0, remainingOperationsLine)

        val op = OP.from(operator)

        val resultOfColumn = (0 until input.size - 1).fold(op.identity) { acc, i ->
            val (operand, remainingOperandsLines) = input[i].extractFirstWordAndReturnRemainder()
            inputForNext.add(i, remainingOperandsLines)
            op.fn(acc, operand.toLong())
        }

        return resultOfColumn to inputForNext
    }

    private fun String.extractFirstWordAndReturnRemainder() =
        trim().run {
            takeWhile { char -> !char.isWhitespace() }
                .let { word -> word to substring(word.length).trim() }
        }


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