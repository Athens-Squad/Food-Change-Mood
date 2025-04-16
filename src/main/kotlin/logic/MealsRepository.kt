package com.thechance.logic

import com.thechance.model.Meal

interface MealsRepository {
    fun getAllMeals(): List<Meal?>
}