package com.thechance.logic.useCases.mealSearchUseCase

import com.thechance.model.Meal

interface MealSearchStrategy {
    fun searchMeals(searchTerm: String): List<Meal>
}