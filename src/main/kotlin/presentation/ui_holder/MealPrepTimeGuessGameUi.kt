package com.thechance.presentation.ui_holder

import com.thechance.logic.useCases.MealPrepTimeGuessGameUseCase
import com.thechance.presentation.BaseFeatureUi
import com.thechance.presentation.IO.ConsoleIO
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
            when (result) {
                MealGameResult.CORRECT -> consoleIO.printer.showMessage("Correct!")
                MealGameResult.TOO_LOW -> consoleIO.printer.showMessage("Too low!")
                MealGameResult.TOO_HIGH -> consoleIO.printer.showMessage("Too high!")
                MealGameResult.LOST -> consoleIO.printer.showMessage("You've run out of attempts. The correct time was ${currentMeal.minutes} minutes.")
                MealGameResult.WON -> consoleIO.printer.showMessage("Congratulations! You've won the game.")
            }
        } while (result != MealGameResult.CORRECT && result != MealGameResult.LOST)
    }

}