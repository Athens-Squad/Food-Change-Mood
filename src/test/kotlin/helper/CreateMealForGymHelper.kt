package helper

import com.thechance.model.Meal
import com.thechance.model.NutritionFacts
import java.util.*

fun createMealForGymHelper(
    calories: Float,
    protein: Float,
): Meal {
    return Meal(
        id = 1,
        name = "Meal 1",
        minutes = 20,
        contributorId = 1,
        submitted = Date(),
        tags = listOf("gym", "high-protein"),
        nutritionFacts = NutritionFacts(calories, 0.0f, 0.0f, 0.0f, protein, 0.0f, 0.0f),
        numberOfSteps = 5,
        steps = listOf("Step 1", "Step 2", "Step 3", "Step 4"),
        description = "A meal good for gym",
        ingredients = listOf("Ingredient 1", "Ingredient 2", "Ingredient 3", "Ingredient 4"),
        numberOfIngredients = 5
    )
}