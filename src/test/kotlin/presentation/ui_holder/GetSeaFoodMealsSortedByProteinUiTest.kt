package presentation.ui_holder

import com.thechance.logic.useCases.GetSeaFoodMealsSortedByProteinContent
import com.thechance.model.NutritionFacts
import com.thechance.model.ProteinMeal
import com.thechance.presentation.io.ConsoleIO
import com.thechance.presentation.io.Printer
import com.thechance.presentation.ui_holder.GetSeaFoodMealsSortedByProteinUi
import com.thechance.presentation.ui_holder.SoThinUi
import helper.createFakeMeal
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifySequence
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetSeaFoodMealsSortedByProteinUiTest {

    private lateinit var getSeaFoodMealsSortedByProteinContent: GetSeaFoodMealsSortedByProteinContent
    private lateinit var consoleIO: ConsoleIO
    private lateinit var printer: Printer
    private lateinit var getSeaFoodMealsSortedByProteinUi: GetSeaFoodMealsSortedByProteinUi

    @BeforeEach
    fun setUp() {
        getSeaFoodMealsSortedByProteinContent= mockk()
        printer= mockk(relaxed = true)
        consoleIO= mockk(relaxed = true)

        every {consoleIO.printer  } returns printer

        getSeaFoodMealsSortedByProteinUi= GetSeaFoodMealsSortedByProteinUi(getSeaFoodMealsSortedByProteinContent,consoleIO)

    }

  @Test
    fun `startUi  print meals sorted by protein when  seafood meals are founded `() {
        val meal1 = ProteinMeal( rank = 1, name = "Grilled Salmon",35.5f)
        val meal2 = ProteinMeal(rank = 2,"Tuna Steak",30.0f)
        val meals= listOf(meal1,meal2)

        every { getSeaFoodMealsSortedByProteinContent.getSeaFoodMealsSortedByProteinContent() } returns meals

         getSeaFoodMealsSortedByProteinUi.startUi()
      verifySequence {
          printer.showMessage("Seafood Meals Sorted by Protein Content:")
          printer.showMessage("${meal1.rank}: ${meal1.name} - Protein: ${meal1.proteinAmount}g")
          printer.showMessage("${meal2.rank}: ${meal2.name} - Protein: ${meal2.proteinAmount}g")

      }

    }



    @Test
    fun `startUi when no seafood meals returned should print no meals message`() {
        every { getSeaFoodMealsSortedByProteinContent.getSeaFoodMealsSortedByProteinContent() } returns emptyList()

        getSeaFoodMealsSortedByProteinUi.startUi()

        verifySequence {
            printer.showMessage("Seafood Meals Sorted by Protein Content:")
            printer.showMessage("No seafood meals found.")
        }
    }










}