package com.thechance.model

import java.util.Date

data class Meal(
    val name: String,
    val id: Int,
    val minutes: Int,
    val contributorId: String,
    val submitted: Date,
    val tags: List<String>,
    val nutritionFacts: NutritionFacts,
    val numberOfSteps: Int,
    val steps: List<String>,
    val description: String,
    val ingredients: List<String>,
    val numberOfIngredients: Int
)