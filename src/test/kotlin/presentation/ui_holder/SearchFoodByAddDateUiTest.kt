package presentation.ui_holder


import com.thechance.logic.NoMealsWithGivenDateException
import com.thechance.logic.useCases.SearchFoodByAddDateUseCase
import com.thechance.model.NameDateMeal
import com.thechance.presentation.io.ConsoleIO
import com.thechance.presentation.ui_holder.SearchFoodByAddDateUi
import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat

class SearchFoodByAddDateUiTest {
    private val mockUseCase = mockk<SearchFoodByAddDateUseCase>()
    private val mockkConsoleIO = mockk<ConsoleIO>()

    private val date = SimpleDateFormat("yyyy-MM-dd").parse("2025-04-24")

    private lateinit var featureUi: SearchFoodByAddDateUi

    @BeforeEach
    fun setUp(){
        featureUi = SearchFoodByAddDateUi(mockUseCase, mockkConsoleIO)
    }


    @Test
    fun `should show meals and selected meal details`() {
        //given
        val meals = listOf(
            NameDateMeal(1, "Meal One", date),
            NameDateMeal(2, "Meal Two", date)
        )

        //when
        every { mockkConsoleIO.reader.readStringFromUser() } returns "2025-04-24"
        every { mockUseCase.getFoodByAddDate(date) } returns meals
        every { mockkConsoleIO.reader.readNumberFromUser() } returns 2
        every { mockkConsoleIO.printer.showMessage(any()) } just Runs

        featureUi.startUi()

        //then
        verifySequence {
            mockkConsoleIO.printer.showMessage("Please Enter the desired date : yyyy-MM-dd")
            mockkConsoleIO.reader.readStringFromUser()
            mockUseCase.getFoodByAddDate(date)

            mockkConsoleIO.printer.showMessage(
                """
            Meal Id: 1
            Meal Name: Meal One
            Meal Date: $date
            --------------------------
        """.trimIndent()
            )
            mockkConsoleIO.printer.showMessage(
                """
            Meal Id: 2
            Meal Name: Meal Two
            Meal Date: $date
            --------------------------
        """.trimIndent()
            )
            mockkConsoleIO.printer.showMessage("Enter the ID To see details : ")
            mockkConsoleIO.reader.readNumberFromUser()
            mockkConsoleIO.printer.showMessage("NameDateMeal(id=2, name=Meal Two, date=$date)")
        }
    }

    @Test
    fun `should show message for invalid ID`() {
        //given
        val meals = listOf(NameDateMeal(1, "Meal One", date))

        every { mockkConsoleIO.reader.readStringFromUser() } returns "2025-04-24"
        every { mockUseCase.getFoodByAddDate(date) } returns meals
        every { mockkConsoleIO.reader.readNumberFromUser() } returns 99
        every { mockkConsoleIO.printer.showMessage(any()) } just Runs

        featureUi.startUi()

        verify {
            mockkConsoleIO.printer.showMessage("No meal found with ID: 99")
        }
    }

    @Test
    fun `should print NoMealsWithGivenDateException message when no meals added the given date`() {
        //given
        every { mockkConsoleIO.reader.readStringFromUser() } returns "2025-04-24"
        every { mockUseCase.getFoodByAddDate(date) } returns listOf()
        every { mockkConsoleIO.printer.showMessage(any()) } just Runs

        //when & then
        featureUi.startUi()

        verify { mockkConsoleIO.printer.showMessage(NoMealsWithGivenDateException(date).message.toString()) }

    }

    @Test
    fun `should print InvalidDateFormatException message when the given date format is malformed`() {
        //given
        every { mockkConsoleIO.reader.readStringFromUser() } returns "10-notarealdate2010" andThen "2010-10-11"
        every { mockUseCase.getFoodByAddDate(any()) } returns listOf()
        every { mockkConsoleIO.printer.showMessage(any()) } just Runs

        //when & then
        featureUi.startUi()

        verify { mockkConsoleIO.printer.showMessage("Incorrect Date Format! Please try again.") }

    }


}