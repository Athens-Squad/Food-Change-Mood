package com.thechance.logic.useCases.mealSearchUseCase

import com.thechance.logic.MealsRepository
import com.thechance.model.Meal

class KmpSearchStrategy(private val repository: MealsRepository): MealSearchStrategy {
    override fun searchMeals(searchTerm: String): List<Meal> {
        val kmpMatcher = KmpMatcher(searchTerm)
        return repository.getAllMeals()
            .filterNotNull()
            .filter { meal -> kmpMatcher.search(meal.name) }
    }
}