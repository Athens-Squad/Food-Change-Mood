package com.thechance.logic.useCases

import com.thechance.logic.MealsRepository
import com.thechance.model.Meal
import com.thechance.model.ProteinMeal

//14
class GetSeaFoodMealsSortedByProteinContent(private val repository: MealsRepository) {

    fun getSeaFoodMealsSortedByProteinContent(): List<ProteinMeal> {
        return repository.getAllMeals()
            .filterNotNull()
            .filter { it.isSeaFood() }
            .sortedByDescending { it.nutritionFacts.protein }
            .mapIndexed { index, meal ->
                ProteinMeal(
                    rank = index + 1,
                    name = meal.name,
                    proteinAmount = meal.nutritionFacts.protein
                )
            }
    }

    private fun Meal.isSeaFood(): Boolean {
        return this.tags.contains("seafood")
    }
}