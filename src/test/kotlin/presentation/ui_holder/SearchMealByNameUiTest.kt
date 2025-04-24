package presentation.ui_holder

import com.thechance.logic.useCases.GetHealthyMealsUseCase
import com.thechance.logic.useCases.mealSearchUseCase.MealSearchUseCase
import com.thechance.presentation.io.ConsoleIO
import com.thechance.presentation.ui_holder.HealthyFastFoodMealsUi
import com.thechance.presentation.ui_holder.SearchMealByNameUi
import helper.createFakeMeal
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SearchMealByNameUiTest{

    private val consoleIO : ConsoleIO = mockk(relaxed = true)
    private val searchMealsUseCase: MealSearchUseCase = mockk(relaxed = true)
    private lateinit var searchMealByNameUi: SearchMealByNameUi

    @BeforeEach
    fun setup(){
        searchMealByNameUi = SearchMealByNameUi(searchMealsUseCase, consoleIO)
    }


    @Test
    fun `should call search function from MealSearchUseCase`(){
       //Given
        every { consoleIO.reader.readStringFromUser() } returns "Pizza"

        //When
        searchMealByNameUi.startUi()

        //Then
        verify(exactly = 1){ searchMealsUseCase.search("Pizza") }
    }

//    @Test
//    fun `should print the description message when startUi function is called and meals are not empty`(){
//        //Given
//        every {  getHealthyMealsUseCase.getHealthyFastMeals()} returns listOf(
//            createFakeMeal(), createFakeMeal(), createFakeMeal()
//        )
//
//        //When
//        getHealthyFastFoodMealsUi.startUi()
//
//        //Then
//        verify {
//            consoleIO.printer.showMessage("Healthy Fast Food Meals (15 minutes or less):")
//        }
//    }
//
//    @Test
//    fun `should print the description message when startUi function is called and meals are empty`(){
//        //Given
//        every {  getHealthyMealsUseCase.getHealthyFastMeals()} returns emptyList()
//
//        //When
//        getHealthyFastFoodMealsUi.startUi()
//
//        //Then
//        verify(
//            exactly = 1
//        ) {
//            consoleIO.printer.showMessage("No healthy meals found.")
//        }
//    }



}