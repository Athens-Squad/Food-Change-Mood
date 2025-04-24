package com.thechance.presentation.ui_holder

import com.thechance.logic.useCases.GetSeaFoodMealsSortedByProteinContent
import com.thechance.presentation.BaseFeatureUi
import com.thechance.presentation.IO.ConsoleIO

class GetSeaFoodMealsSortedByProteinUi(
    private val getSeaFoodMealsSortedByProteinContent: GetSeaFoodMealsSortedByProteinContent,
    private val consoleIO: ConsoleIO,
    override val featureNumber: Int = 14,
    override val featureName: String ="List Seafood Meals Sorted by Protein Content"
) : BaseFeatureUi{
    override fun startUi() {
        val seafoodMeals =
            getSeaFoodMealsSortedByProteinContent.getSeaFoodMealsSortedByProteinContent()
        consoleIO.printer.showMessage("Seafood Meals Sorted by Protein Content:")
        if (seafoodMeals.isNotEmpty()) {
            seafoodMeals.forEach { meal ->
                consoleIO.printer.showMessage("${meal.rank}: ${meal.name} - Protein: ${meal.proteinAmount}g")
            }
        } else {
            consoleIO.printer.showMessage("No seafood meals found.")
        }
    }

}