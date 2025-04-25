package presentation.ui_holder

import com.thechance.logic.useCases.KetoDietMealUseCase
import com.thechance.presentation.io.ConsoleIO
import com.thechance.presentation.ui_holder.KetoDietMealUi
import helper.createMeal
import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource


class KetoDietMealUiTest {
    private val mockUseCase = mockk<KetoDietMealUseCase>()
    private val mockkConsoleIO = mockk<ConsoleIO>()

    private lateinit var featureUi: KetoDietMealUi

    @BeforeEach
    fun setUp(){
        featureUi = KetoDietMealUi(mockUseCase, mockkConsoleIO)
    }

    @Test
    fun `should print message when no keto meal is returned`() {
        every { mockUseCase.suggestKetoMeal() } returns null
        every { mockkConsoleIO.printer.showMessage(any()) } just Runs

        featureUi.startUi()

        verify { mockkConsoleIO.printer.showMessage("No more Keto Diet Meals Available.") }
    }

    @ParameterizedTest
    @CsvSource(
        "yes,Details",
        "no,No more Keto Diet Meals Available.",
        "-,Invalid Input!"
    )
    fun `should print suitable message for each input`(input: String, expectedThirdMessage: String) {
        val ketoMeal = createMeal()
        every { mockUseCase.suggestKetoMeal() } returns ketoMeal andThen null
        every { mockkConsoleIO.reader.readStringFromUser() } returns input
        every { mockkConsoleIO.printer.showMessage(any()) } just Runs

        featureUi.startUi()

        verifySequence {
            mockkConsoleIO.printer.showMessage("Suggested Keto Meal: ${ketoMeal.name} - ${ketoMeal.description}")
            mockkConsoleIO.printer.showMessage("Would you like to see more details for ${ketoMeal.name}? (yes/no)")

            mockkConsoleIO.reader.readStringFromUser()

            if (input == "yes"){
                mockkConsoleIO.printer.showMessage("$expectedThirdMessage: $ketoMeal")
            } else {
                mockkConsoleIO.printer.showMessage(expectedThirdMessage)
            }
        }
    }

}