package logic.useCases

import com.thechance.model.Meal
import com.thechance.model.NutritionFacts
import java.time.Instant
import java.util.Date

fun createMeal(
    mealPrepTime: Int
) = Meal(
    name = "",
    id = 0,
    minutes = mealPrepTime,
    contributorId = 0,
    submitted = Date.from(Instant.now()),
    tags = emptyList(),
    nutritionFacts = NutritionFacts(
        calories = 0.0f,
        totalFat = 0.0f,
        sugar = 0.0f,
        sodium = 0.0f,
        protein = 0.0f,
        saturatedFat = 0.0f,
        carbohydrates = 0.0f
    ),
    numberOfSteps = 0,
    steps = emptyList(),
    description = "Meal Description",
    ingredients = emptyList(),
    numberOfIngredients = 0
)