package fake

import com.thechance.logic.MealsRepository
import com.thechance.model.Meal
import com.thechance.model.NutritionFacts
import java.text.SimpleDateFormat

open class FakeMealsRepository: MealsRepository {
    override fun getAllMeals(): List<Meal?> {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return listOf(
            Meal(
                name = "Vegan Tacos",
                id = 101,
                minutes = 30,
                contributorId = 2001,
                submitted = sdf.parse("2023-06-10"),
                tags = listOf("vegan", "easy", "quick"),
                nutritionFacts = NutritionFacts(
                    calories = 750.0f,
                    totalFat = 5.0f,
                    sugar = 2.0f,
                    sodium = 350.0f,
                    protein = 3.0f,
                    saturatedFat = 0.5f,
                    carbohydrates = 22.0f
                ),
                steps = listOf("Warm tortillas", "Cook vegetables", "Assemble tacos"),
                description = "Quick and easy vegan tacos with sautéed vegetables.",
                ingredients = listOf("corn tortillas", "bell peppers", "onion", "avocado"),
                numberOfIngredients = 4,
                numberOfSteps = 3
            ),
            Meal(
                name = "Chicken Alfredo Pasta",
                id = 102,
                minutes = 45,
                contributorId = 2002,
                submitted = sdf.parse("2022-11-15"),
                tags = listOf("pasta", "chicken", "creamy"),
                nutritionFacts = NutritionFacts(
                    calories = 800.0f,
                    totalFat = 30.0f,
                    sugar = 3.0f,
                    sodium = 800.0f,
                    protein = 25.0f,
                    saturatedFat = 15.0f,
                    carbohydrates = 60.0f
                ),
                steps = listOf("Cook pasta", "Prepare Alfredo sauce", "Cook chicken", "Combine everything"),
                description = "Creamy chicken Alfredo pasta, perfect for a hearty dinner.",
                ingredients = listOf("penne pasta", "chicken breast", "cream", "parmesan cheese"),
                numberOfIngredients = 4,
                numberOfSteps = 4
            ),
            Meal(
                name = "Salmon Salad",
                id = 103,
                minutes = 20,
                contributorId = 2003,
                submitted = sdf.parse("2021-08-30"),
                tags = listOf("healthy", "fish", "salad", "seafood"),
                nutritionFacts = NutritionFacts(
                    calories = 2050.0f,
                    totalFat = 20.0f,
                    sugar = 4.0f,
                    sodium = 300.0f,
                    protein = 25.0f,
                    saturatedFat = 3.0f,
                    carbohydrates = 10.0f
                ),
                steps = listOf("Grill salmon", "Prepare salad base", "Toss salad with dressing"),
                description = "A fresh and healthy salmon salad with a tangy vinaigrette.",
                ingredients = listOf("salmon", "mixed greens", "tomatoes", "cucumber", "olive oil"),
                numberOfIngredients = 5,
                numberOfSteps = 3
            ),
            Meal(
                name = "Beef Stir Fry",
                id = 104,
                minutes = 25,
                contributorId = 2004,
                submitted = sdf.parse("2024-03-12"),
                tags = listOf("stir fry", "beef", "asian"),
                nutritionFacts = NutritionFacts(
                    calories = 450.0f,
                    totalFat = 20.0f,
                    sugar = 5.0f,
                    sodium = 900.0f,
                    protein = 30.0f,
                    saturatedFat = 7.0f,
                    carbohydrates = 30.0f
                ),
                steps = listOf("Cook beef", "Stir fry vegetables", "Combine beef and veggies"),
                description = "Quick stir fry with tender beef and colorful vegetables.",
                ingredients = listOf("beef strips", "broccoli", "carrot", "soy sauce", "ginger"),
                numberOfIngredients = 5,
                numberOfSteps = 3
            ),
            Meal(
                name = "Banana Pancakes",
                id = 105,
                minutes = 20,
                contributorId = 2005,
                submitted = sdf.parse("2023-02-01"),
                tags = listOf("breakfast", "pancakes", "sweet"),
                nutritionFacts = NutritionFacts(
                    calories = 250.0f,
                    totalFat = 8.0f,
                    sugar = 12.0f,
                    sodium = 250.0f,
                    protein = 5.0f,
                    saturatedFat = 2.0f,
                    carbohydrates = 40.0f
                ),
                steps = listOf("Mash bananas", "Prepare pancake batter", "Cook pancakes", "Serve with syrup"),
                description = "Fluffy banana pancakes for a delicious breakfast.",
                ingredients = listOf("bananas", "flour", "milk", "eggs", "maple syrup"),
                numberOfIngredients = 5,
                numberOfSteps = 4
            ),
            Meal(
                name = "Grilled Shrimp",
                id = 106,
                minutes = 25,
                contributorId = 2006,
                submitted = sdf.parse("2023-05-05"),
                tags = listOf("seafood", "shrimp", "grill"),
                nutritionFacts = NutritionFacts(
                    calories = 300.0f,
                    totalFat = 6.0f,
                    sugar = 0.0f,
                    sodium = 400.0f,
                    protein = 28.0f,
                    saturatedFat = 1.0f,
                    carbohydrates = 5.0f
                ),
                steps = listOf("Marinate shrimp", "Grill shrimp", "Serve with sauce"),
                description = "Juicy grilled shrimp with a smoky flavor.",
                ingredients = listOf("shrimp", "olive oil", "garlic", "lemon juice"),
                numberOfIngredients = 4,
                numberOfSteps = 3
            ),
            Meal(
                name = "Seafood Paella",
                id = 107,
                minutes = 50,
                contributorId = 2007,
                submitted = sdf.parse("2023-07-20"),
                tags = listOf("seafood", "rice", "paella"),
                nutritionFacts = NutritionFacts(
                    calories = 700.0f,
                    totalFat = 18.0f,
                    sugar = 2.0f,
                    sodium = 600.0f,
                    protein = 35.0f,
                    saturatedFat = 5.0f,
                    carbohydrates = 60.0f
                ),
                steps = listOf("Cook rice", "Add seafood", "Simmer everything"),
                description = "Classic Spanish paella packed with seafood and spices.",
                ingredients = listOf("rice", "shrimp", "mussels", "peas", "saffron"),
                numberOfIngredients = 5,
                numberOfSteps = 3
            )
        )
    }
}