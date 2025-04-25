package presentation.ui_holder

import com.thechance.logic.useCases.GetRandomSweetWithNoEggsUseCase
import com.thechance.presentation.io.ConsoleIO
import com.thechance.presentation.ui_holder.GetRandomSweetsWithNoEggsUi
import helper.createMeal
import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class GetRandomSweetsWithNoEggsUiTest {
    private val mockUseCase = mockk<GetRandomSweetWithNoEggsUseCase>()
    private val mockkConsoleIO = mockk<ConsoleIO>()

    private lateinit var featureUi: GetRandomSweetsWithNoEggsUi

    @BeforeEach
    fun setUp(){
        featureUi = GetRandomSweetsWithNoEggsUi(mockUseCase, mockkConsoleIO)
    }

    @Test
    fun `should print message when no sweet meal is returned`() {
        every { mockUseCase.suggestASweetMeal() } returns null
        every { mockkConsoleIO.printer.showMessage(any()) } just Runs

        featureUi.startUi()

        verify { mockkConsoleIO.printer.showMessage("No more egg-free sweets available.") }
    }


    @ParameterizedTest
    @CsvSource(
        "yes,Details",
        "no,No more egg-free sweets available.",
        "-,Invalid Input!"
    )
    fun `should print suitable message for each input`(input: String, expectedThirdMessage: String) {
        val sweet = createMeal()
        every { mockUseCase.suggestASweetMeal() } returns sweet andThen null
        every { mockkConsoleIO.reader.readStringFromUser() } returns input
        every { mockkConsoleIO.printer.showMessage(any()) } just Runs

        featureUi.startUi()

        verifySequence {
            mockkConsoleIO.printer.showMessage("Suggested Sweet (No Eggs): ${sweet.name} - ${sweet.description}")
            mockkConsoleIO.printer.showMessage("Would you like to see more details for ${sweet.name}? (yes/no)")

            mockkConsoleIO.reader.readStringFromUser()

            if (input == "yes"){
                mockkConsoleIO.printer.showMessage("$expectedThirdMessage: $sweet")
            } else {
                mockkConsoleIO.printer.showMessage(expectedThirdMessage)
            }
        }
    }



}