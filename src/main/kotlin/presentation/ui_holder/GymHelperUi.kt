package com.thechance.presentation.ui_holder

import com.thechance.logic.useCases.GymHelperUseCase
import com.thechance.presentation.BaseFeatureUi
import com.thechance.presentation.io.ConsoleIO

class GymHelperUi(
    private val gymHelperUseCase: GymHelperUseCase,
    private val consoleIO: ConsoleIO,
    override val featureNumber: Int = 9,
    override val featureName: String = "Gym Helper"
) : BaseFeatureUi {
    override fun startUi() {
        consoleIO.printer.showMessage("Enter desired calories:")
        val calories = consoleIO.reader.readNumberFromUser()

        consoleIO.printer.showMessage("Enter desired protein:")
        val protein = consoleIO.reader.readNumberFromUser()

        val meals = gymHelperUseCase.getMealsByCaloriesAndProtein(calories, protein)
        consoleIO.printer.showMessage("Meals matching your criteria (Calories: $calories, Protein: $protein):")
        if (meals.isNotEmpty()) {
            meals.forEach { consoleIO.printer.showMessage(it.toString()) }
        } else {
            consoleIO.printer.showMessage("No meals found matching those criteria.")
        }
    }
}