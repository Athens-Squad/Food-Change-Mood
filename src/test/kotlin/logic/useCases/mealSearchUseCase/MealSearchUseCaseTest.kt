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



}

