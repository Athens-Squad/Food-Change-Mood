package com.thechance.logic.useCases.mealSearchUseCase

import fake.FakeMealsRepository
import org.junit.Assert.assertEquals
import org.junit.Test

class MealSearchUseCaseTest {

    private val fakeRepository = FakeMealsRepository()
    private val searchStrategy = KmpSearchStrategy(fakeRepository)
    private val useCase = MealSearchUseCase(searchStrategy)


    @Test
    fun `search for full meal name should return exact match`() {
        val results = useCase.search("Salmon Salad")
        assertEquals(1, results.size)
        assertEquals("Salmon Salad", results[0].name)
    }

    @Test
    fun `search for partial match should return matching meals`() {
        val results = useCase.search("Pasta")
        assertEquals(1, results.size)
        assertEquals("Chicken Alfredo Pasta", results[0].name)
    }

    @Test
    fun `search is case sensitive and should return no match if casing doesn't match`() {
        val results = useCase.search("salmon salad") // KMP is case-sensitive by default
        assertEquals(0, results.size)
    }

    @Test
    fun `search for a substring that appears in multiple meals`() {
        val results = useCase.search("a")
        // Meals: Vegan Tacos, Salmon Salad, Banana Pancakes, Chicken Alfredo Pasta, Beef Stir Fry
        // All contain "a", so all should be returned
        assertEquals(5, results.size)
    }

    @Test
    fun `search for a term not found in any meal`() {
        val results = useCase.search("Pizza")
        assertEquals(0, results.size)
    }

}

