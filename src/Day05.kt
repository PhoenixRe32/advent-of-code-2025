fun main() {
    Day05().run {
        test(3, 14)
        execute()
    }
}

class Day05 : Day<Long> {

    override fun part1(input: List<String>): Long {
        val ingredientsAndFreshRanges = IngredientsAndFreshRanges(input)
        return ingredientsAndFreshRanges.countFreshIngredients()
    }

    override fun part2(input: List<String>): Long {
        val freshRanges = FreshRanges(input)
        return freshRanges.countFreshIngredientIds()
    }
}
