package com.thechance.logic.useCases

import com.thechance.logic.MealsRepository
import com.thechance.model.Meal

class GymHelperUseCase(private val repository: MealsRepository) {
    fun getMealsByCaloriesAndProtein(calories: Int, protein: Int): List<Meal> {
        return repository.getAllMeals().filterNotNull().filter { meal ->
            meal.nutritionFacts.calories.toInt() == calories &&
                    meal.nutritionFacts.protein.toInt() == protein
        }
    }
}