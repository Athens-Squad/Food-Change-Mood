package logic.useCases

import com.thechance.logic.MealsRepository
import com.thechance.logic.useCases.SearchByCountryName
import com.thechance.util.Nationalities
import com.google.common.truth.Truth.assertThat
import helper.createMealHelper
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SearchByCountryNameTest {

    private lateinit var repository: MealsRepository
    private lateinit var searchByCountryName: SearchByCountryName

    @BeforeEach
    fun setup() {
        repository = mockk(relaxed = true)
        searchByCountryName = SearchByCountryName(repository)
    }

    @Test
    fun `should return meals when country is found in tags`() {
        // Given
        val country = "Italy"
        every { repository.getAllMeals() } returns listOf(
            createMealHelper(name = "Pizza", tags = listOf("italian"), description = "Delicious cheese"),
            createMealHelper(name = "Pasta", tags = listOf("Italy", "noodles"), description = "Traditional Italian dish")
        )
        // When
        val result = searchByCountryName.getMealsByCountry(country)
        // Then
        assertThat(result).hasSize(2)
    }

    @Test
    fun `should return meals when nationality is found in meal name`() {
        // Given
        val country = "Mexico"
        val nationality = Nationalities.countryToNationality[country]
        every { repository.getAllMeals() } returns listOf(
            createMealHelper(name = "$nationality Tacos", tags = listOf("spicy"), description = "Try this street food"),
        )
        // When
        val result = searchByCountryName.getMealsByCountry(country)[0]
        // Then
        assertThat(result.name).contains(nationality)
    }

    @Test
    fun `should return meals when country is found in meal description`() {
        // Given
        val country = "Mexico"
        val lowerCaseCountry = country.lowercase()
        every { repository.getAllMeals() } returns listOf(
            createMealHelper(name = "Grilled Corn", tags = listOf("bbq"), description = "Inspired by $lowerCaseCountry flavors")
        )
        // When
        val result = searchByCountryName.getMealsByCountry(lowerCaseCountry)[0]
        // Then
        assertThat(result.description).contains(lowerCaseCountry)
    }

    @Test
    fun `should return no meals when country does not match anything`() {
        // Given
        every { repository.getAllMeals() } returns listOf(
            createMealHelper(name = "Curry", tags = listOf("Indian"), description = "Spicy and hot"),
            createMealHelper(name = "Sushi", tags = listOf("Japanese"), description = "Fresh fish with rice")
        )

        // When
        val result = searchByCountryName.getMealsByCountry("Sweden")

        // Then
        assertThat(result).isEmpty()
    }
//
//    @Test
//    fun `should return no meals when meal list is empty`() {
//        // Given
//        every { repository.getAllMeals() } returns emptyList()
//
//        // When
//        val result = searchByCountryName.getMealsByCountry("Italy")
//
//        // Then
//        assertThat(result).isEmpty()
//    }

    @Test
    fun `should return at most 20 meals`() {
        // Given
        val meals = List(25) {
            createMealHelper(name = "Dish", tags = listOf("Egypt"), description = "Inspired by Egypt")
        }
        every { repository.getAllMeals() } returns meals

        // When
        val result = searchByCountryName.getMealsByCountry("Egypt")

        // Then
        assertThat(result).hasSize(20)
    }

    @Test
    fun `should return different results due to shuffle`() {
        // Given
        val meals = List(25) {
            createMealHelper(name = "Dish ", tags = listOf("Spain"), description = "Spanish flavor")
        }
        every { repository.getAllMeals() } returns meals

        // When
        val result1 = searchByCountryName.getMealsByCountry("Spain")
        val result2 = searchByCountryName.getMealsByCountry("Spain")

        // Then
        assertThat(result1).isNotEqualTo(result2)
    }
}
