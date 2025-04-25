package presentation.ui_holder

import com.thechance.logic.useCases.GymHelperUseCase
import com.thechance.model.NutritionFacts
import com.thechance.presentation.io.ConsoleIO
import com.thechance.presentation.ui_holder.GymHelperUi
import helper.createMeal
import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat

class GymHelperUiTest{
    private val mockUseCase = mockk<GymHelperUseCase>()
    private val mockkConsoleIO = mockk<ConsoleIO>()

    private val date = SimpleDateFormat("yyyy-MM-dd").parse("2025-04-24")

    private lateinit var featureUi: GymHelperUi

    @BeforeEach
    fun setUp(){
        featureUi = GymHelperUi(mockUseCase, mockkConsoleIO)
    }

    @Test
    fun `should display matched meals based on user input`() {
        //given
        val meals = listOf(
            createMeal(
                nutritionFactCalories = 400f,
                nutritionFactTotalFat = 1f,
                nutritionFactSugar = 1f,
                nutritionFactSodium = 0f,
                nutritionFactProtein = 30f,
                nutritionFactSaturatedFat = 1f,
                nutritionFactCarbohydrates = 1f
            )
        )
        every { mockUseCase.getMealsByCaloriesAndProtein(400, 30) } returns meals
        every { mockkConsoleIO.reader.readNumberFromUser() } returnsMany listOf(400, 30)
        every { mockkConsoleIO.printer.showMessage(any()) } just Runs

        // When
        featureUi.startUi()

        // Then
        verifySequence {
            mockkConsoleIO.printer.showMessage("Enter desired calories:")
            mockkConsoleIO.reader.readNumberFromUser()
            mockkConsoleIO.printer.showMessage("Enter desired protein:")
            mockkConsoleIO.reader.readNumberFromUser()

            mockUseCase.getMealsByCaloriesAndProtein(400, 30)

            mockkConsoleIO.printer.showMessage("Meals matching your criteria (Calories: 400, Protein: 30):")
            mockkConsoleIO.printer.showMessage(meals[0].toString())
        }
    }

    @Test
    fun `should display no results message when no meals match`() {
        // Given
        every { mockkConsoleIO.printer.showMessage(any()) } just Runs
        every { mockkConsoleIO.reader.readNumberFromUser() } returnsMany listOf(100, 10)
        every { mockUseCase.getMealsByCaloriesAndProtein(100, 10) } returns emptyList()

        // When
        featureUi.startUi()

        // Then
        verifySequence {
            mockkConsoleIO.printer.showMessage("Enter desired calories:")
            mockkConsoleIO.reader.readNumberFromUser()
            mockkConsoleIO.printer.showMessage("Enter desired protein:")
            mockkConsoleIO.reader.readNumberFromUser()

            mockUseCase.getMealsByCaloriesAndProtein(100, 10)
            mockkConsoleIO.printer.showMessage("Meals matching your criteria (Calories: 100, Protein: 10):")
            mockkConsoleIO.printer.showMessage("No meals found matching those criteria.")
        }
    }

}