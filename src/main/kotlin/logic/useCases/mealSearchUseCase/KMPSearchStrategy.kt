package com.thechance.logic.useCases.mealSearchUseCase

import com.thechance.model.Meal

class KMPSearchStrategy: MealSearchStrategy {
    override fun searchMeals(meals: List<Meal>, searchTerm: String): List<Meal> {
        val matcher = KMPMatcher(searchTerm)
        return meals.filter { meal -> kmpMatcher.search(meal.name) }
    }
}