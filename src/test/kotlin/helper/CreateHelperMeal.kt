package helper

import com.thechance.model.Meal
import com.thechance.model.NutritionFacts
import java.time.Instant
import java.util.*

fun createMeal(
    name: String = "",
    id: Int = 0,
    minutes: Int = 0,
    contributorId: Int = 0,
    submitted: Date = Date.from(Instant.now()),
    tags: List<String> = emptyList(),
    nutritionFactCalories: Float = 0.0f,
    nutritionFactTotalFat: Float = 0.0f,
    nutritionFactSugar: Float = 0.0f,
    nutritionFactSodium: Float= 0.0f,
    nutritionFactProtein: Float = 0.0f,
    nutritionFactSaturatedFat: Float = 0.0f,
    nutritionFactCarbohydrates: Float = 0.0f,
    numberOfSteps: Int = 0,
    steps: List<String> = emptyList(),
    description: String = "",
    ingredients: List<String> = emptyList(),
    numberOfIngredients: Int = 0
) = Meal(
    name = name,
    id = id,
    minutes = minutes,
    contributorId = contributorId,
    submitted = submitted,
    tags = tags,
    nutritionFacts = NutritionFacts(
        calories = nutritionFactCalories,
        totalFat = nutritionFactTotalFat,
        sugar = nutritionFactSugar,
        sodium = nutritionFactSodium,
        protein = nutritionFactProtein,
        saturatedFat = nutritionFactSaturatedFat,
        carbohydrates = nutritionFactCarbohydrates
    ),
    numberOfSteps = numberOfSteps,
    steps = steps,
    description = description,
    ingredients = ingredients,
    numberOfIngredients = numberOfIngredients
)