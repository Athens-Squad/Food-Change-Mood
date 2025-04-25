package com.thechance.logic.useCases

import com.thechance.logic.MealsRepository
import com.thechance.model.Meal

class GetRandomTenMealIncludePotatoUseCase(
    private val mealsRepository: MealsRepository
) {
    fun suggestPotatoMeals(numberOfMeal: Int = 10): List<Meal> {
        return mealsRepository.getAllMeals()
            .filterNotNull()
            .filter { it.ingredients.any { ingredient -> ingredient.contains("potato", ignoreCase = true) } }
            .shuffled().take(numberOfMeal)

    }
}



