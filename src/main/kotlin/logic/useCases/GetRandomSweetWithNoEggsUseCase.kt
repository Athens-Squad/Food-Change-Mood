package com.thechance.logic.useCases

import com.thechance.logic.MealsRepository
import com.thechance.model.Meal

class GetRandomSweetWithNoEggsUseCase(
    private val mealsRepository: MealsRepository
) {
    private val seenSweets = mutableSetOf<Meal>()

    fun suggestASweetMeal(): Meal? {
        return try {
            getSweetMealsWithoutEggsOnly()
                .takeWhile(::isNotInSeenSweets)
                .random()
                .also { seenSweets.add(it) }
        } catch (e: NoSuchElementException) {
           return null  // No sweets without eggs left
        }
    }

    private fun isNotInSeenSweets(meal: Meal): Boolean {
        return meal !in seenSweets
    }

    private fun getSweetMealsWithoutEggsOnly(): List<Meal> {
        return mealsRepository.getAllMeals()
            .filterNotNull()
            .filter(::onlySweetsWithoutEggs)
    }

    private fun onlySweetsWithoutEggs(meal: Meal): Boolean {
        return isOnlySweets(meal)
                && isWithoutEggs(meal)
    }

    private fun isWithoutEggs(meal: Meal): Boolean {
        return !meal.ingredients.contains("eggs")
    }

    private fun isOnlySweets(meal: Meal): Boolean {
        return meal.name.contains("sweets", ignoreCase = true)
                || meal.description.contains("sweets", ignoreCase = true)
    }
}