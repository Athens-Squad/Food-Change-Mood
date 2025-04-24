package helper

import com.thechance.model.Meal
import com.thechance.model.NutritionFacts
import java.util.*


fun createFakeMeal(
    name: String = "Meal",
    id: Int = 1,
    minutes: Int = 20,
    contributorId: Int = 5,
    submitted: Date = Date(),
    tags: List<String> = emptyList(),
    nutritionFacts: NutritionFacts = NutritionFacts(1f, 1f, 1f, 1f, 1f, 1f, 1f),
    numberOfSteps: Int = 6,
    steps: List<String> = listOf("step 1", "step 2"),
    description: String = "description",
    ingredients: List<String>  = listOf("ingredient 1", "ingredient 2"),
    numberOfIngredients: Int = 5

): Meal {
    return Meal(
        id = id,
        name = name,
        minutes = minutes,
        contributorId = contributorId,
        submitted = submitted,
        tags = tags,
        nutritionFacts = nutritionFacts,
        numberOfSteps = numberOfSteps,
        steps = steps,
        description = description,
        ingredients = ingredients,
        numberOfIngredients = numberOfIngredients
    )
}