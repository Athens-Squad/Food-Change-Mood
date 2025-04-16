package com.thechance.logic.useCases

import com.thechance.logic.MealsRepository
import com.thechance.model.Meal

class MealPrepTimeGuessGameUseCase(
    private val mealsRepositoryImpl: MealsRepository
) {

    private val currentRandomMeal = generateRandomMeal()

    private var countLoses = INITIAL_LOSES_COUNT

    fun playMealGame(guessPrepMinutesInput: Int): MealGameResult {
        if (countLoses == MAXIMUM_NUMBER_OF_LOSES) {
            return MealGameResult.LOST
        }

        val guessResult = checkUserGuess(guessPrepMinutesInput, currentRandomMeal.minutes)
        return if (guessResult == MealGameResult.CORRECT) {
            MealGameResult.WON
        } else {
            countLoses++
            guessResult
        }
    }

    fun getCurrentRandomMeal(): Meal = currentRandomMeal

    private fun checkUserGuess(guessMinutes: Int, mealPrepMinutes: Int): MealGameResult {
        return when {
            guessMinutes > mealPrepMinutes -> MealGameResult.TOO_HIGH
            guessMinutes < mealPrepMinutes -> MealGameResult.TOO_LOW
            else -> MealGameResult.CORRECT
        }
    }

    private fun generateRandomMeal(): Meal {
        return mealsRepositoryImpl.getAllMeals()
            .filterNotNull()
            .random()
    }

    companion object {
        const val MAXIMUM_NUMBER_OF_LOSES = 2
        const val INITIAL_LOSES_COUNT = 0
    }
}

// Results that game logic can produce
enum class MealGameResult {
    TOO_LOW,
    TOO_HIGH,
    CORRECT,
    WON,
    LOST
}
