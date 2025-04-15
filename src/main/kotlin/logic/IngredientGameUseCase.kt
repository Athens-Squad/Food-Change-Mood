package com.thechance.logic

import com.thechance.model.Meal

class IngredientGameUseCase(private val mealsRepository: MealsRepository) {

    private var score = 0


    fun guessIngredient() {
        val meals = mealsRepository.getAllMeals()
            .filter { it?.ingredients?.isNotEmpty() == true }
            .filterNotNull()


        while (score < MAX_CORRECT_ANSWER * SCORE_EVERY_WIN) {
            val meal = meals.random()
            val correctIngredient = meal.ingredients.random()
            val options = getOptions(meals, correctIngredient)

            with(meal) {
                println("Meal Name: $name")
                options.forEach{ ingredient ->
                    println(" $ingredient")
                }
            }
            val selected = readUserChoice(options)
            selected?.let {
                if (it.equals(correctIngredient, ignoreCase = true)) {
                    score += SCORE_EVERY_WIN
                    println("Correct choice: $score")
                } else {
                    println("Wrong choice! Correct Ingredient was: $correctIngredient and your score $score")
                    return
                }
            } ?: println("Invalid input.Game Over.")
            println("final score $score")



        }
    }

    private fun getOptions(meals: List<Meal>, correct: String): List<String> {
        val allIngredients = mutableListOf<String>()

        meals.forEach { meal ->
            allIngredients.addAll(meal.ingredients)
        }

        return allIngredients
            .distinct()
            .filterNot { it.equals(correct, ignoreCase = true) }
            .let { filtered ->
                mutableListOf<String>().apply {
                    addAll(filtered.sortedBy { it.length }.take(2))
                    add(correct)
                    sortBy { it.length }
                }
            }
    }

    private fun readUserChoice(options: List<String>): String? {
        print("Your choice (1 of ${options.size}): ")
        return readLine()
            ?.toIntOrNull()
            ?.let { options.getOrNull(it - 1) }
    }


companion object{
    const val MAX_CORRECT_ANSWER=15
    const val SCORE_EVERY_WIN=100
}

}