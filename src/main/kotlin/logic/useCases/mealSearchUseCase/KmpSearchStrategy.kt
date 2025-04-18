package com.thechance.logic.useCases.mealSearchUseCase

import com.thechance.model.Meal

class KmpSearchStrategy: MealSearchStrategy {
    override fun searchMeals(meals: List<Meal>, searchTerm: String): List<Meal> {
        val kmpMatcher = KmpMatcher(searchTerm)
        return meals.filter { meal -> kmpMatcher.search(meal.name) }
    }
}