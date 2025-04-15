package com.thechance.logic.useCases

import com.thechance.logic.MealsRepository
import com.thechance.model.Meal

typealias UserGuessInput = () -> Int?
typealias WelcomeMessage = (String) -> Unit
typealias GuessResultMessage = (MealGameResult) -> Unit
typealias GameOverMessage = (String, Int) -> Unit
typealias GeneralMessage = () -> Unit

class MealPrepTimeGuessGameUseCase(
    private val mealsRepositoryImpl: MealsRepository
) {

    fun playMealGame(
        getGuessInput: UserGuessInput,
        showWelcomeMessage: WelcomeMessage,
        showGuessResult: GuessResultMessage,
        showGameOver: GameOverMessage,
        showRetryMessage: GeneralMessage,
        showWinningMessage: GeneralMessage
    ): MealGameResult {

        val randomMeal = generateRandomMeal()
        showWelcomeMessage(randomMeal.name)

        var countLoses = INITIAL_LOSES_COUNT

        repeat(MAXIMUM_NUMBER_OF_ATTEMPTS) {

            val guessPrepMinutesInput = getGuessInput() ?: return MealGameResult.INVALID_INPUT

            if (countLoses == MAXIMUM_NUMBER_OF_LOSES) {
                showGameOver(randomMeal.name, randomMeal.minutes)
                return MealGameResult.LOST
            }

            val guessResult = checkUserGuess(guessPrepMinutesInput, randomMeal.minutes)
            showGuessResult(guessResult)

            if (guessResult == MealGameResult.CORRECT) {
                showWinningMessage()
                return MealGameResult.WON
            }
            showRetryMessage()
            countLoses++
        }

        return MealGameResult.LOST
    }

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
        const val MAXIMUM_NUMBER_OF_ATTEMPTS = 3
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
    LOST,
    INVALID_INPUT
}
