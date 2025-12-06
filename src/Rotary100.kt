// Assuming only positive numbers (and 0)
internal object Rotary100 {
    private const val BASE = 100
    private val range = 0..<BASE

    fun rotate(startingPosition: RotationResult, rotation: Rotation): RotationResult {
        var pos = startingPosition.position.requireValidityForBase()
        var zeros = 0
        repeat(rotation.clicks) {
            pos += rotation.direction.move
            pos = if (pos == 100) 0 else pos
            pos = if (pos == -1) 99 else pos
            zeros += if (pos == 0) 1 else 0
        }

        return RotationResult(position = pos, zeroClicks = zeros)
    }

    private fun Int.requireValidityForBase(): Int {
        require(this in range) { "Must be between 0 and $BASE left inclusive: $this" }
        return this
    }
}

internal data class RotationResult(val position: Int, val zeroClicks: Int)

internal data class Rotation(val direction: Direction, val clicks: Int) {
    init {
        require(clicks >= 0) { "Distance must be positive: $clicks" }
    }

    enum class Direction(val move: Int) { L(-1), R(1) }
}

internal fun String.toOperation(): Rotation {
    require(length > 1) { "Must be at least 2 characters long: $this" }
    val direction = when (first().uppercaseChar()) {
        'L' -> Rotation.Direction.L
        'R' -> Rotation.Direction.R
        else -> error("Unknown direction: $this")
    }
    val distance = substring(1).toIntOrNull() ?: error("Invalid distance: $this")
    return Rotation(direction = direction, clicks = distance)
}

