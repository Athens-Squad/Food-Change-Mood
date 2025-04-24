package com.thechance.presentation.ui_holder

import com.thechance.logic.useCases.GetHealthyMealsUseCase
import com.thechance.presentation.BaseFeatureUi
import com.thechance.presentation.io.ConsoleIO

class HealthyFastFoodMealsUi(
    private val getHealthyMealsUseCase: GetHealthyMealsUseCase,
    private val consoleIO: ConsoleIO,
    override val featureNumber: Int = 1,
    override val featureName: String = "List Healthy Fast Food Meals"
) : BaseFeatureUi{
    override fun startUi() {
        val healthyMeals = getHealthyMealsUseCase.getHealthyFastMeals()
        if (healthyMeals.isNotEmpty()) {
            consoleIO.printer.showMessage("Healthy Fast Food Meals (15 minutes or less):")
            healthyMeals.forEach { consoleIO.printer.showMessage(it.name) }
        } else {
            consoleIO.printer.showMessage("No healthy meals found.")
        }
    }

}