package com.thechance.presentation.ui_holder

import com.thechance.logic.useCases.GetRandomSweetWithNoEggsUseCase
import com.thechance.presentation.BaseFeatureUi
import com.thechance.presentation.IO.ConsoleIO

class GetRandomSweetsWithNoEggsUi(
    private val getRandomSweetWithNoEggsUseCase: GetRandomSweetWithNoEggsUseCase,
    private val consoleIO: ConsoleIO,
    override val featureNumber: Int = 6,
    override val featureName: String = "Suggest a Sweet with No Eggs"
) : BaseFeatureUi {
    override fun startUi() {
        val sweet = getRandomSweetWithNoEggsUseCase.suggestASweetMeal()
        if (sweet != null) {
            consoleIO.printer.showMessage("Suggested Sweet (No Eggs): ${sweet.name} - ${sweet.description}")
            consoleIO.printer.showMessage("Would you like to see more details for ${sweet.name}? (yes/no)")

            val userInput = consoleIO.reader.readStringFromUser().trim()
            if (userInput.equals("yes", ignoreCase = true)) {
                consoleIO.printer.showMessage("Details: $sweet")
            } else if (userInput.equals("No", ignoreCase = true)) {
                startUi()
            } else {
                consoleIO.printer.showMessage("Invalid Input!")
            }
        } else {
            consoleIO.printer.showMessage("No more egg-free sweets available.")
        }
    }

}