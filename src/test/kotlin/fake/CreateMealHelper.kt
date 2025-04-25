package fake

import com.thechance.model.Meal
import com.thechance.model.NutritionFacts
import java.util.*

fun createwithIngredients(name: String, ingredients: List<String>): Meal {
    return Meal(
        name = name,

        id = name.hashCode(),
        minutes = 30,
        contributorId = 1,
        submitted = Date(),
        tags = listOf("tag1"),
        nutritionFacts =
            NutritionFacts(
                100.0f, 5f, 20f, 10f,
                protein = 0f,
                saturatedFat = 0f,
                carbohydrates = 0f
            ),
        numberOfSteps = 2,
        steps = listOf("step1", "step2"),
        description = "Delicious $name",
        ingredients = ingredients,
        numberOfIngredients = ingredients.size
    )
}
