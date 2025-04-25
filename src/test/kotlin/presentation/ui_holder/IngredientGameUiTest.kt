package presentation.ui_holder

import com.thechance.logic.useCases.IngredientGameUseCase
import com.thechance.presentation.io.ConsoleIO
import com.thechance.presentation.io.Printer
import com.thechance.presentation.io.Reader
import com.thechance.presentation.ui_holder.IngredientGameUi
import io.mockk.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class IngredientGameUiTest {
    private lateinit var ingredientGameUseCase: IngredientGameUseCase
    private lateinit var consoleIO: ConsoleIO
    private lateinit var printer: Printer
    private lateinit var reader: Reader
    private lateinit var ingredientGameUi: IngredientGameUi

    @BeforeEach
    fun setUp() {
        ingredientGameUseCase = mockk()
        printer = mockk(relaxed = true)
        reader = mockk(relaxed = true)
        consoleIO = mockk(relaxed = true)

        every { consoleIO.printer } returns printer
        every { consoleIO.reader } returns reader

        ingredientGameUi = IngredientGameUi(ingredientGameUseCase, consoleIO)
    }

    @Test
    fun `startUi - valid input, within range`() {
        //given
        val options = listOf("Tomato", "Onion", "Garlic")
        val question = Triple("Pizza", options, "Tomato")


        every { ingredientGameUseCase.isGameOver() } returnsMany listOf(false, true)
        every { ingredientGameUseCase.guessIngredient() } returns question
        every { reader.readNumberFromUser() } returns 1
        every { ingredientGameUseCase.submitAnswer("Tomato") } returns true
        every { ingredientGameUseCase.getScore() } returns 1000

        //when
        ingredientGameUi.startUi()

        //then
        verify {
            printer.showMessage("The Ingredient of  Pizza")
            printer.showMessage("1. Tomato")
            printer.showMessage("2. Onion")
            printer.showMessage("3. Garlic")
            printer.showMessage("Correct!\n")
            printer.showMessage("Current Score: 1000")
            printer.showMessage("Game Over! Your final score is 1000")
        }
    }

    @Test
    fun `startUi when user choice correct answer`() {
        //given
        val options = listOf("Tomato", "Onion", "Garlic")
        val question = Triple("Pizza", options, "Tomato")

        every { ingredientGameUseCase.isGameOver() } returnsMany listOf(false, true)
        every { ingredientGameUseCase.guessIngredient() } returns question
        every { reader.readNumberFromUser() } returns 1
        every { ingredientGameUseCase.submitAnswer("Tomato") } returns true
        every { ingredientGameUseCase.getScore() } returns 1000

        //when
        ingredientGameUi.startUi()

        //then
        verify {
            printer.showMessage("Correct!\n")
        }
    }

    @Test
    fun `startUi when user choice  incorrect answer`() {
        //given
        val options = listOf("Tomato", "Onion", "Garlic")
        val question = Triple("Pizza", options, "Tomato")

        every { ingredientGameUseCase.isGameOver() } returnsMany listOf(false, true)
        every { ingredientGameUseCase.guessIngredient() } returns question
        every { reader.readNumberFromUser() } returns 2
        every { ingredientGameUseCase.submitAnswer("Onion") } returns false
        every { ingredientGameUseCase.getScore() } returns 0

        //when
        ingredientGameUi.startUi()

        //then
        verify {
            printer.showMessage("Wrong choice ! The correct answer was: Tomato\n")
        }
    }

    @Test
    fun `startUi when  invalid input, out of range`() {
        //given
        val options = listOf("Tomato", "Onion", "Garlic")
        val question = Triple("Pizza", options, "Tomato")

        every { ingredientGameUseCase.isGameOver() } returnsMany listOf(false, true)
        every { ingredientGameUseCase.guessIngredient() } returns question
        every { reader.readNumberFromUser() } returns 5
        every { ingredientGameUseCase.getScore() } returns 0

        //when
        ingredientGameUi.startUi()

         // then
        verify {
            printer.showMessage("Invalid input. Skipping question.")
            printer.showMessage("Game Over! Your final score is 0")
        }
    }


    @Test
    fun `startUi  when null input, skips question`() {
        //given
        val options = listOf("Tomato", "Onion", "Garlic")
        val question = Triple("Pizza", options, "Tomato")

        every { ingredientGameUseCase.isGameOver() } returnsMany listOf(false, true)
        every { ingredientGameUseCase.guessIngredient() } returns question
        every { reader.readStringFromUser() } returns null.toString()
        every { ingredientGameUseCase.getScore() } returns 0
          //when
        ingredientGameUi.startUi()

        //then
        verify {
            printer.showMessage("Invalid input. Skipping question.")
            printer.showMessage("Game Over! Your final score is 0")
        }
    }

    @Test
    fun `startUi when  guessIngredient returns null, game ends immediately`() {
        //given
        every { ingredientGameUseCase.isGameOver() } returns false
        every { ingredientGameUseCase.guessIngredient() } returns null
        every { ingredientGameUseCase.getScore() } returns 0

        //when
        ingredientGameUi.startUi()

        //then
        verify {
            printer.showMessage("Game Over! Your final score is 0")
        }
    }


}

