package com.thechance.presentation.ui_holder

import com.thechance.logic.useCases.GetIraqiMealsUseCase
import com.thechance.presentation.BaseFeatureUi
import com.thechance.presentation.io.ConsoleIO

class IdentifyIraqiMealsUi(
    private val getIraqiMealsUseCase: GetIraqiMealsUseCase,
    private val consoleIO: ConsoleIO,
    override val featureNumber: Int = 3,
    override val featureName: String = "List Iraqi Meals"
) : BaseFeatureUi {
    override fun startUi() {
        val iraqiMeals = getIraqiMealsUseCase.getIraqiMeals()
        if (iraqiMeals.isNotEmpty()) {
            consoleIO.printer.showMessage("Iraqi Meals:")
            iraqiMeals.forEach { consoleIO.printer.showMessage(it.toString()) }
        } else {
            consoleIO.printer.showMessage("No Iraqi meals found.")
        }
    }

}