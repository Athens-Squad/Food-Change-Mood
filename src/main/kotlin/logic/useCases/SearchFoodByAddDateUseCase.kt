package com.thechance.logic.useCases

import com.thechance.logic.MealsRepository
import com.thechance.logic.NoMealsWithGivenDateException
import com.thechance.model.Meal
import com.thechance.model.NameDateMeal
import java.util.*

class SearchFoodByAddDate(private val repo: MealsRepository) {
    fun getFoodByAddDate(date: Date): List<NameDateMeal> {
        val meals = repo.getAllMeals()
            .filterNotNull()
            .filter { it.submitted == date }
            .map { meal ->
                NameDateMeal(
                    id = meal.id,
                    name = meal.name,
                    date = meal.submitted
                )
            }
        if (meals.isEmpty()) throw NoMealsWithGivenDateException(date)
        return meals
    }

    fun getMealById(id: Int?): Meal? {
        return repo.getAllMeals()
            .filterNotNull()
            .find { it.id == id }
    }
}


