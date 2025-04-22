package logic.useCases

import com.google.common.truth.Truth.assertThat
import com.thechance.logic.MealsRepository
import com.thechance.logic.useCases.GetRandomTenMealIncludePotatoUseCase
import fake.createMeal
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetRandomTenMealIncludePotatoUseCaseTest{
  private lateinit var mealsRepository: MealsRepository
  private lateinit var useCase: GetRandomTenMealIncludePotatoUseCase

  @BeforeEach
  fun setUp(){
    mealsRepository=mockk()
    useCase=GetRandomTenMealIncludePotatoUseCase(mealsRepository)
  }

 @Test
 fun `return only meals that include potato`(){
  // given
  val mealWithPotato = createMeal("Mashed Potato", listOf("potato", "butter"))
  val mealWithoutPotato = createMeal("Chicken Salad", listOf("chicken", "lettuce"))
  val mealWithSweetPotato = createMeal("Sweet Potato Pie", listOf("sweet potato", "sugar"))
  val meals= listOf(mealWithPotato,mealWithoutPotato,mealWithSweetPotato)

  every { mealsRepository.getAllMeals() } returns meals

  // when
  val result = useCase.suggestPotatoMeals()
  //then
   assertThat(result).containsExactlyElementsIn(listOf(mealWithPotato,mealWithSweetPotato))


 }


    @Test
    fun `returns exactly  10 meals `() {
        // given
        val potatoMeals = (1..20).map {
            createMeal("Meal $it", listOf("potato", "salt"))
        }

        every { mealsRepository.getAllMeals() } returns potatoMeals

        // when
        val result = useCase.suggestPotatoMeals()

        // then
        assertThat(result.size).isAtMost(10)
        result.forEach {
            assertThat(it.ingredients.any { ing -> ing.contains("potato", ignoreCase = true) }).isTrue()
        }
    }
    @Test
    fun `returns empty list when no meals contain potato`() {
        //given
        val meals = listOf(
            createMeal("Rice", listOf("rice", "salt")),
            createMeal("Eggs", listOf("eggs", "milk"))
        )
        every { mealsRepository.getAllMeals() } returns meals
        //when
        val result = useCase.suggestPotatoMeals()
         // then
        assertThat(result).isEmpty()
    }

    @Test
    fun `handles Null Meals when  Meal List has an Random Null Meal`() {
        //given
        val meal = createMeal("Potato Soup", listOf("potato", "cream"))
        every { mealsRepository.getAllMeals() } returns listOf(meal, null, null)
       //when
        val result = useCase.suggestPotatoMeals()
        //then
        assertThat(result).containsExactly(meal)
    }





 }