package com.thechance.logic.useCases

import com.thechance.logic.MealsRepository
import com.thechance.model.Meal

class SearchByCountryName(private val repository: MealsRepository) {

    fun getMealsByCountry(country: String): List<Meal> {
        val lowerCaseCountry = country.lowercase()
        return repository.getAllMeals()
            .filterNotNull()
            .filter { meal ->
                meal.tags.any { tag ->
                    tag.lowercase() == lowerCaseCountry
                }
                meal.description.contains(lowerCaseCountry)
            }
            .shuffled()
            .take(20)
    }
}