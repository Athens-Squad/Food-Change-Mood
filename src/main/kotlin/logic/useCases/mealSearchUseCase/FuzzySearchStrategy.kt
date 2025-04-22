package com.thechance.logic.useCases.mealSearchUseCase

import com.thechance.logic.MealsRepository
import com.thechance.model.Meal

class FuzzySearchStrategy(private val repository: MealsRepository): MealSearchStrategy  {
    override fun searchMeals(searchTerm: String): List<Meal> {
        return repository.getAllMeals()
            .filterNotNull()
            .filter { meal -> meal.name.contains(searchTerm, ignoreCase = true) }
    }
}