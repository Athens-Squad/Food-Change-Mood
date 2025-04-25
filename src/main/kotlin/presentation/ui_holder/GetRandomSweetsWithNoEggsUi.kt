package com.thechance.presentation.ui_holder

import com.thechance.logic.useCases.GetRandomSweetWithNoEggsUseCase
import com.thechance.model.Meal
import com.thechance.presentation.BaseFeatureUi
import com.thechance.presentation.io.ConsoleIO

class GetRandomSweetsWithNoEggsUi(
    private val getRandomSweetWithNoEggsUseCase: GetRandomSweetWithNoEggsUseCase,
    private val consoleIO: ConsoleIO,
    override val featureNumber: Int = 6,
    override val featureName: String = "Suggest a Sweet with No Eggs"
) : BaseFeatureUi {
    override fun startUi() {

        val sweet = getRandomSweetWithNoEggsUseCase.suggestASweetMeal()

        if (sweet == null) {
            consoleIO.printer.showMessage("No more egg-free sweets available.")
            return
        }

        showSuggestion(sweet)

        val userInput = consoleIO.reader.readStringFromUser().trim().lowercase()

        handleUserInput(userInput, sweet)
    }

    private fun handleUserInput(userInput: String, sweet: Meal) {
        when (userInput) {
            "yes" -> consoleIO.printer.showMessage("Details: $sweet")
            "no" -> startUi()
            else -> consoleIO.printer.showMessage("Invalid Input!")
        }
    }

    private fun showSuggestion(sweet: Meal) {
        consoleIO.printer.showMessage("Suggested Sweet (No Eggs): ${sweet.name} - ${sweet.description}")
        consoleIO.printer.showMessage("Would you like to see more details for ${sweet.name}? (yes/no)")
    }

}