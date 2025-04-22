package logic.useCases

import com.google.common.truth.Truth.assertThat
import com.thechance.logic.MealsRepository
import com.thechance.logic.useCases.MealPrepTimeGuessGameUseCase
import io.mockk.every
import io.mockk.mockk
import model.MealGameResult
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MealPrepTimeGuessGameUseCaseTest {

    private lateinit var mealsRepository: MealsRepository
    private lateinit var mealPrepTimeGuessGameUseCase: MealPrepTimeGuessGameUseCase

    @BeforeEach
    fun setUp() {
        mealsRepository = mockk(relaxed = true)
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeal(mealPrepTime = 30)
        )
        mealPrepTimeGuessGameUseCase = MealPrepTimeGuessGameUseCase(mealsRepository)
    }

    @Test
    fun `should generate a random meal, when user starts the game`() {
        // When
        val result = mealPrepTimeGuessGameUseCase.getCurrentRandomMeal()

        // Then
        assertThat(result).isNotNull()
    }

    @Test
    fun `should return WON, when user enters correct meal prep time`() {
        // When
        val guessInput = 30
        val result = mealPrepTimeGuessGameUseCase.playMealGame(guessInput)

        // Then
        assertThat(result).isEqualTo(MealGameResult.WON)
    }


    @Test
    fun `should return TOO_HIGH, when user enters meal prep time higher than actual`() {
        // When
        val guessInput = 40
        val result = mealPrepTimeGuessGameUseCase.playMealGame(guessInput)

        // Then
        assertThat(result).isEqualTo(MealGameResult.TOO_HIGH)
    }

    @Test
    fun `should return TOO_LOW, when user enters meal prep time lower than actual`() {
        // When
        val guessInput = 20
        val result = mealPrepTimeGuessGameUseCase.playMealGame(guessInput)

        // Then
        assertThat(result).isEqualTo(MealGameResult.TOO_LOW)
    }

    @Test
    fun `should return LOST, when user reaches maximum number of loses`() {
        // When
        mealPrepTimeGuessGameUseCase.playMealGame(20)
        mealPrepTimeGuessGameUseCase.playMealGame(40)

        // Incorrect guess after 3rd attempt
        val result = mealPrepTimeGuessGameUseCase.playMealGame(50)

        // Then
        assertThat(result).isEqualTo(MealGameResult.LOST)
    }
}