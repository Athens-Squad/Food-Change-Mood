package com.thechance.logic.useCases.mealSearchUseCase

import com.thechance.model.Meal

interface MealSearchStrategy {
    fun searchMeals(meals: List<Meal>, searchTerm: String): List<Meal>
}