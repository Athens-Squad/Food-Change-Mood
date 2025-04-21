package logic.useCases

import com.google.common.truth.Truth.*
import com.thechance.logic.MealsRepository
import com.thechance.logic.useCases.SoThinUseCase
import com.thechance.model.Meal
import com.thechance.model.NutritionFacts
import fake.FakeMealsRepository
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.util.*

/*
1. If the meal selected with calories greater than 700.
2. If the meal selected is not null
3. If there is no meals with greater than 700 cal.
4. If meals that were previously "unliked" (added to unLikedMeals) are not selected.
*/

class SoThinUseCaseTest {

    @Test
    fun `should return meal with calories greater than 700`() {
        //given
        val fakeMealsRepository = FakeMealsRepository()
        val soThinUseCase = SoThinUseCase(fakeMealsRepository)
        //when
        val meal = soThinUseCase.getPlus700Meal()
        //then
        assertThat(meal?.nutritionFacts?.calories).isGreaterThan(700f)
    }

    @Test
    fun `should return meal that is not null if there is meals with more than 700 calories`() {
        //when
        val meal = soThinUseCase.getPlus700Meal()
        //then
        assertThat(meal).isNotNull()
    }

    @Test
    fun `should return null when no meals have greater than 700 calories`() {
        //given
        val fakeMealsRepository = object : MealsRepository {
            override fun getAllMeals(): List<Meal?> {
                return listOf(
                    Meal(
                        name = "Vegan Salad",
                        id = 103,
                        minutes = 15,
                        contributorId = 2003,
                        submitted = Date(),
                        tags = listOf("vegan", "salad", "light"),
                        nutritionFacts = NutritionFacts(
                            calories = 250.0f,  // This meal has fewer than 700 calories
                            totalFat = 5.0f,
                            sugar = 3.0f,
                            sodium = 200.0f,
                            protein = 5.0f,
                            saturatedFat = 1.0f,
                            carbohydrates = 35.0f
                        ),
                        steps = listOf("Chop vegetables", "Toss in dressing", "Serve"),
                        description = "A light and healthy vegan salad.",
                        ingredients = listOf("lettuce", "tomato", "cucumber", "olive oil"),
                        numberOfIngredients = 4,
                        numberOfSteps = 3
                    ),
                    Meal(
                        name = "Fruit Bowl",
                        id = 104,
                        minutes = 5,
                        contributorId = 2004,
                        submitted = Date(),
                        tags = listOf("fruit", "healthy", "snack"),
                        nutritionFacts = NutritionFacts(
                            calories = 150.0f,  // This meal also has fewer than 700 calories
                            totalFat = 1.0f,
                            sugar = 20.0f,
                            sodium = 0.0f,
                            protein = 1.0f,
                            saturatedFat = 0.2f,
                            carbohydrates = 35.0f
                        ),
                        steps = listOf("Cut fruits", "Combine in a bowl", "Serve"),
                        description = "A refreshing fruit bowl for a light snack.",
                        ingredients = listOf("apple", "banana", "orange", "grapes"),
                        numberOfIngredients = 4,
                        numberOfSteps = 3
                    )
                )
            }
        }
        val soThinUseCase = SoThinUseCase(fakeMealsRepository)

        //when
        val meal = soThinUseCase.getPlus700Meal()

        //then
        assertThat(meal).isNull()
    }

    @Test
    fun `should return different meal each time`() {
        //given
        val firstMeal = soThinUseCase.getPlus700Meal()
        val secondMeal = soThinUseCase.getPlus700Meal()

        //when
        val meal = soThinUseCase.getPlus700Meal()

        //then
        assertThat(meal).isNotEqualTo(firstMeal)
        assertThat(meal).isNotEqualTo(secondMeal)
    }


  companion object {
   private lateinit var soThinUseCase: SoThinUseCase
   @JvmStatic
   @BeforeAll
   fun setUp(): Unit {
    val fakeMealsRepository = FakeMealsRepository()
    soThinUseCase = SoThinUseCase(fakeMealsRepository)
   }
  }
 }