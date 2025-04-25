package com.thechance.presentation.ui_holder

import com.thechance.logic.useCases.GymHelperUseCase
import com.thechance.model.Meal
import com.thechance.presentation.BaseFeatureUi
import com.thechance.presentation.io.ConsoleIO

class GymHelperUi(
    private val gymHelperUseCase: GymHelperUseCase,
    private val consoleIO: ConsoleIO,
    override val featureNumber: Int = 9,
    override val featureName: String = "Gym Helper"
) : BaseFeatureUi {
    override fun startUi() {
        val (calories, protein) = receiveCaloriesAndProteinFromUser()

        val meals = findMatchingMeals(calories, protein)

        displayMealResults(calories, protein, meals)
    }

    private fun receiveCaloriesAndProteinFromUser(): Pair<Int, Int> {
        val calories = receiveNumberFromUser("Enter desired calories:")
        val protein = receiveNumberFromUser("Enter desired protein:")
        return Pair(calories, protein)
    }

    private fun receiveNumberFromUser(message: String): Int {
        consoleIO.printer.showMessage(message)
        return consoleIO.reader.readNumberFromUser()
    }

    private fun findMatchingMeals(calories: Int, protein: Int): List<Meal> {
        return gymHelperUseCase.getMealsByCaloriesAndProtein(calories, protein)
    }

    private fun displayMealResults(calories: Int, protein: Int, meals: List<Meal>) {
        consoleIO.printer.showMessage("Meals matching your criteria (Calories: $calories, Protein: $protein):")

        if (meals.isEmpty()) {
            consoleIO.printer.showMessage("No meals found matching those criteria.")
            return
        }

        meals.forEach { meal ->
            consoleIO.printer.showMessage(meal.toString())
        }
    }
}