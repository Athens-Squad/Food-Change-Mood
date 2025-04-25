package logic.useCases

import com.thechance.logic.MealsRepository
import com.thechance.logic.useCases.GymHelperUseCase
import com.google.common.truth.Truth.assertThat
import helper.createMeal
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GymHelperUseCaseTest {

    private lateinit var mealsRepository: MealsRepository
    private lateinit var gymHelperUseCase: GymHelperUseCase

    @BeforeEach
    fun setup() {
        mealsRepository = mockk(relaxed = true)
        gymHelperUseCase = GymHelperUseCase(mealsRepository)
    }

    @Test
    fun `should return meals matching exact calories and protein`() {
        // Given
        val matchingMeal = createMeal(nutritionFactCalories = 400f, nutritionFactProtein = 30f)
        every { mealsRepository.getAllMeals() } returns listOf(
            matchingMeal,
            createMeal(nutritionFactCalories = 500f, nutritionFactProtein = 30f),
            createMeal(nutritionFactCalories = 400f, nutritionFactProtein = 20f),
        )

        // When
        val result = gymHelperUseCase.getMealsByCaloriesAndProtein(calories = 400, protein = 30)

        // Then
        assertThat(result).containsExactly(matchingMeal)
    }

    @Test
    fun `should return empty list when no meals match the criteria`() {
        // Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeal(nutritionFactCalories = 200f, nutritionFactProtein = 10f),
            createMeal(nutritionFactCalories = 350f, nutritionFactProtein = 25f),
        )

        // When
        val result = gymHelperUseCase.getMealsByCaloriesAndProtein(calories = 400, protein = 30)

        // Then
        assertThat(result).isEmpty()
    }
}
