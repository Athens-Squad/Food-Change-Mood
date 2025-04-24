package presentation.ui_holder

import com.thechance.logic.useCases.SearchByCountryName
import com.thechance.presentation.io.ConsoleIO
import com.thechance.presentation.ui_holder.SearchMealByCountryUi
import helper.createFakeMeal
import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SearchMealByCountryUiTest{
    private val mockUseCase = mockk<SearchByCountryName>()
    private val mockkConsoleIO = mockk<ConsoleIO>()

    private lateinit var featureUi: SearchMealByCountryUi

    @BeforeEach
    fun setUp(){
        featureUi = SearchMealByCountryUi(mockUseCase, mockkConsoleIO)
    }

    @ParameterizedTest
    @CsvSource(
        "Mexico",
        "Italy"
    )
    fun `should display meals found for a valid country`(country: String) {
        //given
        val meals = listOf(createFakeMeal(name = "Mexican Cheese"))


        every { mockkConsoleIO.reader.readStringFromUser() } returns country
        every { mockUseCase.getMealsByCountry(country) } returns when(country) {
            "Mexico" -> meals
            else -> emptyList()
        }
        every { mockkConsoleIO.printer.showMessage(any()) } just Runs

        //when
        featureUi.startUi()

        //then
        verifySequence {
            mockkConsoleIO.printer.showMessage("Enter country name:")
            mockkConsoleIO.reader.readStringFromUser()

            mockUseCase.getMealsByCountry(country)
            mockkConsoleIO.printer.showMessage("Meals related to $country:")
            if (country == "Mexico") {
                mockkConsoleIO.printer.showMessage("Mexican Cheese")
            } else {
                mockkConsoleIO.printer.showMessage("No meals found for the country $country.")
            }
        }
    }




}