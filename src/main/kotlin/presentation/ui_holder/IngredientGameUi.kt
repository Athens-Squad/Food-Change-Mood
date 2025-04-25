package com.thechance.presentation.ui_holder

import com.thechance.logic.useCases.IngredientGameUseCase
import com.thechance.presentation.BaseFeatureUi
import com.thechance.presentation.io.ConsoleIO

class IngredientGameUi(
    private val ingredientGameUseCase: IngredientGameUseCase,
    private val consoleIO: ConsoleIO,
    override val featureNumber: Int = 11,
    override val featureName: String = "Play Ingredient Guessing Game"
) : BaseFeatureUi{


    override fun startUi() {
        showWelcome()

        while (!ingredientGameUseCase.isGameOver()) {
            val question = ingredientGameUseCase.guessIngredient() ?: break

            val (mealName, options, correctAnswer) = question
            showQuestion(mealName, options)

            val selectedOption = readUserOption(options)
            if (selectedOption == null) {
                consoleIO.printer.showMessage("Invalid input. Skipping question.")
                continue
            }

            val isCorrect = ingredientGameUseCase.submitAnswer(selectedOption)
            showAnswerFeedback(isCorrect, correctAnswer)

            if (!isCorrect) break

            showScore()
        }

        showGameOver()
    }

    private fun showWelcome() {
        consoleIO.printer.showMessage("Guess the correct ingredient for each meal.\n")
    }

    private fun showQuestion(mealName: String, options: List<String>) {
        consoleIO.printer.showMessage("The Ingredient of  $mealName")
        options.forEachIndexed { index, option ->
            consoleIO.printer.showMessage("${index + 1}. $option")
        }
    }

    private fun readUserOption(options: List<String>): String? {
        val input = consoleIO.reader.readNumberFromUser()
        return if (input in 1..options.size) options[input - 1] else null
    }

    private fun showAnswerFeedback(isCorrect: Boolean, correctAnswer: String) {
        if (isCorrect) {
            consoleIO.printer.showMessage("Correct!\n")
        } else {
            consoleIO.printer.showMessage("Wrong choice ! The correct answer was: $correctAnswer\n")
        }
    }

    private fun showScore() {
        val score = ingredientGameUseCase.getScore()
        consoleIO.printer.showMessage("Current Score: $score")
    }

    private fun showGameOver() {
        val finalScore = ingredientGameUseCase.getScore()
        consoleIO.printer.showMessage("Game Over! Your final score is $finalScore")
    }
}