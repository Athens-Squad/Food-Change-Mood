package com.thechance.logic.useCases
import com.thechance.model.Meal
import com.thechance.logic.MealsRepository

class GetItalianMealsForLargeGroupsUseCase(
    private val mealsRepository: MealsRepository
) {
    fun suggestItalianMealsForLargeGroups(): List<Meal> {
        return mealsRepository.getAllMeals()
            .filterNotNull()
            .filter { meal ->
                checkForLargeGroupsInTag(meal) &&
                        (checkItalianMealsInTag(meal) || checkItalianMealsInDescription(meal))
            }
    }

    private fun checkForLargeGroupsInTag(meal: Meal): Boolean {
        return meal.tags.toString().contains(LARGE_GROUPS, ignoreCase = true)
    }

    private fun checkItalianMealsInTag(meal: Meal): Boolean {
        return meal.tags.toString().contains(ITALIAN, ignoreCase = true)
    }

    private fun checkItalianMealsInDescription(meal: Meal): Boolean {
        return meal.description.contains(ITALIAN, ignoreCase = true)
    }

    companion object {
        const val LARGE_GROUPS = "for-large-groups"
        const val ITALIAN = "italian"
    }
}

