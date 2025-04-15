package com.thechance.logic.useCases

import com.thechance.logic.MealsRepository
import com.thechance.model.Meal

typealias IsUserLikeSweetInput = () -> Boolean?
typealias ShortDetailMessage = (String, String) -> Unit
typealias FullDetailMessage = (Meal) -> Unit
typealias ChoiceMessage = () -> Unit

class GetRandomSweetWithNoEggsUseCase(
    private val mealsRepository: MealsRepository
) {
    private val seenSweets = mutableSetOf<Meal>()

    fun swipeOrKeepTheSweet(
        showNameAndDescription: ShortDetailMessage,
        showChoiceMessage: ChoiceMessage,
        showFullDetail: FullDetailMessage,
        isUseLikeSweet: IsUserLikeSweetInput
    ) {
        while (true) {
            val meal = suggestASweetMeal()
            seenSweets.add(meal)
            showNameAndDescription(meal.name, meal.description)

            showChoiceMessage()
            val isUserLikeIt = isUseLikeSweet() ?: return
            if (isUserLikeIt) {
                showFullDetail(meal)
                return
            } else {
                suggestASweetMeal()
            }
        }
    }

    private fun suggestASweetMeal(): Meal {
        return getSweetMealsOnly()
            .random()
            .takeIf(::isNotInSeenSweets) ?: suggestASweetMeal()
    }

    private fun isNotInSeenSweets(meal: Meal): Boolean {
        return meal !in seenSweets
    }
    private fun getSweetMealsOnly(): List<Meal> {
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