package presentation.ui_holder

import io.mockk.verify

import com.thechance.logic.useCases.SoThinUseCase
import com.thechance.model.Meal
import com.thechance.model.NutritionFacts
import com.thechance.presentation.io.ConsoleIO
import com.thechance.presentation.io.Printer
import com.thechance.presentation.ui_holder.SoThinUi
import helper.createMeal
import io.mockk.every
import io.mockk.mockk

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SoThinUiTest {
    private lateinit var soThinUseCase: SoThinUseCase
    private lateinit var consoleIO: ConsoleIO
    private lateinit var printer: Printer
    private lateinit var soThinUi: SoThinUi


    @BeforeEach
    fun setUp() {
        soThinUseCase= mockk()
        printer= mockk(relaxed = true)
        consoleIO=mockk(relaxed = true)

            every {consoleIO.printer  }returns printer

        soThinUi=SoThinUi(soThinUseCase,consoleIO)

    }

    @Test
    fun `startUi shows suggested meal message when meal is returned  `() {

        //given
        val meal =
            createMeal(
                name = "High Calorie Burger",
                nutritionFactCalories = 750f,
                nutritionFactTotalFat = 1f,
                nutritionFactSugar = 1f,
                nutritionFactSodium = 1f,
                nutritionFactProtein = 1f,
                nutritionFactSaturatedFat = 1f,
                nutritionFactCarbohydrates = 1f
            )
        every { soThinUseCase.getPlus700Meal() } returns meal

         //when
        soThinUi.startUi()

          //then
        verify {
            printer.showMessage("Suggested Meal with more than 700 calories: High Calorie Burger - Calories: 750.0")
        }
    }

    @Test
    fun `startUi show no meal available message when meal is null  `() {
        //given
        every { soThinUseCase.getPlus700Meal() } returns null
        //when
        soThinUi.startUi()
          //then
        verify {
            printer.showMessage("No more meals with more than 700 calories available.")
        }
    }







    }