package com.thechance.presentation.ui_holder

import com.thechance.logic.NoMealsWithGivenDateException
import com.thechance.logic.useCases.SearchFoodByAddDateUseCase
import com.thechance.model.Meal
import com.thechance.model.NameDateMeal
import com.thechance.presentation.BaseFeatureUi
import com.thechance.presentation.io.ConsoleIO
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class SearchFoodByAddDateUi(
    private val searchFoodByAddDateUseCase: SearchFoodByAddDateUseCase,
    private val consoleIO: ConsoleIO,
    override val featureNumber: Int = 8,
    override val featureName: String = "Search Food By Add Date"
) : BaseFeatureUi{
    override fun startUi() {

        val date = receiveDateFromUser()
        val meals = fetchMealsByDate(date)

        if (meals.isEmpty()) return

        displayMealList(meals)

        consoleIO.printer.showMessage("Enter the ID To see details : ")
        val mealId = consoleIO.reader.readNumberFromUser()
        showMealBy(mealId, meals)

    }

    private fun receiveDateFromUser(): Date {
        while (true) {
            try {
                consoleIO.printer.showMessage("Please Enter the desired date : yyyy-MM-dd")
                return SimpleDateFormat("yyyy-MM-dd").parse(consoleIO.reader.readStringFromUser())

            } catch (incorrectFormatException: ParseException) {
                consoleIO.printer.showMessage("Incorrect Date Format! Please try again.")
            }
        }
    }

    private fun fetchMealsByDate(date: Date): List<NameDateMeal> {
        return try {
            searchFoodByAddDateUseCase.getFoodByAddDate(date).ifEmpty { throw NoMealsWithGivenDateException(date) }

        } catch (noDataException: NoMealsWithGivenDateException) {
            consoleIO.printer.showMessage(noDataException.message.toString())
            emptyList()
        }
    }

    private fun displayMealList(meals: List<NameDateMeal>) {
        meals.forEach { meal ->
            consoleIO.printer.showMessage("""
            Meal Id: ${meal.id}
            Meal Name: ${meal.name}
            Meal Date: ${meal.date}
            --------------------------
        """.trimIndent())
        }
    }

    private fun showMealBy(mealId: Int, meals: List<NameDateMeal>) {

        meals.find { it.id == mealId }?.let { meal ->

            consoleIO.printer.showMessage(meal.toString())

        } ?: consoleIO.printer.showMessage("No meal found with ID: $mealId")
    }

}