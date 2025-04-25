package logic.useCases

import com.thechance.logic.MealsRepository
import com.thechance.logic.useCases.GymHelperUseCase
import com.google.common.truth.Truth.assertThat
import helper.createMealForGymHelper
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
        val matchingMeal = createMealForGymHelper(calories = 400f, protein = 30f)
        every { mealsRepository.getAllMeals() } returns listOf(
            matchingMeal,
            createMealForGymHelper(calories = 500f, protein = 30f),
            createMealForGymHelper(calories = 400f, protein = 20f)
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
            createMealForGymHelper(calories = 200f, protein = 10f),
            createMealForGymHelper(calories = 350f, protein = 25f)
        )

        // When
        val result = gymHelperUseCase.getMealsByCaloriesAndProtein(calories = 400, protein = 30)

        // Then
        assertThat(result).isEmpty()
    }
}
