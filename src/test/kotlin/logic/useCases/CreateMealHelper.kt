package logic.useCases

import com.thechance.model.Meal
import com.thechance.model.NutritionFacts
import java.time.Instant
import java.util.Date

fun createMeal(
    mealName: String,
    mealDescription: String,
    ingredients: List<String>
) = Meal(
    name = mealName,
    id = 0,
    minutes = 0,
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
    description = mealDescription,
    ingredients = ingredients,
    numberOfIngredients = 0
)