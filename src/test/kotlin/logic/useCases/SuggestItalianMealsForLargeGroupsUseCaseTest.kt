package com.thechance.test.useCases
import com.google.common.truth.Truth.assertThat
import logic.MealsRepository
import logic.use_case.SuggestItalianMealsForLargeGroupsUseCase
import fake.createMeal
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.dsl.module


class SuggestItalianMealsForLargeGroupsUseCaseTest {

    private lateinit var mealsRepository: MealsRepository
    private lateinit var useCase: SuggestItalianMealsForLargeGroupsUseCase

    @BeforeEach
    fun setUp() {
        mealsRepository = mockk()
        useCase = SuggestItalianMealsForLargeGroupsUseCase(mealsRepository)
    }

    @Test
    fun `returns only Italian meals for large groups`() {
        // given
        val italianMeal = createMeal("Pasta", tags = listOf("for-large-groups", "itali"))
        val italianInDescription = createMeal("Pizza", tags = listOf("dinner"), description = "classic Itali dish")
        val unrelatedMeal = createMeal("Burger", tags = listOf("fast food"))

        every { mealsRepository.getAllMeals() } returns listOf(italianMeal, italianInDescription, unrelatedMeal)

        // when
        val result = useCase.suggestItalianMealsForLargeGroups()

        // then
        assertThat(result).containsExactlyElementsIn(listOf(italianMeal, italianInDescription))
    }

    @Test
    fun `returns empty list when no Italian meals for large groups exist`() {
        // given
        val nonMatchingMeal = createMeal("Kebab", tags = listOf("spicy", "lunch"))
        every { mealsRepository.getAllMeals() } returns listOf(nonMatchingMeal)

        // when
        val result = useCase.suggestItalianMealsForLargeGroups()

        // then
        assertThat(result).isEmpty()
    }

    @Test
    fun `ignores null meals safely`() {
        // given
        val validMeal = createMeal("Lasagna", tags = listOf("for-large-groups", "itali"))
        every { mealsRepository.getAllMeals() } returns listOf(validMeal, null, null)

        // when
        val result = useCase.suggestItalianMealsForLargeGroups()

        // then
        assertThat(result).containsExactly(validMeal)
    }
}