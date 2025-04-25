package presentation.ui_holder

import com.thechance.logic.useCases.MealPrepTimeGuessGameUseCase
import com.thechance.presentation.io.ConsoleIO
import com.thechance.presentation.ui_holder.MealPrepTimeGuessGameUi
import helper.createMeal
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifySequence
import model.MealGameResult
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MealPrepTimeGuessGameUiTest {
    private val consoleIO: ConsoleIO = mockk(relaxed = true)
    private val mealPrepTimeGuessGameUseCase: MealPrepTimeGuessGameUseCase = mockk(relaxed = true)
    private lateinit var mealPrepTimeGuessGameUi: MealPrepTimeGuessGameUi

    @BeforeEach
    fun setup() {
        mealPrepTimeGuessGameUi = MealPrepTimeGuessGameUi(mealPrepTimeGuessGameUseCase, consoleIO)
    }


    @Test
    fun `should call playMealGame function from mealPrepTimeGuessGameUseCase`() {
        //Given
        val fakeMeal = createMeal(minutes = CORRECT_MINUTES)
        every { mealPrepTimeGuessGameUseCase.getCurrentRandomMeal() } returns fakeMeal
        every { consoleIO.reader.readNumberFromUser() } returns CORRECT_MINUTES
        every { mealPrepTimeGuessGameUseCase.playMealGame(CORRECT_MINUTES) } returns MealGameResult.CORRECT


        //When
        mealPrepTimeGuessGameUi.startUi()

        //Then
        verify(exactly = 1) { mealPrepTimeGuessGameUseCase.playMealGame(CORRECT_MINUTES) }
    }

    @Test
    fun `should play the game and eventually finish when user guesses correctly`() {
        // Given
        val fakeMeal = createMeal(minutes = CORRECT_MINUTES)
        every { mealPrepTimeGuessGameUseCase.getCurrentRandomMeal() } returns fakeMeal
        every { consoleIO.reader.readNumberFromUser() } returnsMany listOf(MIN_WRONG_MINUTES, CORRECT_MINUTES)
        every { mealPrepTimeGuessGameUseCase.playMealGame(MIN_WRONG_MINUTES) } returns MealGameResult.TOO_LOW
        every { mealPrepTimeGuessGameUseCase.playMealGame(CORRECT_MINUTES) } returns MealGameResult.CORRECT

        // When
        mealPrepTimeGuessGameUi.startUi()

        // Then
        verifySequence {
            mealPrepTimeGuessGameUseCase.getCurrentRandomMeal()
            consoleIO.printer.showMessage("Guess the preparation time for: ${fakeMeal.name}")
            consoleIO.reader.readNumberFromUser()
            mealPrepTimeGuessGameUseCase.playMealGame(MIN_WRONG_MINUTES)
            consoleIO.printer.showMessage("Too low!")

            mealPrepTimeGuessGameUseCase.getCurrentRandomMeal()
            consoleIO.printer.showMessage("Guess the preparation time for: ${fakeMeal.name}")
            consoleIO.reader.readNumberFromUser()
            mealPrepTimeGuessGameUseCase.playMealGame(CORRECT_MINUTES)
            consoleIO.printer.showMessage("Correct!")
        }
    }

    @Test
    fun `should play the game and eventually finish when user loses`() {
        // Given
        val fakeMeal = createMeal(minutes = CORRECT_MINUTES)
        every { mealPrepTimeGuessGameUseCase.getCurrentRandomMeal() } returns fakeMeal
        every { consoleIO.reader.readNumberFromUser() } returnsMany listOf(
            MIN_WRONG_MINUTES,
            MAX_WRONG_MINUTES,
            MAX_WRONG_MINUTES
        )
        every { mealPrepTimeGuessGameUseCase.playMealGame(MIN_WRONG_MINUTES) } returns MealGameResult.TOO_LOW
        every { mealPrepTimeGuessGameUseCase.playMealGame(MAX_WRONG_MINUTES) } returnsMany listOf(
            MealGameResult.TOO_HIGH,
            MealGameResult.LOST
        )

        // When
        mealPrepTimeGuessGameUi.startUi()

        // Then
        verifySequence {
            mealPrepTimeGuessGameUseCase.getCurrentRandomMeal()
            consoleIO.printer.showMessage("Guess the preparation time for: ${fakeMeal.name}")
            consoleIO.reader.readNumberFromUser()
            mealPrepTimeGuessGameUseCase.playMealGame(MIN_WRONG_MINUTES)
            consoleIO.printer.showMessage("Too low!")

            mealPrepTimeGuessGameUseCase.getCurrentRandomMeal()
            consoleIO.printer.showMessage("Guess the preparation time for: ${fakeMeal.name}")
            consoleIO.reader.readNumberFromUser()
            mealPrepTimeGuessGameUseCase.playMealGame(MAX_WRONG_MINUTES)
            consoleIO.printer.showMessage("Too high!")

            mealPrepTimeGuessGameUseCase.getCurrentRandomMeal()
            consoleIO.printer.showMessage("Guess the preparation time for: ${fakeMeal.name}")
            consoleIO.reader.readNumberFromUser()
            mealPrepTimeGuessGameUseCase.playMealGame(MAX_WRONG_MINUTES)
            consoleIO.printer.showMessage("You've run out of attempts. The correct time was ${fakeMeal.minutes} minutes.")
        }
    }
    @Test
    fun `should show winning message when guess is correct`() {
        // Given
        val fakeMeal = createMeal(minutes = CORRECT_MINUTES)
        every { mealPrepTimeGuessGameUseCase.getCurrentRandomMeal() } returns fakeMeal
        every { consoleIO.reader.readNumberFromUser() } returns CORRECT_MINUTES
        every { mealPrepTimeGuessGameUseCase.playMealGame(CORRECT_MINUTES) }  returns MealGameResult.WON

        // When
        mealPrepTimeGuessGameUi.startUi()

        // Then
        verifySequence {
            mealPrepTimeGuessGameUseCase.getCurrentRandomMeal()
            consoleIO.printer.showMessage("Guess the preparation time for: ${fakeMeal.name}")
            consoleIO.reader.readNumberFromUser()
            mealPrepTimeGuessGameUseCase.playMealGame(CORRECT_MINUTES)
            consoleIO.printer.showMessage("Congratulations! You've won the game.")
        }
    }
    companion object {
        const val MIN_WRONG_MINUTES = 20
        const val MAX_WRONG_MINUTES = 40
        const val CORRECT_MINUTES = 30
    }
}