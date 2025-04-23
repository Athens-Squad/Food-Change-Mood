package logic.useCases

import com.google.common.truth.Truth.assertThat
import com.thechance.logic.MealsRepository
import com.thechance.logic.useCases.GetIraqiMealsUseCase
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class GetIraqiMealsUseCaseTest {

    private lateinit var mealsRepository: MealsRepository
    private lateinit var getIraqiMealsUseCase: GetIraqiMealsUseCase

    @BeforeEach
    fun setup() {
        mealsRepository = mockk(relaxed = true)
        getIraqiMealsUseCase = GetIraqiMealsUseCase(mealsRepository)
    }

    @ParameterizedTest
    @ValueSource(strings = ["iraq", "iraqi"])
    fun `should return iraqi meals when the country or nationality is written in description`(countryDescription: String) {
        //Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createIraqiMeal(
                name = "Pizza",
                tags = listOf("main-dish", "easy"),
                description = "A new $countryDescription meal"
            ),
        )

        //When
        val result = getIraqiMealsUseCase.getIraqiMeals()

        //Then
        assertThat(result).hasSize(1)
    }

    @Test
    fun `should return iraqi meals when the nationality is written in tags`() {
        //Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createIraqiMeal(
                name = "Pizza",
                tags = listOf("main-dish", "iraqi"),
                description = "A new meal"
            ),
        )

        //When
        val result = getIraqiMealsUseCase.getIraqiMeals()

        //Then
        assertThat(result).hasSize(1)
    }

    @Test
    fun `should return null when no iraqi meals are found`() {
        //Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createIraqiMeal(
                name = "barmia",
                description = "yummy, inexpensive main dish!",
                tags = listOf("60-minutes-or-less", "meat")
            ),
            createIraqiMeal(
                name = "coconut bread",
                description = "found this recipe on a french recipe website.",
                tags = listOf("low-protein", "cupcakes")
            ),
        )
        //When
        val result = getIraqiMealsUseCase.getIraqiMeals()

        //Then
        assertThat(result).hasSize(0)
    }

    @Test
    fun `should return iraqi meals when has both iraqi tag and iraq in description`() {
        //Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createIraqiMeal(
                name = "Pizza",
                tags = listOf("main-dish", "iraqi"),
                description = "A new iraqi meal"
            )
        )
        //When
        val result = getIraqiMealsUseCase.getIraqiMeals()

        //Then
        assertThat(result).hasSize(1)

    }

    @Test
    fun `should return only iraqi meals when multiple (mixed) meals are given`() {
        // Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createIraqiMeal(
                name = "Pizza",
                tags = listOf("main-dish", "iraqi"),
                description = "A new iraqi meal"
            ),
            createIraqiMeal(
                name = "barmia",
                description = "yummy, inexpensive main dish!",
                tags = listOf("60-minutes-or-less", "meat")
            ),
            createIraqiMeal(
                name = "coconut bread",
                description = "found this recipe on a french recipe website.",
                tags = listOf("low-protein", "cupcakes")
            ),
            createIraqiMeal(
                name = "Pizza",
                tags = listOf("main-dish", "iraqi"),
                description = "A new meal"
            )
        )

        //When
        val result = getIraqiMealsUseCase.getIraqiMeals()

        //Then
        assertThat(result).hasSize(2)
    }

    @Test
    fun `should return null when meal is tagged with (iraq) not (iraqi)`() {
        //Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createIraqiMeal(
                name = "Pizza",
                tags = listOf("main-dish", "iraq"),
                description = "A new meal"
            ),
        )

        //When
        val result = getIraqiMealsUseCase.getIraqiMeals()

        //Then
        assertThat(result).hasSize(0)
    }

    @Test
    fun `should return null when empty list of meals is given`() {
        // Given
        every { mealsRepository.getAllMeals() } returns emptyList()

        //When
        val result = getIraqiMealsUseCase.getIraqiMeals()

        //Then
        assertThat(result).hasSize(0)
    }

    @Test
    fun `should return iraqi meals when tags list is empty but has a description contains iraq`() {
        // Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createIraqiMeal(
                name = "Pizza",
                tags = emptyList(),
                description = "A new iraqi meal"
            ),
        )

        //When
        val result = getIraqiMealsUseCase.getIraqiMeals()

        //Then
        assertThat(result).hasSize(1)
    }

    @Test
    fun `should return iraqi meals when description is empty but is tagged with iraqi`() {
        // Given
        every { mealsRepository.getAllMeals() } returns listOf(
            createIraqiMeal(
                name = "Pizza",
                tags = listOf("main-dish", "iraqi"),
                description = ""
            ),
        )

        //When
        val result = getIraqiMealsUseCase.getIraqiMeals()

        //Then
        assertThat(result).hasSize(1)
    }
}