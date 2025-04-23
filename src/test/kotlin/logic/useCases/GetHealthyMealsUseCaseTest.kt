package logic.useCases

import com.google.common.truth.Truth.assertThat
import com.thechance.logic.MealsRepository
import com.thechance.logic.useCases.GetHealthyMealsUseCase
import com.thechance.model.Meal
import com.thechance.model.NutritionFacts
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import java.util.*
import kotlin.test.Test

class GetHealthyMealsUseCaseTest{


    @Test
    fun `should exclude meals with exactly average values`() {
        val repo = object : MealsRepository {
            override fun getAllMeals(): List<Meal?> {
                return listOf(
                    Meal(
                        name = "Meal 1",
                        id = 1,
                        minutes = 10,
                        contributorId = 123,
                        submitted = Date(),
                        tags = listOf("test"),
                        nutritionFacts = NutritionFacts(10f, 10f, 5f, 200f, 10f, 5f, 30f),
                        steps = listOf("Step 1"),
                        description = "",
                        ingredients = listOf("ingredient"),
                        numberOfIngredients = 1,
                        numberOfSteps = 1
                    ),
                    Meal(
                        name = "Meal 2",
                        id = 2,
                        minutes = 15,
                        contributorId = 124,
                        submitted = Date(),
                        tags = listOf("test"),
                        nutritionFacts = NutritionFacts(10f, 10f, 5f, 200f, 10f, 5f, 30f),
                        steps = listOf("Step 1"),
                        description = "",
                        ingredients = listOf("ingredient"),
                        numberOfIngredients = 1,
                        numberOfSteps = 1
                    )
                )
            }
        }

        val useCase = GetHealthyMealsUseCase(repo)
        val result = useCase.getHealthyFastMeals()

        // Meals with equal to average nutrients should be excluded
        assertThat(result).isEmpty()
    }


    companion object {
        private fun createMeal(
            id: Int = 1,
            name: String = "Test Meal",
            minutes: Int = 10,
            fat: Double = 10.0,
            satFat: Double = 5.0,
            carbs: Double = 60.0
        ): Meal {
            return Meal(
                id = id,
                name = name,
                minutes = minutes,
                contributorId = 123,
                submitted = Date(),
                tags = listOf("test"),
                nutritionFacts = NutritionFacts(
                    calories = 500f,
                    totalFat = fat.toFloat(),
                    saturatedFat = satFat.toFloat(),
                    carbohydrates = carbs.toFloat(),
                    protein = 20f,
                    sugar = 10f,
                    sodium = 300f
                ),
                ingredients = listOf("ingredient 1", "ingredient 2"),
                steps = listOf("step 1", "step 2"),
                numberOfIngredients = 2,
                numberOfSteps = 2,
                description = "Test description"
            )
        }
    }



}