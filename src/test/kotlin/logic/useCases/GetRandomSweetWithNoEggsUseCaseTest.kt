package logic.useCases

import com.google.common.truth.Truth.assertThat
import com.thechance.logic.MealsRepository
import com.thechance.logic.useCases.GetRandomSweetWithNoEggsUseCase
import helper.createMeal
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetRandomSweetWithNoEggsUseCaseTest {

    private lateinit var mealsRepository: MealsRepository
    private lateinit var getRandomSweetWithNoEggsUseCase: GetRandomSweetWithNoEggsUseCase


    @BeforeEach
    fun setUp() {
        mealsRepository = mockk(relaxed = true)
        getRandomSweetWithNoEggsUseCase = GetRandomSweetWithNoEggsUseCase(mealsRepository)
    }

    @Test
    fun `should return a meal with sweets in the name, when called`() {
        // Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeal(
                "Baghrir Moroccan sweets",
                description = "",
                ingredients = emptyList()
            ),
            createMeal(
                "Kebab meat",
                description = "",
                ingredients = emptyList()
            )
        )

        // When
        val result = getRandomSweetWithNoEggsUseCase.suggestASweetMeal()

        // Then
        assertThat(result?.name).isEqualTo("Baghrir Moroccan sweets")
    }

    @Test
    fun `should return a meal with sweets in description, when called`() {
        // Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeal(
                "Pancake",
                description = "Yummy sweets",
                ingredients = emptyList()
            ),
            createMeal(
                "Kebab meat",
                description = "",
                ingredients = emptyList()
            )
        )

        // When
        val result = getRandomSweetWithNoEggsUseCase.suggestASweetMeal()

        // Then
        assertThat(result?.name).isEqualTo("Pancake")
    }

    @Test
    fun `should return a meal contains no eggs, when called`() {
        // Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeal(
                "Cookies sweets",
                description = "",
                ingredients = listOf("flour", "eggs", "sugar")
            ),
            createMeal(
                "Sweets Cake",
                description = "",
                ingredients = listOf("flour", "milk", "sugar")
            )
        )

        // When
        val result = getRandomSweetWithNoEggsUseCase.suggestASweetMeal()

        // Then
        assertThat(result?.name).isEqualTo("Sweets Cake")
    }

    @Test
    fun `should not return previously seen meals, when called`() {
        // Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeal(
                "Cookies sweets",
                description = "",
                ingredients = emptyList()
            ),
            createMeal(
                "Kebab Meat",
                description = "",
                ingredients = emptyList()
            )
        )

        // When
        val firstResult = getRandomSweetWithNoEggsUseCase.suggestASweetMeal()
        val secondResult = getRandomSweetWithNoEggsUseCase.suggestASweetMeal()

        // Then
        assertThat(firstResult).isNotNull()
        assertThat(secondResult).isNull()
    }

    @Test
    fun `should return null, when no suitable sweet meals available`() {
        // Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeal(
                "Cookies sweets",
                description = "",
                ingredients = listOf("flour", "eggs", "sugar")
            ),
            createMeal(
                "Kebab Meat",
                description = "",
                ingredients = emptyList()
            )
        )

        // When
        val result = getRandomSweetWithNoEggsUseCase.suggestASweetMeal()

        // Then
        assertThat(result).isNull()
    }
}