package presentation.ui_holder

import com.thechance.logic.useCases.mealSearchUseCase.MealSearchUseCase
import com.thechance.presentation.io.ConsoleIO
import com.thechance.presentation.ui_holder.SearchMealByNameUi
import helper.createFakeMeal
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SearchMealByNameUiTest {

    private val consoleIO: ConsoleIO = mockk(relaxed = true)
    private val searchMealsUseCase: MealSearchUseCase = mockk(relaxed = true)
    private lateinit var searchMealByNameUi: SearchMealByNameUi

    @BeforeEach
    fun setup() {
        searchMealByNameUi = SearchMealByNameUi(searchMealsUseCase, consoleIO)
    }


    @Test
    fun `should call search function from MealSearchUseCase`() {
        //Given
        every { consoleIO.reader.readStringFromUser() } returns PIZZA

        //When
        searchMealByNameUi.startUi()

        //Then
        verify(exactly = 1) { searchMealsUseCase.search(PIZZA) }
    }

    @Test
    fun `should display description when entering the startUi function`() {
        //When
        searchMealByNameUi.startUi()

        //Then
        verify {
            consoleIO.printer.showMessage("Please Enter the Meal Name: ")
        }
    }

    @Test
    fun `should display meals when found in search`() {
        //Given
        val fakeMeal = createFakeMeal(name = PIZZA)
        every { consoleIO.reader.readStringFromUser() } returns PIZZA
        every { searchMealsUseCase.search(PIZZA) } returns listOf(fakeMeal)

        //When
        searchMealByNameUi.startUi()

        //Then
        verify { consoleIO.printer.showMessage(fakeMeal.toString()) }
    }

    @Test
    fun `should display no meals found when no matching meals found`() {
        //Given
        every { consoleIO.reader.readStringFromUser() } returns PIZZA
        every { searchMealsUseCase.search(PIZZA) } returns emptyList()

        //When
        searchMealByNameUi.startUi()

        //Then
        verify {
            consoleIO.printer.showMessage("No meals found.")

        }
    }

    companion object {
        const val PIZZA = "pizza"
    }


}