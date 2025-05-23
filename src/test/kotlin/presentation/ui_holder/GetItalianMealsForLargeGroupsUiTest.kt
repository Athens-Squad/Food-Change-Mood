package presentation.ui_holder

import com.thechance.logic.useCases.GetItalianMealsForLargeGroupsUseCase
import com.thechance.presentation.io.ConsoleIO
import com.thechance.presentation.io.Printer
import com.thechance.presentation.ui_holder.GetItalianMealsForLargeGroupsUi
import helper.createMeal
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifySequence
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetItalianMealsForLargeGroupsUiTest{

    private lateinit var getItalianMealsForLargeGroupsUseCase: GetItalianMealsForLargeGroupsUseCase
    private lateinit var consoleIO: ConsoleIO
    private lateinit var printer: Printer
    private lateinit var getItalianMealsForLargeGroupsUi: GetItalianMealsForLargeGroupsUi

    @BeforeEach
    fun setUp() {
        getItalianMealsForLargeGroupsUseCase= mockk()
        printer= mockk(relaxed = true)
        consoleIO= mockk(relaxed = true)

        every {consoleIO.printer  } returns printer

        getItalianMealsForLargeGroupsUi= GetItalianMealsForLargeGroupsUi(getItalianMealsForLargeGroupsUseCase,consoleIO)

    }

    @Test
    fun `startUi should print all meals when meals are returned `() {
        //given
        val meal1 = createMeal(name = "Italian Pasta Feast")
        val meal2 = createMeal(name = "Massive Lasagna")
        val meals = listOf(meal1, meal2)

        every { getItalianMealsForLargeGroupsUseCase.suggestItalianMealsForLargeGroups() } returns meals


        //when
        getItalianMealsForLargeGroupsUi.startUi()

        //then
        verifySequence {
            printer.showMessage(meal1.toString())
            printer.showMessage(meal2.toString())
        }
    }


}