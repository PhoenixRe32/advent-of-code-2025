import java.util.TreeSet

fun main() {
    Day05().run {
        test(3, 0)
        execute()
    }
}

class Day05 : Day<Int> {

    override fun part1(input: List<String>): Int {
        val ingredients = Ingredients(input)
        return ingredients.countFreshIngredients()
    }

    override fun part2(input: List<String>): Int {
        return 0
    }
}
