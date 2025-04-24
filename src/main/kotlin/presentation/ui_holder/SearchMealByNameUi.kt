package com.thechance.presentation.ui_holder

import com.thechance.logic.useCases.mealSearchUseCase.MealSearchUseCase
import com.thechance.presentation.BaseFeatureUi
import com.thechance.presentation.IO.ConsoleIO

class SearchMealByNameUi(
    private val mealSearchUseCase: MealSearchUseCase,
    private val consoleIO: ConsoleIO,
    override val featureNumber: Int = 2,
    override val featureName: String = "Search Meal by Name"
) : BaseFeatureUi {
    override fun startUi() {
        consoleIO.printer.showMessage("Please Enter the Meal Name: ")
        val searchKeyWord = consoleIO.reader.readStringFromUser()
        mealSearchUseCase.search(searchKeyWord)
            .forEach { meal ->
                consoleIO.printer.showMessage(meal.toString())
            }
    }
}