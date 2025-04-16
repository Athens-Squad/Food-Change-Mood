package com.thechance.logic.useCases

import com.thechance.logic.MealsRepository
import com.thechance.model.Meal

//13
class SoThinUseCase(private val repository: MealsRepository) {
    private val unLikedMeals = mutableListOf<Meal>()

    fun getPlus700Meal(): Meal {
        val meal = repository.getAllMeals()
            .filterNotNull()
            .filter(::plus700CaloriesMealsFilter)
            .random()
        unLikedMeals.add(meal)
        return meal
    }

    private fun plus700CaloriesMealsFilter(meal: Meal): Boolean {
        return meal.nutritionFacts.calories >= 700 && !unLikedMeals.contains(meal)
    }
}