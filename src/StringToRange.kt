// Assumes sting in format Number1-Number2 and Number1 < Number2
fun String.toRange(): LongRange = split("-")
    .let { (lower, upper) ->
        lower.toLong()..upper.toLong()
    }
