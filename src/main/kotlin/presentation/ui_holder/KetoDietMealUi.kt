package com.thechance.presentation.ui_holder

import com.thechance.logic.useCases.KetoDietMealUseCase
import com.thechance.model.Meal
import com.thechance.presentation.BaseFeatureUi
import com.thechance.presentation.io.ConsoleIO

class KetoDietMealUi(
    private val ketoDietMealUseCase: KetoDietMealUseCase,
    private val consoleIO: ConsoleIO,
    override val featureNumber: Int = 7,
    override val featureName: String = "Keto Diet Meal"
): BaseFeatureUi {
    override fun startUi() {
        val ketoMeal = ketoDietMealUseCase.suggestKetoMeal()

        if (ketoMeal == null) {
            consoleIO.printer.showMessage("No more Keto Diet Meals Available.")
            return
        }

        showSuggestion(ketoMeal)

        val userInput = consoleIO.reader.readStringFromUser().trim().lowercase()

        handleUserInput(userInput, ketoMeal)

    }

    private fun handleUserInput(userInput: String, ketoMeal: Meal) {
        when(userInput) {
            "yes" -> consoleIO.printer.showMessage("Details: $ketoMeal")
            "no" -> startUi()
            else -> consoleIO.printer.showMessage("Invalid Input!")
        }
    }

    private fun showSuggestion(ketoMeal: Meal) {
        consoleIO.printer.showMessage("Suggested Keto Meal: ${ketoMeal.name} - ${ketoMeal.description}")
        consoleIO.printer.showMessage("Would you like to see more details for ${ketoMeal.name}? (yes/no)")
    }
}