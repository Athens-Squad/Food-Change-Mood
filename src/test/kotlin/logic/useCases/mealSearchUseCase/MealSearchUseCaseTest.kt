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



}

