package helper

import com.thechance.model.Meal
import com.thechance.model.NutritionFacts
import java.util.*
import kotlin.random.Random

fun createMealHelper(
    name: String = "Meal",
    tags: List<String> = emptyList(),
    description: String = "description"
): Meal {
    return Meal(
        id = Random.nextInt(1, Int.MAX_VALUE),
        name = name,
        minutes = 20,
        contributorId = 1,
        submitted = Date(),
        tags = tags,
        nutritionFacts = NutritionFacts(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f), // mock basic values
        numberOfSteps = 5,
        steps = listOf("Step 1", "Step 2", "Step 3", "Step 4"),
        description = description,
        ingredients = listOf("Ingredient 1", "Ingredient 2", "Ingredient 3", "Ingredient 4"),
        numberOfIngredients = 5
    )
}