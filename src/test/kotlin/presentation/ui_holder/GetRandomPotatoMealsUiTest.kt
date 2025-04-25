package presentation.ui_holder

import com.thechance.logic.useCases.GetRandomTenMealIncludePotatoUseCase
import com.thechance.presentation.io.ConsoleIO
import com.thechance.presentation.io.Printer
import com.thechance.presentation.ui_holder.GetRandomPotatoMealsUi
import com.thechance.presentation.ui_holder.SoThinUi
import helper.createFakeMeal
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifySequence
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class GetRandomPotatoMealsUiTest {


    private lateinit var getRandomTenMealIncludePotatoUseCase: GetRandomTenMealIncludePotatoUseCase
    private lateinit var consoleIO: ConsoleIO
    private lateinit var printer: Printer
    private lateinit var getRandomPotatoMealsUi:  GetRandomPotatoMealsUi

    @BeforeEach
    fun setUp() {
        getRandomTenMealIncludePotatoUseCase= mockk()
        printer= mockk(relaxed = true)
        consoleIO= mockk(relaxed = true)

        every {consoleIO.printer  } returns printer

        getRandomPotatoMealsUi= GetRandomPotatoMealsUi(getRandomTenMealIncludePotatoUseCase,consoleIO)

    }

    @Test
    fun `startUi when meals are returned should print all potato meals`() {
        val meals = listOf(
            createFakeMeal(name = "Potato Curry"),
            createFakeMeal(name = "Mashed Potatoes")
        )

        every { getRandomTenMealIncludePotatoUseCase.suggestPotatoMeals() } returns meals

        getRandomPotatoMealsUi.startUi()

        verifySequence {
            printer.showMessage("Here are 10 meals that include potatoes:")
            printer.showMessage("1: ${meals[0]}")
            printer.showMessage("2: ${meals[1]}")
        }
    }


    @Test
    fun `startUi when no meals returned should print no potato meals message`() {
        every { getRandomTenMealIncludePotatoUseCase.suggestPotatoMeals() } returns emptyList()

        getRandomPotatoMealsUi.startUi()

        verifySequence {
            printer.showMessage("Here are 10 meals that include potatoes:")
            printer.showMessage("No potato meals found.")
        }
    }












}