import kotlin.math.absoluteValue
import kotlin.math.sign

// Assuming only positive numbers (and 0)
object Rotary100 {
    private const val BASE = 100
    private val range = 0..<BASE

    fun rotate(startingPosition: RotationResult, rotation: Rotation): RotationResult {
        val sum = startingPosition.position.requireValidityForBase() + (rotation.clicks * rotation.direction.move)
        val zeros = if (startingPosition.position == 0) (sum / BASE).absoluteValue
        else (sum / BASE).absoluteValue + if (sum.sign != startingPosition.position.sign) 1 else 0

        val position = (sum % BASE).run { if (this < 0) this + BASE else this }
        return RotationResult(position = position, zeroClicks = zeros)
    }

    private fun Int.requireValidityForBase(): Int {
        require(this in range) { "Must be between 0 and $BASE left inclusive: $this" }
        return this
    }
}

data class RotationResult(val position: Int, val zeroClicks: Int)

data class Rotation(val direction: Direction, val clicks: Int) {
    init {
        require(clicks >= 0) { "Distance must be positive: $clicks" }
    }

    companion object {
        fun parse(s: String): Rotation {
            require(s.length > 1) { "Must be at least 2 characters long: $s" }
            val direction = when (s.first().uppercaseChar()) {
                'L' -> Direction.L
                'R' -> Direction.R
                else -> error("Unknown direction: $s")
            }
            val distance = s.substring(1).toIntOrNull() ?: error("Invalid distance: $s")
            return Rotation(direction = direction, clicks = distance)
        }
    }

    enum class Direction(val move: Int) { L(-1), R(1) }
}
