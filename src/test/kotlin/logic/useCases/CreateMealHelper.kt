package logic.useCases

import com.thechance.model.Meal
import com.thechance.model.NutritionFacts
import java.util.*

fun createMeal(
     name: String,
     tags: List<String>,
     description: String
) = Meal(
    name = name,
    description = description,
    tags = tags,
    id = 1,
    minutes = 12,
    contributorId = 122,
    submitted = Date(),
    nutritionFacts = NutritionFacts(
        1f,
        totalFat = 1f,
        sugar = 1f,
        sodium = 1f,
        protein = 1f,
        saturatedFat = 1f,
        carbohydrates = 1f,
    ),
    numberOfSteps = 1,
    steps = listOf("", ""),
    ingredients = listOf("", ""),
    numberOfIngredients = 4
)