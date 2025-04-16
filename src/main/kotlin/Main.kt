package com.thechance

import com.thechance.di.appModule
import com.thechance.di.useCasesModule
import com.thechance.logic.IngredientGameUseCase
import com.thechance.logic.MealsRepository
import com.thechance.logic.useCases.GetSeaFoodMealsSortedByProteinContent
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.getKoin

fun main() {

    startKoin {
        modules(appModule, useCasesModule)
    }


    val ingredientGameUseCase= getKoin().get<IngredientGameUseCase>()

       while (!ingredientGameUseCase.isGameOver()){

           val (mealName, options)=ingredientGameUseCase.guessIngredient()?:break


           println("Meal: $mealName")
           println("Choose the correct ingredient:")
           options.forEachIndexed { index, option ->
               println("${index + 1}. $option")
           }
           print("Your choice (1-${options.size}): ")
           val userChoice = readln().toIntOrNull()
           val selected = options.getOrNull(userChoice?.minus(1) ?: -1)


           if (selected == null || !ingredientGameUseCase.submitAnswer(selected)) {
               println("Incorrect. Game over!")
               break
           } else {
               println("Correct!")
           }
           println("Final Score: ${ingredientGameUseCase.getScore()}")

       }
       println("Total score = ${ingredientGameUseCase.getScore()}")

}