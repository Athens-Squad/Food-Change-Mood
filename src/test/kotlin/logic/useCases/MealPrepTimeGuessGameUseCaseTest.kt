package logic.useCases

import com.google.common.truth.Truth.assertThat
import com.thechance.logic.MealsRepository
import com.thechance.logic.useCases.MealPrepTimeGuessGameUseCase
import helper.createMeal
import io.mockk.every
import io.mockk.mockk
import model.MealGameResult
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MealPrepTimeGuessGameUseCaseTest {

    private lateinit var mealsRepository: MealsRepository
    private lateinit var mealPrepTimeGuessGameUseCase: MealPrepTimeGuessGameUseCase

    @BeforeEach
    fun setUp() {
        mealsRepository = mockk(relaxed = true)
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeal(minutes = 30)
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


    @ParameterizedTest
    @CsvSource(
        "30, WON",
        "40, TOO_HIGH",
        "20, TOO_LOW"
    )
    fun `should return the suitable game result, when user enter the guess preparation time`(
        userGuessInput: Int,
        expectedGameResult: MealGameResult
    ) {
        // When
        val result = mealPrepTimeGuessGameUseCase.playMealGame(userGuessInput)

        // Then
        assertThat(result).isEqualTo(expectedGameResult)
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