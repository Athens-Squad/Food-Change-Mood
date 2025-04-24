package com.thechance.presentation.ui_holder

import com.thechance.logic.useCases.IngredientGameUseCase
import com.thechance.presentation.BaseFeatureUi
import com.thechance.presentation.IO.ConsoleIO

class IngredientGameUi(
    private val ingredientGameUseCase: IngredientGameUseCase,
    private val consoleIO: ConsoleIO,
    override val featureNumber: Int = 11,
    override val featureName: String = "Play Ingredient Guessing Game"
) : BaseFeatureUi{
    override fun startUi() {
        consoleIO.printer.showMessage("Welcome to the Ingredient Guessing Game!")
        consoleIO.printer.showMessage("Guess the correct ingredient for each meal.")

        while (!ingredientGameUseCase.isGameOver()) {
            val question = ingredientGameUseCase.guessIngredient()
            if (question == null) {
                consoleIO.printer.showMessage("Game Over!")
                break
            }

            val (mealName, options, correct) = question
            consoleIO.printer.showMessage("Which of the following is an ingredient in: $mealName")
            options.forEachIndexed { index, option ->
                consoleIO.printer.showMessage("${index + 1}: $option")
            }

            val userChoice = consoleIO.reader.readNumberFromUser()
            val selected = if (userChoice in 1..options.size) {
                options[userChoice - 1]
            } else {
                consoleIO.printer.showMessage("Invalid input. Skipping question.")
                continue
            }

            if (ingredientGameUseCase.submitAnswer(selected)) {
                consoleIO.printer.showMessage("✅ Correct!")
            } else {
                consoleIO.printer.showMessage("❌ Wrong! The correct answer was: $correct")
                return
            }

            consoleIO.printer.showMessage("Current Score: ${ingredientGameUseCase.getScore()}")
            consoleIO.printer.showMessage("------------------------------------------------")
        }

        consoleIO.printer.showMessage("🎉 Game finished! Your final score is ${ingredientGameUseCase.getScore()}")
    }
}