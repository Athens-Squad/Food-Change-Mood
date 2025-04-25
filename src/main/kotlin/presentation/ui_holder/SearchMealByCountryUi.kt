package com.thechance.presentation.ui_holder

import com.thechance.logic.useCases.SearchByCountryName
import com.thechance.model.Meal
import com.thechance.presentation.BaseFeatureUi
import com.thechance.presentation.io.ConsoleIO

class SearchMealByCountryUi(
    private val searchByCountryName: SearchByCountryName,
    private val consoleIO: ConsoleIO,
    override val featureNumber: Int = 10,
    override val featureName: String = "Search Meals By Country"
) : BaseFeatureUi {
    override fun startUi() {
        val country = receiveCountryFromUser()

        val meals = findMealsByCountry(country)

        displayMealsResult(country, meals)
    }

    private fun receiveCountryFromUser(): String {
        consoleIO.printer.showMessage("Enter country name:")
        return consoleIO.reader.readStringFromUser()
    }

    private fun  findMealsByCountry(country: String): List<Meal> {
        return searchByCountryName.getMealsByCountry(country)
    }

    private fun displayMealsResult(country: String, meals: List<Meal>) {
        consoleIO.printer.showMessage("Meals related to $country:")

        if (meals.isEmpty()) {
            consoleIO.printer.showMessage("No meals found for the country $country.")
            return
        }

        meals.forEach { meal ->
            consoleIO.printer.showMessage(meal.name)
        }
    }
}