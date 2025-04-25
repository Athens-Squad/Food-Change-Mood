package presentation.ui_holder

import com.thechance.logic.useCases.GetIraqiMealsUseCase
import com.thechance.presentation.io.ConsoleIO
import com.thechance.presentation.ui_holder.IdentifyIraqiMealsUi
import helper.createFakeMeal
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class IdentifyIraqiMealsUiTest {
    private val consoleIO: ConsoleIO = mockk(relaxed = true)
    private val getIraqiMealsUseCase: GetIraqiMealsUseCase = mockk(relaxed = true)
    private lateinit var identifyIraqiMealsUi: IdentifyIraqiMealsUi

    @BeforeEach
    fun setup() {
        identifyIraqiMealsUi = IdentifyIraqiMealsUi(getIraqiMealsUseCase, consoleIO)
    }


    @Test
    fun `should call getIraqiMeals function from getIraqiMealsUseCase`() {
        //When
        identifyIraqiMealsUi.startUi()

        //Then
        verify(exactly = 1) { getIraqiMealsUseCase.getIraqiMeals() }
    }

    @Test
    fun `should print the description message and display meal details when startUi function is called and meals are not empty`() {
        //Given
        val fakeMeal = createFakeMeal()
        every { getIraqiMealsUseCase.getIraqiMeals() } returns listOf(
            fakeMeal
        )

        //When
        identifyIraqiMealsUi.startUi()

        //Then
        verify {
            consoleIO.printer.showMessage("Iraqi Meals:")
        }
        verify {
            consoleIO.printer.showMessage(fakeMeal.toString())
        }
    }

    @Test
    fun `should print the description message when startUi function is called and meals are empty`() {
        //Given
        every { getIraqiMealsUseCase.getIraqiMeals() } returns emptyList()

        //When
        identifyIraqiMealsUi.startUi()

        //Then
        verify(
            exactly = 1
        ) {
            consoleIO.printer.showMessage("No Iraqi meals found.")
        }
    }
}