package com.thechance.presentation.ui_holder

import com.thechance.logic.NoMealsWithGivenDateException
import com.thechance.logic.useCases.SearchFoodByAddDateUseCase
import com.thechance.presentation.BaseFeatureUi
import com.thechance.presentation.IO.ConsoleIO
import java.text.ParseException
import java.text.SimpleDateFormat

class SearchFoodByAddDateUi(
    private val searchFoodByAddDateUseCase: SearchFoodByAddDateUseCase,
    private val consoleIO: ConsoleIO,
    override val featureNumber: Int = 8,
    override val featureName: String = "Search Food By Add Date"
) : BaseFeatureUi{
    override fun startUi() {
        consoleIO.printer.showMessage("Please Enter the desired date : yyyy-MM-dd")
        try {
            val date = SimpleDateFormat("yyyy-MM-dd").parse(readlnOrNull())
            searchFoodByAddDateUseCase.getFoodByAddDate(date)
                .forEach { nameDateMeal ->
                    consoleIO.printer.showMessage("Meal Id : ${nameDateMeal.id} \n" +
                            "Meal Name : ${nameDateMeal.name} \n Meal Date : ${nameDateMeal.date}\n")
                }
            consoleIO.printer.showMessage("Enter the ID To see details : ")
            consoleIO.printer.showMessage(
                searchFoodByAddDateUseCase.getMealById(consoleIO.reader.readNumberFromUser()).toString()
            )
        } catch (incorrectFormatException: ParseException) {
            consoleIO.printer.showMessage("Incorrect Date Format!")
            startUi()
        } catch (noDataException: NoMealsWithGivenDateException) {
            consoleIO.printer.showMessage(noDataException.message.toString())
            return
        }
    }
}