package com.thechance.presentation.ui_holder

import com.thechance.logic.useCases.SuggestEasyMealsUseCase
import com.thechance.presentation.BaseFeatureUi
import com.thechance.presentation.io.ConsoleIO

class SuggestEasyMealsUi(
    private val suggestFoodUseCase: SuggestEasyMealsUseCase,
    private val consoleIO: ConsoleIO,
    override val featureNumber: Int = 4,
    override val featureName: String = "Suggest Easy Meals"
) : BaseFeatureUi{
    override fun startUi() {
        val easyMeals = suggestFoodUseCase.getEasyMeals()
        if (easyMeals.isNotEmpty()) {
            consoleIO.printer.showMessage("Suggested Easy Meals:")
            easyMeals.forEach { consoleIO.printer.showMessage(it.toString()) }
        } else {
            consoleIO.printer.showMessage("No meals available.")
        }
    }
}