package com.thechance.logic.useCases

import com.thechance.logic.MealsRepository
import com.thechance.model.Meal
import com.thechance.util.Nationalities.countryToNationality

class SearchByCountryName(private val repository: MealsRepository) {

    fun getMealsByCountry(country: String): List<Meal> {
        val lowerCaseCountry = country.lowercase()
        val nationality = countryToNationality[lowerCaseCountry].toString()
        return repository.getAllMeals()
            .filterNotNull()
            .filter { meal ->
                meal.name.contains(nationality)||
                meal.tags.any {tag ->
                    tag.lowercase() == lowerCaseCountry
                    ||tag.lowercase() == nationality
                }
                    ||meal.description.contains(lowerCaseCountry)
                    ||meal.description.contains(nationality)

            }
            .shuffled()
            .take(20)
    }
}
