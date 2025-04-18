data class KetoMeal(
    val mood: String,
    val name: String,
    val ingredients: List<String>,
    val calories: Int,
    val protein: Int,
    val carbs: Int,
    val fat: Int,
    val recipe: String
)

fun printFormattedMeal(meal: KetoMeal) {
    println("\n======== Keto Meal Recommendation ========")
    println("Mood        : ${meal.mood}")
    println("Meal Name   : ${meal.name}")
    println("------------------------------------------")
    println("Nutritional Facts:")
    println("- Calories : ${meal.calories} kcal")
    println("- Protein  : ${meal.protein} g")
    println("- Carbs    : ${meal.carbs} g")
    println("- Fat      : ${meal.fat} g")
    println("------------------------------------------")
    println("Ingredients:")
    meal.ingredients.forEach { println("- $it") }
    println("------------------------------------------")
    println("Recipe Instructions:")
    println(meal.recipe)
    println("==========================================")
}

fun searchMealsByMood(meals: List<KetoMeal>, mood: String): KetoMeal? {
    return meals.find { it.mood.equals(mood, ignoreCase = true) }
}

fun searchMealsByAny(meals: List<KetoMeal>, query: String): List<KetoMeal> {
    return meals.filter { meal ->
        meal.name.contains(query, ignoreCase = true) ||
                meal.mood.equals(query, ignoreCase = true) ||
                meal.ingredients.any { it.contains(query, ignoreCase = true) }
    }
}

fun main() {
    val ketoMeals = listOf(
        KetoMeal(
            mood = "happy",
            name = "Keto Pizza",
            ingredients = listOf("Cauliflower", "Egg", "Mozzarella", "Tomato Sauce"),
            calories = 400,
            protein = 25,
            carbs = 8,
            fat = 30,
            recipe = "Steam cauliflower, mix with egg and mozzarella, shape it, add sauce, and bake."
        ),
        KetoMeal(
            mood = "sad",
            name = "Creamy Chicken Soup",
            ingredients = listOf("Chicken", "Cream", "Broth", "Onion", "Garlic"),
            calories = 350,
            protein = 30,
            carbs = 6,
            fat = 28,
            recipe = "Cook chicken, add cream and broth with onion and garlic, simmer for 20 minutes."
        ),
        KetoMeal(
            mood = "stressed",
            name = "Tuna Avocado Salad",
            ingredients = listOf("Tuna", "Avocado", "Cucumber", "Lettuce"),
            calories = 300,
            protein = 22,
            carbs = 5,
            fat = 25,
            recipe = "Mix all ingredients in a bowl and serve cold."
        ),
        KetoMeal(
            mood = "tired",
            name = "Veggie Omelet",
            ingredients = listOf("Eggs", "Peppers", "Onions", "Cheese"),
            calories = 280,
            protein = 20,
            carbs = 4,
            fat = 22,
            recipe = "Sauté veggies, add eggs and cheese, and cook until done."
        )
    )

    println("Choose an option:")
    println("1. Search by mood")
    println("2. General search (name / mood / ingredient)")
    print("Enter option (1 or 2): ")
    val option = readlnOrNull()?.trim()

    when (option) {
        "1" -> {
            print("Enter your mood (happy / sad / stressed / tired): ")
            val moodInput = readlnOrNull()?.trim()
            val result = moodInput?.let { searchMealsByMood(ketoMeals, it) }

            if (result != null) {
                printFormattedMeal(result)
            } else {
                println("No meal found for that mood.")
            }
        }

        "2" -> {
            print("Enter any keyword to search: ")
            val keyword = readlnOrNull()?.trim()
            val results = keyword?.let { searchMealsByAny(ketoMeals, it) } ?: emptyList()

            if (results.isNotEmpty()) {
                println("\nFound ${results.size} matching meal(s):")
                results.forEach { printFormattedMeal(it) }
            } else {
                println("No meals matched your search.")
            }
        }else -> {
        println("Invalid option. Please enter 1 or 2.")
    }
    }
}