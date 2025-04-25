package presentation.ui_holder

import com.thechance.logic.useCases.SuggestEasyMealsUseCase
import com.thechance.presentation.io.ConsoleIO
import com.thechance.presentation.ui_holder.SuggestEasyMealsUi
import helper.createFakeMeal
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifySequence
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SuggestEasyMealsUiTest {
    private val consoleIO: ConsoleIO = mockk(relaxed = true)
    private val suggestFoodUseCase: SuggestEasyMealsUseCase = mockk(relaxed = true)
    private lateinit var suggestEasyMealsUi: SuggestEasyMealsUi

    @BeforeEach
    fun setup() {
        suggestEasyMealsUi = SuggestEasyMealsUi(suggestFoodUseCase, consoleIO)
    }


    @Test
    fun `should call getIraqiMeals function from getIraqiMealsUseCase`() {
        //When
        suggestEasyMealsUi.startUi()

        //Then
        verify(exactly = 1) { suggestFoodUseCase.getEasyMeals()}
    }

    @Test
    fun `should print the description message and display meal details when startUi function is called and meals are not empty`() {
        //Given
        val fakeMeal = createFakeMeal()
        every { suggestFoodUseCase.getEasyMeals() } returns listOf(
            fakeMeal
        )

        //When
        suggestEasyMealsUi.startUi()

        //Then
        verifySequence{
            consoleIO.printer.showMessage("Suggested Easy Meals:")
            consoleIO.printer.showMessage(fakeMeal.toString())
        }

    }

    @Test
    fun `should print the description message when startUi function is called and meals are empty`() {
        //Given
        every { suggestFoodUseCase.getEasyMeals() } returns emptyList()

        //When
        suggestEasyMealsUi.startUi()

        //Then
        verify(
            exactly = 1
        ) {
            consoleIO.printer.showMessage("No meals available.")
        }
    }
}