package logic.useCases

import com.google.common.truth.Truth.assertThat
import com.thechance.logic.MealsRepository
import com.thechance.logic.useCases.GetHealthyMealsUseCase
import com.thechance.model.Meal
import com.thechance.model.NutritionFacts
import helper.createMeal
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import java.util.*
import kotlin.test.Test

class GetHealthyMealsUseCaseTest {


    @Test
    fun `should exclude meals with exactly average values`() {
        val repo = object : MealsRepository {
            override fun getAllMeals(): List<Meal?> {
                return listOf(
                    createMeal(),
                    createMeal()
                )
            }
        }

        val useCase = GetHealthyMealsUseCase(repo)
        val result = useCase.getHealthyFastMeals()

        // Meals with equal to average nutrients should be excluded
        assertThat(result).isEmpty()
    }

    @Test
    fun `should ignore null meals in the list`() {
        val repo = object : MealsRepository {
            override fun getAllMeals(): List<Meal?> {
                return listOf(
                    null,
                    createMeal(
                        minutes = 12,
                        nutritionFactTotalFat = 1f,
                        nutritionFactSaturatedFat = 1f,
                        nutritionFactCarbohydrates = 1f
                    ),
                    createMeal()
                )
            }
        }

        val useCase = GetHealthyMealsUseCase(repo)
        val result = useCase.getHealthyFastMeals()

        assertThat(result.size).isEqualTo(1)
    }

    @Test
    fun `should exclude meals that exceed 15 minutes even if nutrients are healthy`() {
        val repo = object : MealsRepository {
            override fun getAllMeals(): List<Meal?> {
                return listOf(
                    createMeal(minutes = 20)
                )
            }
        }

        val useCase = GetHealthyMealsUseCase(repo)
        val result = useCase.getHealthyFastMeals()

        assertThat(result).isEmpty()
    }

    @Test
    fun `should include meal with all nutrition facts below average and time under 15`() {
        val healthyMeal = createMeal(
            minutes = 10,
            nutritionFactTotalFat = 5.0f,
            nutritionFactSaturatedFat = 2.0f,
            nutritionFactCarbohydrates = 30.0f
        )
        val highMeal = createMeal(
            minutes = 20,
            nutritionFactTotalFat = 50.0f,
            nutritionFactSaturatedFat = 30.0f,
            nutritionFactCarbohydrates = 200.0f
        )

        val repository = object : MealsRepository {
            override fun getAllMeals(): List<Meal?> {
                return listOf(healthyMeal, highMeal)
            }
        }

        val useCase = GetHealthyMealsUseCase(repository)
        val result = useCase.getHealthyFastMeals()

        assertThat(result).containsExactly(healthyMeal)
    }

    @Test
    fun `should not include meals with exactly average nutrition facts`() {
        val meal1 = createMeal(
            minutes = 10,
            nutritionFactTotalFat = 10.0f,
            nutritionFactSaturatedFat = 5.0f,
            nutritionFactCarbohydrates = 60.0f
        )
        val meal2 = createMeal(
            minutes = 10,
            nutritionFactTotalFat = 10.0f,
            nutritionFactSaturatedFat = 5.0f,
            nutritionFactCarbohydrates = 60.0f
        )

        val repository = object : MealsRepository {
            override fun getAllMeals(): List<Meal?> {
                return listOf(meal1, meal2)
            }
        }

        val useCase = GetHealthyMealsUseCase(repository)
        val result = useCase.getHealthyFastMeals()

        assertThat(result).isEmpty()
    }

    @Test
    fun `should exclude healthy meals that exceed 15 minutes`() {
        val healthyButSlowMeal = createMeal(
            minutes = 20,
            nutritionFactTotalFat = 5.0f,
            nutritionFactSaturatedFat = 2.0f,
            nutritionFactCarbohydrates = 30.0f
        )

        val fastUnhealthyMeal = createMeal(
            minutes = 10,
            nutritionFactTotalFat = 50.0f,
            nutritionFactSaturatedFat = 20.0f,
            nutritionFactCarbohydrates = 100.0f
        )

        val repo = object : MealsRepository {
            override fun getAllMeals(): List<Meal?> {
                return listOf(healthyButSlowMeal, fastUnhealthyMeal)
            }
        }

        val useCase = GetHealthyMealsUseCase(repo)
        val result = useCase.getHealthyFastMeals()

        // Should exclude both: one is too slow, the other is unhealthy
        assertThat(result).isEmpty()
    }

}