package presentation.ui_holder

import com.thechance.logic.useCases.GetHealthyMealsUseCase
import com.thechance.presentation.io.ConsoleIO
import com.thechance.presentation.ui_holder.HealthyFastFoodMealsUi
import helper.createMeal
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifySequence
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HealthyFastFoodMealsUiTest {

    private val consoleIO: ConsoleIO = mockk(relaxed = true)
    private val getHealthyMealsUseCase: GetHealthyMealsUseCase = mockk(relaxed = true)
    private lateinit var getHealthyFastFoodMealsUi: HealthyFastFoodMealsUi

    @BeforeEach
    fun setup() {
        getHealthyFastFoodMealsUi = HealthyFastFoodMealsUi(getHealthyMealsUseCase, consoleIO)
    }


    @Test
    fun `should call getHealthyFastMeals function from GetHealthyMealsUseCase`() {
        //When
        getHealthyFastFoodMealsUi.startUi()

        //Then
        verify(exactly = 1) { getHealthyMealsUseCase.getHealthyFastMeals() }
    }

    @Test
    fun `should print the description message and display meal details when startUi function is called and meals are not empty`() {
        //Given
        val fakeMeal = createMeal()
        every { getHealthyMealsUseCase.getHealthyFastMeals() } returns listOf(
            fakeMeal
        )

        //When
        getHealthyFastFoodMealsUi.startUi()

        //Then
        verifySequence {
            consoleIO.printer.showMessage("Healthy Fast Food Meals (15 minutes or less):")
            consoleIO.printer.showMessage(fakeMeal.name)
        }

    }


    @Test
    fun `should print the description message when startUi function is called and meals are empty`() {
        //Given
        every { getHealthyMealsUseCase.getHealthyFastMeals() } returns emptyList()

        //When
        getHealthyFastFoodMealsUi.startUi()

        //Then
        verify(
            exactly = 1
        ) {
            consoleIO.printer.showMessage("No healthy meals found.")
        }
    }


}