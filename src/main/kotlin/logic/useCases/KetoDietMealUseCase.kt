package com.thechance.logic.useCases

import com.thechance.logic.MealsRepository
import com.thechance.model.Meal

class KetoDietMealUseCase(
    private val mealsRepository: MealsRepository
) {
    private val seenMeals = mutableSetOf<Meal>()
    fun suggestKetoMeal(): Meal?{

        return try {
            getKetoMeals()
                .random()
                .also { seenMeals.add(it) }
        } catch (exception:Exception){
            return null
        }
    }

    private fun getKetoMeals(): List<Meal>{
        return mealsRepository.getAllMeals()
            .filterNotNull()
            .filter (::isKeto)
            .filterNot(::isInSeenMeals)
    }

    private fun isInSeenMeals(currentMeal: Meal): Boolean {
        return currentMeal in seenMeals
    }

    private fun isKeto(currentMeal: Meal): Boolean {
        return (currentMeal.nutritionFacts.carbohydrates.toInt() in MIN_PROTEIN..MAX_PROTEIN)
                && currentMeal.nutritionFacts.totalFat > currentMeal.nutritionFacts.protein
                && currentMeal.nutritionFacts.totalFat > MIN_FATS
    }

    companion object{
        const val MIN_PROTEIN = 15
        const val MAX_PROTEIN = 20
        const val MIN_FATS = 100
    }

}