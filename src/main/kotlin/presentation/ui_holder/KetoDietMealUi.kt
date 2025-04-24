package com.thechance.presentation.ui_holder

import com.thechance.logic.useCases.KetoDietMealUseCase
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
        if (ketoMeal != null) {
            consoleIO.printer.showMessage("Suggested Keto Meal: ${ketoMeal.name} - ${ketoMeal.description}")
            consoleIO.printer.showMessage("Would you like to see more details for ${ketoMeal.name}? (yes/no)")

            val userInput = consoleIO.reader.readStringFromUser().trim()
            if (userInput.equals("yes", ignoreCase = true)) {
                consoleIO.printer.showMessage("Details: $ketoMeal")
            } else if (userInput.equals("No", ignoreCase = true)) {
                startUi()
            } else {
                consoleIO.printer.showMessage("Invalid Input!")
            }
        } else {
            consoleIO.printer.showMessage("No more Keto Diet Meals Available.")
        }
    }
}