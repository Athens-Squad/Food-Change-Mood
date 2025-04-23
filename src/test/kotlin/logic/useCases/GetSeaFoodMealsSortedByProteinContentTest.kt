package logic.useCases

import com.google.common.truth.Truth.*
import com.thechance.logic.MealsRepository
import com.thechance.logic.useCases.GetSeaFoodMealsSortedByProteinContent
import com.thechance.model.Meal
import com.thechance.model.NutritionFacts
import fake.FakeMealsRepository
import fake.noSeaFoodMeals
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeAll
import java.util.*
import kotlin.test.Test

class GetSeaFoodMealsSortedByProteinContentTest {

 @Test
 fun `should return empty list when no seafood meals exist`() {
  //given
  val mealsRepository = mockk<MealsRepository>()
  every { mealsRepository.getAllMeals() } returns noSeaFoodMeals
  val getSeaFoodMealsSortedByProteinContent = GetSeaFoodMealsSortedByProteinContent(mealsRepository)

  //when
  val proteinMeals = getSeaFoodMealsSortedByProteinContent.getSeaFoodMealsSortedByProteinContent()

  //then
  assertThat(proteinMeals).isEmpty()
 }

 @Test
 fun `should ignore null meals in the repository list`() {
  //when
  val proteinMeals = getSeaFoodMealsSortedByProteinContent.getSeaFoodMealsSortedByProteinContent()

  //then
  assertThat(proteinMeals).doesNotContain(null)
 }

 @Test
 fun `should return empty list when meals list is empty`() {
  //given
  every { mealsRepository.getAllMeals() } returns emptyList()
  val getSeaFoodMealsSortedByProteinContent = GetSeaFoodMealsSortedByProteinContent(mealsRepository)

  //when
  val proteinMeals = getSeaFoodMealsSortedByProteinContent.getSeaFoodMealsSortedByProteinContent()

  //then
  assertThat(proteinMeals).isEmpty()
 }

 @Test
 fun `should return only meals tagged with seafood`() {
  //when
  val proteinMeals = getSeaFoodMealsSortedByProteinContent.getSeaFoodMealsSortedByProteinContent()

  //then
  proteinMeals.forEach { proteinMeal ->
   val meal = fakeMealsRepository.getAllMeals()
    .filterNotNull()
    .find { it.name == proteinMeal.name }

   assertThat(meal?.tags).contains("seafood")
  }
 }

 @Test
 fun `should return seafood meals sorted by protein descending`() {
  //when
  val proteinMeals = getSeaFoodMealsSortedByProteinContent.getSeaFoodMealsSortedByProteinContent()

  //then
  assertThat(proteinMeals.map { it.proteinAmount }).isInOrder(compareByDescending<Double> { it })
 }

 companion object {
  private lateinit var fakeMealsRepository: FakeMealsRepository
  private lateinit var getSeaFoodMealsSortedByProteinContent: GetSeaFoodMealsSortedByProteinContent
  val mealsRepository = mockk<MealsRepository>()
     @JvmStatic
     @BeforeAll
     fun setUp(): Unit {
      fakeMealsRepository = FakeMealsRepository()
      getSeaFoodMealsSortedByProteinContent = GetSeaFoodMealsSortedByProteinContent(fakeMealsRepository)
     }
 }
}