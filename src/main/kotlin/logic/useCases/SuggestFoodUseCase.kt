package com.thechance.logic.useCases

import com.thechance.logic.MealsRepository
import com.thechance.model.Meal

class SuggestFoodUseCase(
    private val mealsRepository: MealsRepository
) {
    fun getMeals(): List<Meal> {
        return mealsRepository.getAllMeals()
            .filterNotNull()
            .filter(::isEasy)
            .shuffled()
            .take(10)

    }

    private fun isEasy(meal: Meal): Boolean {
        return (meal.tags.contains("30-minutes-or-less")
                || meal.tags.contains("15-minutes-or-less")
                || meal.minutes <= 30)
                && meal.numberOfIngredients <= 5
                && meal.numberOfSteps <= 6
    }
}