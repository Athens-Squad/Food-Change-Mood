package com.thechance.logic.useCases

import com.thechance.logic.MealsRepository
import com.thechance.model.Meal

class RandomTenMealIncludePotatoUseCase(val mealsRepository: MealsRepository) {

    fun suggestPotatoMeals(numberOfMeal: Int = 10): List<Meal> {
        return mealsRepository.getAllMeals()
            .filterNotNull()
            .filter { it.ingredients.any { ingredient -> ingredient.contains("potato", ignoreCase = true) } }
            .shuffled().take(numberOfMeal)




            /*.toMutableList()
            .let { meals ->
                mutableListOf<Meal>().apply {
                    val size = minOf(limit, meals.size)
                    repeat(size) {
                        meals.randomOrNull()?.also { selected ->
                            add(selected)
                            //no duplicated
                            meals.remove(selected)
                        }
                    }
                }
            }*/
    }}



