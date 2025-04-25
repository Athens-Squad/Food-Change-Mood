package com.thechance.presentation.ui_holder

import com.thechance.logic.useCases.GetItalianMealsForLargeGroupsUseCase
import com.thechance.presentation.BaseFeatureUi
import com.thechance.presentation.io.ConsoleIO

class GetItalianMealsForLargeGroupsUi(
    private val getItalianMealsForLargeGroupsUseCase: GetItalianMealsForLargeGroupsUseCase,
    private val consoleIO: ConsoleIO,
    override val featureNumber: Int = 15,
    override val featureName: String = "Large Group Italian Meals"
) : BaseFeatureUi {
    override fun startUi() {
        getItalianMealsForLargeGroupsUseCase.suggestItalianMealsForLargeGroups()
            .forEach { meal ->
                consoleIO.printer.showMessage(meal.toString())
            }
    }

}