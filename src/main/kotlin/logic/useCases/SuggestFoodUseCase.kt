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
        return (meal.minutes <= 30)
                && meal.numberOfIngredients <= 5
                && meal.numberOfSteps <= 6
    }


}