package fake

import com.thechance.model.Meal
import com.thechance.model.NutritionFacts
import java.io.File
import java.util.*

const val validMealText = "ingredients,n_ingredients\n" +
        "arriba   baked winter squash mexican style,137739,55,47892,2005-09-16,\"['60-minutes-or-less', 'time-to-make', 'course', 'main-ingredient', 'cuisine', 'preparation', 'occasion', 'north-american', 'side-dishes', 'vegetables', 'mexican', 'easy', 'fall', 'holiday-event', 'vegetarian', 'winter', 'dietary', 'christmas', 'seasonal', 'squash']\",\"[51.5, 0.0, 13.0, 0.0, 2.0, 0.0, 4.0]\",11,\"['make a choice and proceed with recipe', 'depending on size of squash , cut into half or fourths', 'remove seeds', 'for spicy squash , drizzle olive oil or melted butter over each cut squash piece', 'season with mexican seasoning mix ii', 'for sweet squash , drizzle melted honey , butter , grated piloncillo over each cut squash piece', 'season with sweet mexican spice mix', 'bake at 350 degrees , again depending on size , for 40 minutes up to an hour , until a fork can easily pierce the skin', 'be careful not to burn the squash especially if you opt to use sugar or butter', 'if you feel more comfortable , cover the squash with aluminum foil the first half hour , give or take , of baking', 'if desired , season with salt']\",\"autumn is my favorite time of year to cook! this recipe \n" +
        "can be prepared either spicy or sweet, your choice!\n" +
        "two of my posted mexican-inspired seasoning mix recipes are offered as suggestions.\",\"['winter squash', 'mexican seasoning', 'mixed spice', 'honey', 'butter', 'olive oil', 'salt']\",7"

const val multiLinedMealText = "ingredients,n_ingredients\n" +
        "arriba   baked winter squash mexican style,137739,55,47892,2005-09-16,\"['60-minutes-or-less', 'time-to-make', 'course', 'main-ingredient', 'cuisine', 'preparation', 'occasion', 'north-american', 'side-dishes', 'vegetables', 'mexican', 'easy', 'fall', 'holiday-event', 'vegetarian', 'winter', 'dietary', 'christmas', 'seasonal', 'squash']\",\"[51.5, 0.0, 13.0, 0.0, 2.0, 0.0, 4.0]\",11,\"['make a choice and proceed with recipe', 'depending on size of squash , cut into half or fourths', 'remove seeds', 'for spicy squash , drizzle olive oil or melted butter over each cut squash piece', 'season with mexican seasoning mix ii', 'for sweet squash , drizzle melted honey , butter , grated piloncillo over each cut squash piece', 'season with sweet mexican spice mix', 'bake at 350 degrees , again depending on size , for 40 minutes up to an hour , until a fork can easily pierce the skin', 'be careful not to burn the squash especially if you opt to use sugar or butter', 'if you feel more comfortable , cover the squash with aluminum foil the first half hour , give or take , of baking', 'if desired , season with salt']\",\"autumn is my favorite time of year to cook! this recipe \n" +
        "can be prepared either spicy or sweet, your choice!\n" +
        "two of my posted mexican-inspired seasoning mix recipes\n are offered as suggestions.\",\"['winter squash', 'mexican seasoning', 'mixed spice', 'honey', 'butter', 'olive oil', 'salt']\",7"

val mealsLessThen700Calories = listOf(
    Meal(
        name = "Vegan Salad",
        id = 103,
        minutes = 15,
        contributorId = 2003,
        submitted = Date(),
        tags = listOf("vegan", "salad", "light"),
        nutritionFacts = NutritionFacts(
            calories = 250.0f,  // This meal has fewer than 700 calories
            totalFat = 5.0f,
            sugar = 3.0f,
            sodium = 200.0f,
            protein = 5.0f,
            saturatedFat = 1.0f,
            carbohydrates = 35.0f
        ),
        steps = listOf("Chop vegetables", "Toss in dressing", "Serve"),
        description = "A light and healthy vegan salad.",
        ingredients = listOf("lettuce", "tomato", "cucumber", "olive oil"),
        numberOfIngredients = 4,
        numberOfSteps = 3
    ),
    Meal(
        name = "Fruit Bowl",
        id = 104,
        minutes = 5,
        contributorId = 2004,
        submitted = Date(),
        tags = listOf("fruit", "healthy", "snack"),
        nutritionFacts = NutritionFacts(
            calories = 150.0f,  // This meal also has fewer than 700 calories
            totalFat = 1.0f,
            sugar = 20.0f,
            sodium = 0.0f,
            protein = 1.0f,
            saturatedFat = 0.2f,
            carbohydrates = 35.0f
        ),
        steps = listOf("Cut fruits", "Combine in a bowl", "Serve"),
        description = "A refreshing fruit bowl for a light snack.",
        ingredients = listOf("apple", "banana", "orange", "grapes"),
        numberOfIngredients = 4,
        numberOfSteps = 3
    )
)

fun createFileFromMealText(mealText: String): File {
    return kotlin.io.path.createTempFile().toFile().apply {
        writeText(mealText)
        deleteOnExit()
    }
}