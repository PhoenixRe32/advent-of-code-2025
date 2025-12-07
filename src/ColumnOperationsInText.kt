// Assuming a text representing multiple vertical operations
// presented next to each other in a horizontal list
// with the operation being the last row (think of elementary school math)
object ColumnOperationsInText {

    fun operateOnColumn(input: List<String>): Pair<Long, List<String>> {
        val inputForNext = MutableList(input.size) { "" }

        val (operator, remainingOperationsLine) = input.last().extractFirstWordAndReturnRemainder()
        inputForNext[input.size - 1] = remainingOperationsLine

        val op = OP.from(operator)

        val resultOfColumn = (0 until input.size - 1).fold(op.identity) { acc, i ->
            val (operand, remainingOperandsLines) = input[i].extractFirstWordAndReturnRemainder()
            inputForNext[i] = remainingOperandsLines
            op.fn(acc, operand.trim().toLong())
        }

        return resultOfColumn to inputForNext
    }

    private fun String.extractFirstWordAndReturnRemainder(): Pair<String, String> {
        var nextSpaceIsStop = false
        val word = StringBuilder()
        for (c in toCharArray()) {
            if (c.isWhitespace() && nextSpaceIsStop) break
            word.append(c)
            nextSpaceIsStop = !c.isWhitespace()
        }

        return word.toString() to substring(word.length)
    }
}


enum class OP(val identity: Long, val fn: (x: Long, y: Long) -> Long) {
    SUM(0, { a, b -> a + b }),
    MUL(1, { a, b -> a * b });

    companion object {
        fun from(operator: String) = when (operator.trim()) {
            "+" -> SUM
            "*" -> MUL
            else -> throw IllegalArgumentException("Unknown operator: $operator")
        }
    }
}

fun power(baseVal: Int, exponentVal: Int): Long {
    val base = baseVal
    var exponent = exponentVal
    var result: Long = 1

    while (exponent != 0) {
        result *= base.toLong()
        --exponent
    }
    return result
}