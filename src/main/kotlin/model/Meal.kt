package com.thechance.model

import java.util.Date

data class Meal(
    val id: Long,
    val name: String,
    val minutes: Int,
    val contributorId: Long,
    val submitted: Date,
    val tags: List<String>,
    val nutritionFacts: NutritionFacts,
    val stepsNumber: Int,
    val steps: List<String>,
    val description: String?,
    val ingredients: List<String>,
    val ingredientsNumber: Int
)