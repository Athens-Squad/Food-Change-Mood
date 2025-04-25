package com.thechance.test.fake
import model.Meal
import model.NutritionFacts

fun createMeal(
    name: String = "Meal",
    tags: List<String> = listOf(),
    description: String? = "",
    ingredients: List<String> = listOf("ingredient"),
): Meal {
    return Meal(
        id = 1,
        name = name,
        description = description,
        tags = tags,
        steps = listOf("step1"),
        nutritionFacts = NutritionFacts(100, 10, 5, 300, 20, 3, 50),
        numberOfIngredients = ingredients.size,
        numberOfSteps = 1,
        ingredients = ingredients,
        submitted = "2023-01-01",
        contributorId = 1,
        minutes = 20
    )
}