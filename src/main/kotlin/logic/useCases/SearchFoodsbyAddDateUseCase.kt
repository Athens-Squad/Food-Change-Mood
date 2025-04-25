package com.thechance.logic.useCases

import com.thechance.data.MealsDataException
import com.thechance.logic.MealsRepository
import com.thechance.model.Meal
import java.text.SimpleDateFormat

import java.util.Date

class SearchFoodsbyAddDateUseCase(private val mealsRepository: MealsRepository) {

    fun findMeals(
        input: String,
        filter: (Meal, Date) -> Boolean
    ): List<Meal?> {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val targetDate = try {
            formatter.parse(input)
        } catch (e: Exception) {
            throw MealsDataException.InvalidDateFormatException("Invalid date format! Use yyyy-MM-dd.")
        }

        val filtered = mealsRepository.getAllMeals().filterNotNull().filter { filter(it, targetDate) }

        if (filtered.isEmpty()) {
            throw MealsDataException.NoMealsFoundException("No meals found for $input.")
        }

        return filtered
    }
}




