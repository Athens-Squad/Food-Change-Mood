package com.thechance.logic.useCases
import com.thechance.logic.MealsRepository
import com.thechance.model.Meal

class GetHealthyMealsUseCase(private val repository: MealsRepository) {

    fun getHealthyFastMeals(): List<Meal> {
        val allMeals = repository.getAllMeals().filterNotNull()

        // Find average or thresholds for comparison
        val avgFat = allMeals.map { it.nutritionFacts.totalFat }.average()
        val avgSaturatedFat = allMeals.map { it.nutritionFacts.saturatedFat }.average()
        val avgCarbs = allMeals.map { it.nutritionFacts.carbohydrates }.average()

        return allMeals.filter { meal ->
            meal.minutes <= 15 &&
                    meal.nutritionFacts.totalFat < avgFat &&
                    meal.nutritionFacts.saturatedFat < avgSaturatedFat &&
                    meal.nutritionFacts.carbohydrates < avgCarbs
        }
    }
}
