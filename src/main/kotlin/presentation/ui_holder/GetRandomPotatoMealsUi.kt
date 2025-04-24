package com.thechance.presentation.ui_holder

import com.thechance.logic.useCases.GetRandomTenMealIncludePotatoUseCase
import com.thechance.presentation.BaseFeatureUi
import com.thechance.presentation.IO.ConsoleIO

class GetRandomPotatoMealsUi(
    private val getRandomTenMealIncludePotatoUseCase: GetRandomTenMealIncludePotatoUseCase,
    private val consoleIO: ConsoleIO,
    override val featureNumber: Int = 12,
    override val featureName: String = "Suggest 10 Meals with Potatoes"
) : BaseFeatureUi {
    override fun startUi() {
        val meals = getRandomTenMealIncludePotatoUseCase.suggestPotatoMeals()
        consoleIO.printer.showMessage("Here are 10 meals that include potatoes:")
        if (meals.isNotEmpty()) {
            meals.forEachIndexed { index, meal ->
                consoleIO.printer.showMessage("${index + 1}: $meal")
            }
        } else {
            consoleIO.printer.showMessage("No potato meals found.")
        }
    }
}