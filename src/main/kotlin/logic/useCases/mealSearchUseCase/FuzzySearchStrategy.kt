package com.thechance.logic.useCases.mealSearchUseCase

import com.thechance.model.Meal

class FuzzySearchStrategy: MealSearchStrategy  {
    override fun searchMeals(meals: List<Meal>, searchTerm: String): List<Meal> {
        return meals.filter { meal -> meal.name.contains(searchTerm, ignoreCase = true) }
    }
}