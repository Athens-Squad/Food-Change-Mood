package com.thechance.presentation.ui_holder

import com.thechance.logic.useCases.GetIraqiMealsUseCase
import com.thechance.presentation.BaseFeatureUi
import com.thechance.presentation.IO.ConsoleIO

class IdentifyIraqiMealsUi(
    private val getIraqiMealsUseCase: GetIraqiMealsUseCase,
    private val consoleIO: ConsoleIO,
    override val featureNumber: Int = 3,
    override val featureName: String = "List Iraqi Meals"
) : BaseFeatureUi {
    override fun startUi() {
        val iraqiMeals = getIraqiMealsUseCase.getIraqiMeals()
        consoleIO.printer.showMessage("Iraqi Meals:")
        if (iraqiMeals.isNotEmpty()) {
            iraqiMeals.forEach { consoleIO.printer.showMessage(it.toString()) }
        } else {
            consoleIO.printer.showMessage("No Iraqi meals found.")
        }
    }

}