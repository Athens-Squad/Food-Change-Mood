package com.thechance.logic

import com.thechance.model.Meal

class IngredientGameUseCase(private val mealsRepository: MealsRepository) {

    private var score = 0

    private var correctAnswers = 0
    private lateinit var correctIngredient: String


    fun guessIngredient(): Triple<String, List<String>, String>? {


        val meals = mealsRepository.getAllMeals().filterNotNull().filter {
            it.ingredients.isNotEmpty()
        }.shuffled().toMutableList()

        if (isGameOver()) return null
        val meal = meals.removeFirst()
        correctIngredient = meal.ingredients.random()
        val otherMeals = meals.shuffled().take(2)
        val wrongIngredient = otherMeals.mapNotNull { it.ingredients.randomOrNull() }

        val options = (listOf(correctIngredient) + wrongIngredient).shuffled()
        return Triple(meal.name, options, correctIngredient)


    }

    fun submitAnswer(selectedIngredient: String): Boolean {
        val isCorrect = selectedIngredient == correctIngredient
        if (isCorrect) {
            score += SCORE_EVERY_WIN
            correctAnswers++
        }
        return isCorrect
    }

    fun isGameOver(): Boolean = correctAnswers >= MAX_CORRECT_ANSWER
    fun getScore(): Int = score

    companion object {
        const val MAX_CORRECT_ANSWER = 15
        const val SCORE_EVERY_WIN = 100
    }

}


