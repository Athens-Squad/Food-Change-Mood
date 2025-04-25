package logic.useCases

import com.thechance.logic.MealsRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import com.google.common.truth.Truth.assertThat
import com.thechance.logic.useCases.SuggestEasyMealsUseCase
import helper.createMeal
import helper.createMeal

class SuggestFoodUseCaseTest {
    private lateinit var mealsRepository: MealsRepository
    private lateinit var suggestFoodUseCase: SuggestEasyMealsUseCase

    @BeforeEach
    fun setup() {
        mealsRepository = mockk(relaxed = true)
        suggestFoodUseCase = SuggestEasyMealsUseCase(mealsRepository)
    }

    @Test
    fun `should return easy meals when all meals meet the criteria`() {
        //Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeal(minutes = 18, numberOfIngredients = 5, numberOfSteps = 4),
            createMeal(minutes = 30, numberOfIngredients = 4, numberOfSteps = 6),
            createMeal(minutes = 5, numberOfIngredients = 5, numberOfSteps = 6)

        )

        //When
        val result = suggestFoodUseCase.getEasyMeals()

        //Then
        assertThat(result).hasSize(3)
    }

    @Test
    fun `should return null when meal meets the number of minutes criteria only`() {
        //Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeal(minutes = 18, numberOfIngredients = 7, numberOfSteps = 8),
            createMeal(minutes = 30, numberOfIngredients = 9, numberOfSteps = 10)

        )

        //When
        val result = suggestFoodUseCase.getEasyMeals()

        //Then
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return null when meal meet the number of ingredients criteria only`() {
        //Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeal(minutes = 40, numberOfIngredients = 5, numberOfSteps = 8),
            createMeal(minutes = 35, numberOfIngredients = 2, numberOfSteps = 10)

        )

        //When
        val result = suggestFoodUseCase.getEasyMeals()

        //Then
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return null when meal meet the number of steps criteria only`() {
        //Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeal(minutes = 40, numberOfIngredients = 7, numberOfSteps = 6),
            createMeal(minutes = 35, numberOfIngredients = 8, numberOfSteps = 5)

        )

        //When
        val result = suggestFoodUseCase.getEasyMeals()

        //Then
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return null when meal meets the number of minutes and ingredients criteria only`() {
        //Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeal(minutes = 18, numberOfIngredients = 5, numberOfSteps = 8),
            createMeal(minutes = 30, numberOfIngredients = 4, numberOfSteps = 10)

        )

        //When
        val result = suggestFoodUseCase.getEasyMeals()

        //Then
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return null when meal meets the number of minutes and steps criteria only`() {
        //Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeal(minutes = 18, numberOfIngredients = 7, numberOfSteps = 6),
            createMeal(minutes = 30, numberOfIngredients = 8, numberOfSteps = 5)

        )

        //When
        val result = suggestFoodUseCase.getEasyMeals()

        //Then
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return null when meal meets the number of ingredients and steps criteria only`() {
        //Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeal(minutes = 35, numberOfIngredients = 4, numberOfSteps = 6),
            createMeal(minutes = 37, numberOfIngredients = 5, numberOfSteps = 5)

        )

        //When
        val result = suggestFoodUseCase.getEasyMeals()

        //Then
        assertThat(result).isEmpty()
    }


    @Test
    fun `should return null when all meals have either too many minutes, ingredients, or steps`() {
        //Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeal(minutes = 40, numberOfIngredients = 7, numberOfSteps = 8),
            createMeal(minutes = 35, numberOfIngredients = 8, numberOfSteps = 9)
        )

        //When
        val result = suggestFoodUseCase.getEasyMeals()

        //Then
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return null when some meals meet the criteria, others do not`() {
        //Given
        val invalidMeal1 = createMeal(minutes = 40, numberOfIngredients = 7, numberOfSteps = 8)
        val invalidMeal2 = createMeal(minutes = 35, numberOfIngredients = 8, numberOfSteps = 9)
        val validMeal1 = createMeal(minutes = 18, numberOfIngredients = 5, numberOfSteps = 4)
        val validMeal2 = createMeal(minutes = 30, numberOfIngredients = 4, numberOfSteps = 6)

        every { mealsRepository.getAllMeals() } returns listOf(
            invalidMeal1,
            invalidMeal2,
            validMeal1,
            validMeal2
        )

        //When
        val result = suggestFoodUseCase.getEasyMeals()

        //Then
        assertThat(result).containsExactly(validMeal1, validMeal2)
    }

    @Test
    fun `should return only 10 meals when more than 10 meals meet the criteria`() {
        //Given
        val meals = List(10){ index ->
            createMeal(minutes = 10 + index, numberOfIngredients = 5, numberOfSteps = 4)

        }
        every { mealsRepository.getAllMeals() } returns meals


        //When
        val result = suggestFoodUseCase.getEasyMeals()

        //Then
        assertThat(result).hasSize(10)
    }


    @Test
    fun `should return shuffled (different) meals every time when called`() {

        //Given
        val meals = List(12){ index ->
            createMeal(minutes = 10 + index, numberOfIngredients = 5, numberOfSteps = 4)

        }
        every { mealsRepository.getAllMeals() } returns meals

        //When
        val firstResult = suggestFoodUseCase.getEasyMeals()
        val secondResult = suggestFoodUseCase.getEasyMeals()

        //Then
        assertThat(firstResult).isNotEqualTo(secondResult)
    }

    @Test
    fun `should return easy meals when criteria are on the boundary`() {
        //Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createMeal(minutes = 30, numberOfIngredients = 5, numberOfSteps = 6)
        )

        //When
        val result = suggestFoodUseCase.getEasyMeals()

        //Then
        assertThat(result).hasSize(1)
    }

    @Test
    fun `should return null when empty list of meals is given`() {
        // Given
        every { mealsRepository.getAllMeals() } returns emptyList()

        //When
        val result = suggestFoodUseCase.getEasyMeals()

        //Then
        assertThat(result).isEmpty()
    }


}