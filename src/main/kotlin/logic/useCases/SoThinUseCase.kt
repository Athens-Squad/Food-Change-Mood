package com.thechance.logic.useCases

import com.thechance.logic.MealsRepository
import com.thechance.model.Meal

class SoThinUseCase(private val repository: MealsRepository) {
    private val unLikedMeals = mutableListOf<Meal?>()

    fun getPlus700Meal(): Meal? {
        val meal = repository.getAllMeals()
            .filter(::plus700CaloriesMealsFilter)
            .random()
        unLikedMeals.add(meal)
        return meal
    }

    private fun plus700CaloriesMealsFilter(meal: Meal?): Boolean {
        val calories = meal?.nutritionFacts?.calories
        return calories != null && calories >= 700 && !unLikedMeals.contains(meal)
    }
}