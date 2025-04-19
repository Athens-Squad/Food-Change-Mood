package com.thechance.logic.useCases.mealSearchUseCase

import com.thechance.model.Meal

class MealSearchUseCase(private val mealSearchStrategy: MealSearchStrategy) {
    fun search(searchTerm: String): List<Meal> {
        return mealSearchStrategy.searchMeals(searchTerm)
    }
}