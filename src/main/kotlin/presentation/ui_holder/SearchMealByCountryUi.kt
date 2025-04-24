package com.thechance.presentation.ui_holder

import com.thechance.logic.useCases.SearchByCountryName
import com.thechance.presentation.BaseFeatureUi
import com.thechance.presentation.io.ConsoleIO

class SearchMealByCountryUi(
    private val searchByCountryName: SearchByCountryName,
    private val consoleIO: ConsoleIO,
    override val featureNumber: Int = 10,
    override val featureName: String = "Search Meals By Country"
) : BaseFeatureUi {
    override fun startUi() {
        consoleIO.printer.showMessage("Enter country name:")
        val country = consoleIO.reader.readStringFromUser()
        val meals = searchByCountryName.getMealsByCountry(country)
        consoleIO.printer.showMessage("Meals related to $country:")
        if (meals.isNotEmpty()) {
            meals.forEach { consoleIO.printer.showMessage(it.name) }
        } else {
            consoleIO.printer.showMessage("No meals found for the country $country.")
        }
    }
}