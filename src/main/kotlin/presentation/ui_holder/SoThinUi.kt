package com.thechance.presentation.ui_holder

import com.thechance.logic.useCases.SoThinUseCase
import com.thechance.presentation.BaseFeatureUi
import com.thechance.presentation.io.ConsoleIO

class SoThinUi(
    private val soThinUseCase: SoThinUseCase,
    private val consoleIO: ConsoleIO,
    override val featureNumber: Int = 13,
    override val featureName: String = "Suggest a Meal with More Than 700 Calories"
) : BaseFeatureUi {
    override fun startUi() {
        val meal = soThinUseCase.getPlus700Meal()
        if (meal != null){ consoleIO.printer.showMessage("Suggested Meal with more than 700 calories: ${meal.name} - Calories: ${meal.nutritionFacts.calories}")
        }else consoleIO.printer.showMessage("No more meals with more than 700 calories available.")

    }
}