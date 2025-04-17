package com.thechance.logic.useCases

import com.thechance.logic.MealsRepository
import com.thechance.model.Meal

class GetIraqiMealsUseCase(
    private val mealsRepository: MealsRepository
) {
    fun getIraqiMeals(): List<Meal?> {
        return mealsRepository.getAllMeals()
            .filterNotNull()
            .filter(::isIraqi)
    }

    private fun isIraqi(meal: Meal): Boolean {
        return (meal.tags.contains("iraqi")
                || meal.description.contains("iraq", ignoreCase = true))
    }
}

