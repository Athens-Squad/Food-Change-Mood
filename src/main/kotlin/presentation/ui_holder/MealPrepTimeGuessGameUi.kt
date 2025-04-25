package com.thechance.presentation.ui_holder

import com.thechance.logic.useCases.MealPrepTimeGuessGameUseCase
import com.thechance.presentation.BaseFeatureUi
import com.thechance.presentation.io.ConsoleIO
import model.MealGameResult

class MealPrepTimeGuessGameUi(
    private val mealPrepTimeGuessGameUseCase: MealPrepTimeGuessGameUseCase,
    private val consoleIO: ConsoleIO,
    override val featureNumber: Int = 5,
    override val featureName: String = "Guess Meal Preparation Time"
) : BaseFeatureUi {
    override fun startUi() {
        var result: MealGameResult
        do {
            val currentMeal = mealPrepTimeGuessGameUseCase.getCurrentRandomMeal()
            consoleIO.printer.showMessage("Guess the preparation time for: ${currentMeal.name}")
            val userGuess = consoleIO.reader.readNumberFromUser()

            result = mealPrepTimeGuessGameUseCase.playMealGame(userGuess)
            showResultMessage(result, currentMeal.minutes)

        } while (!isGameOver(result))
    }

    private fun showResultMessage(result: MealGameResult, correctMinutes: Int) {
        val message = when (result) {
            MealGameResult.CORRECT -> "Correct!"
            MealGameResult.TOO_LOW -> "Too low!"
            MealGameResult.TOO_HIGH -> "Too high!"
            MealGameResult.LOST -> "You've run out of attempts. The correct time was $correctMinutes minutes."
            MealGameResult.WON -> "Congratulations! You've won the game."
        }
        consoleIO.printer.showMessage(message)
    }

    private fun isGameOver(result: MealGameResult): Boolean {
        return result == MealGameResult.CORRECT || result == MealGameResult.LOST || result == MealGameResult.WON
    }

}