package helper
import com.thechance.model.Meal
import com.thechance.model.NutritionFacts
import java.util.*

fun createMeal(
    name: String = "Meal",
    tags: List<String> = listOf(),
    description: String? = "",
    ingredients: List<String> = listOf("ingredient"),
): Meal {
    return Meal(
        id = 1,
        name = name,
        description = description.toString(),
        tags = tags,
        steps = listOf("step1"),
        nutritionFacts = NutritionFacts(100f, 10f, 5f, 300f, 20f, 3f, 50f),
        numberOfIngredients = ingredients.size,
        numberOfSteps = 1,
        ingredients = ingredients,
        submitted = Date(),
        contributorId = 1,
        minutes = 20
    )
}