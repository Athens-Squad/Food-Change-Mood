package com.thechance.presentation

import com.thechance.logic.IngredientGameUseCase
import com.thechance.logic.useCases.*
import model.MealGameResult

class FoodChangeMoodCLI(
    private val getHealthyMealsUseCase: GetHealthyMealsUseCase,
    private val searchByCountryName: SearchByCountryName,
    private val getIraqiMealsUseCase: GetIraqiMealsUseCase,
    private val suggestFoodUseCase: SuggestFoodUseCase,
    private val guessGameUseCase: MealPrepTimeGuessGameUseCase,
    private val getRandomSweetWithNoEggs: GetRandomSweetWithNoEggsUseCase,
    private val gymHelperUseCase: GymHelperUseCase,
    private val soThinUseCase: SoThinUseCase,
    private val getSeaFoodMealsSortedByProteinContent: GetSeaFoodMealsSortedByProteinContent,
    private val ingredientGameUseCase: IngredientGameUseCase,
    private val getRandomTenMealIncludePotatoUseCase: GetRandomTenMealIncludePotatoUseCase

) {
    fun start() {
        while (true) {
            println("Welcome to the Food Change Mood App!")
            println("Select an option:")
            println("1: List Healthy Fast Food Meals")
            println("2: Search Meal by Country")
            println("3: List Iraqi Meals")
            println("4: Suggest Easy Meals")
            println("5: Guess Meal Preparation Time")
            println("6: Suggest a Sweet with No Eggs")
            println("7: Gym Helper (Calories and Protein)")
            println("8: Suggest a Meal with More Than 700 Calories")
            println("9: List Seafood Meals Sorted by Protein Content")
            println("10: Play Ingredient Guessing Game")
            println("11: Suggest 10 Meals with Potatoes")
            println("0: Exit")

            when (readLine()?.toIntOrNull()) {
                1 -> listHealthyFastFoodMeals()
                2 -> searchMealsByCountry()
                3 -> listIraqiMeals()
                4 -> suggestEasyMeals()
                5 -> startGuessGame()
                6 -> suggestSweetWithNoEggs()
                7 -> gymHelper()
                8 -> suggestSoThinMeal()
                9 -> listSeaFoodMealsSortedByProtein()
                10 -> startIngredientGame()
                11 -> suggestPotatoMeals()
                0 -> {
                    println("Exiting the app. Goodbye!")
                    return
                }
                else -> println("Invalid option. Please choose again.")
            }
        }
    }

    private fun listHealthyFastFoodMeals() {
        val healthyMeals = getHealthyMealsUseCase.getHealthyFastMeals()
        println("Healthy Fast Food Meals (15 mins or less):")
        if (healthyMeals.isNotEmpty()) {
            healthyMeals.forEach { println(it.name) }
        } else {
            println("No healthy meals found.")
        }
    }

    private fun searchMealsByCountry() {
        println("Enter country name:")
        val country = readLine() ?: return
        val meals = searchByCountryName.getMealsByCountry(country)
        println("Meals related to $country:")
        if (meals.isNotEmpty()) {
            meals.forEach { println(it.name) }
        } else {
            println("No meals found for the country $country.")
        }
    }

    private fun listIraqiMeals() {
        val iraqiMeals = getIraqiMealsUseCase.getIraqiMeals()
        println("Iraqi Meals:")
        if (iraqiMeals.isNotEmpty()) {
            iraqiMeals.forEach { println(it?.name) }
        } else {
            println("No Iraqi meals found.")
        }
    }

    private fun suggestEasyMeals() {
        val easyMeals = suggestFoodUseCase.getMeals()
        println("Suggested Easy Meals:")
        if (easyMeals.isNotEmpty()) {
            easyMeals.forEach { println(it.name) }
        } else {
            println("No meals available.")
        }
    }

    private fun startGuessGame() {
        var result: MealGameResult
        do {
            val currentMeal = guessGameUseCase.getCurrentRandomMeal()
            println("Guess the preparation time for: ${currentMeal.name}")
            val userGuess = readLine()?.toIntOrNull() ?: return
            result = guessGameUseCase.playMealGame(userGuess)
            when (result) {
                MealGameResult.CORRECT -> println("Correct!")
                MealGameResult.TOO_LOW -> println("Too low!")
                MealGameResult.TOO_HIGH -> println("Too high!")
                MealGameResult.LOST -> println("You've run out of attempts. The correct time was ${currentMeal.minutes} minutes.")
                MealGameResult.WON -> println("Congratulations! You've won the game.")
            }
        } while (result != MealGameResult.CORRECT && result != MealGameResult.LOST)
    }

    private fun suggestSweetWithNoEggs() {
        val sweet = getRandomSweetWithNoEggs.suggestASweetMeal()
        if (sweet != null) {
            println("Suggested Sweet (No Eggs): ${sweet.name} - ${sweet.description}")
            println("Would you like to see more details for ${sweet.name}? (yes/no)")
            if (readLine()?.trim()?.equals("yes", ignoreCase = true) == true) {
                println("Details: ${sweet.description}")
            }
        } else {
            println("No more egg-free sweets available.")
        }
    }

    private fun gymHelper() {
        println("Enter desired calories:")
        val calories = readLine()?.toIntOrNull() ?: return
        println("Enter desired protein:")
        val protein = readLine()?.toIntOrNull() ?: return

        val meals = gymHelperUseCase.getMealsByCaloriesAndProtein(calories, protein)
        println("Meals matching your criteria (Calories: $calories, Protein: $protein):")
        if (meals.isNotEmpty()) {
            meals.forEach { println(it.name) }
        } else {
            println("No meals found matching those criteria.")
        }
    }

    private fun suggestSoThinMeal() {
        val meal = soThinUseCase.getPlus700Meal()
        if (true) println("Suggested Meal with more than 700 calories: ${meal.name} - Calories: ${meal.nutritionFacts.calories}") else println("No more meals with more than 700 calories available.")
    }

    private fun listSeaFoodMealsSortedByProtein() {
        val seafoodMeals =
            getSeaFoodMealsSortedByProteinContent.getSeaFoodMealsSortedByProteinContent()
        println("Seafood Meals Sorted by Protein Content:")
        if (seafoodMeals.isNotEmpty()) {
            seafoodMeals.forEach { meal ->
                println("${meal.rank}: ${meal.name} - Protein: ${meal.proteinAmount}g")
            }
        } else {
            println("No seafood meals found.")
        }
    }

    private fun startIngredientGame() {
        println("Welcome to the Ingredient Guessing Game!")
        println("Guess the correct ingredient for each meal.")

        while (!ingredientGameUseCase.isGameOver()) {
            val question = ingredientGameUseCase.guessIngredient()
            if (question == null) {
                println("Game Over!")
                break
            }

            val (mealName, options, correct) = question
            println("Which of the following is an ingredient in: $mealName")
            options.forEachIndexed { index, option ->
                println("${index + 1}: $option")
            }

            val userChoice = readLine()?.toIntOrNull()
            val selected = if (userChoice != null && userChoice in 1..options.size) {
                options[userChoice - 1]
            } else {
                println("Invalid input. Skipping question.")
                continue
            }

            if (ingredientGameUseCase.submitAnswer(selected)) {
                println("✅ Correct!")
            } else {
                println("❌ Wrong! The correct answer was: $correct")
            }

            println("Current Score: ${ingredientGameUseCase.getScore()}")
            println("------------------------------------------------")
        }

        println("🎉 Game finished! Your final score is ${ingredientGameUseCase.getScore()}")
    }
    private fun suggestPotatoMeals() {
        val meals = getRandomTenMealIncludePotatoUseCase.suggestPotatoMeals()
        println("Here are 10 meals that include potatoes:")
        if (meals.isNotEmpty()) {
            meals.forEachIndexed { index, meal ->
                println("${index + 1}: ${meal.name}")
            }
        } else {
            println("No potato meals found.")
        }
    }

}