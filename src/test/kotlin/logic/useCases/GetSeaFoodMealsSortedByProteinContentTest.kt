package logic.useCases

import com.google.common.truth.Truth.*
import com.thechance.logic.useCases.GetSeaFoodMealsSortedByProteinContent
import com.thechance.model.Meal
import com.thechance.model.NutritionFacts
import fake.FakeMealsRepository
import org.junit.jupiter.api.BeforeAll
import java.util.*
import kotlin.test.Test

class GetSeaFoodMealsSortedByProteinContentTest {

 @Test
 fun `should return empty list when no seafood meals exist`() {
  //given
  val fakeMealsRepository = object : FakeMealsRepository() {
   override fun getAllMeals(): List<Meal?> {
    return listOf(
     Meal(
      name = "Vegan Tacos",
      id = 101,
      minutes = 30,
      contributorId = 2001,
      submitted = Date(),
      tags = listOf("vegan", "easy", "quick"),
      nutritionFacts = NutritionFacts(
       calories = 750.0f,
       totalFat = 5.0f,
       sugar = 2.0f,
       sodium = 350.0f,
       protein = 3.0f,
       saturatedFat = 0.5f,
       carbohydrates = 22.0f
      ),
      steps = listOf("Warm tortillas", "Cook vegetables", "Assemble tacos"),
      description = "Quick and easy vegan tacos with sautéed vegetables.",
      ingredients = listOf("corn tortillas", "bell peppers", "onion", "avocado"),
      numberOfIngredients = 4,
      numberOfSteps = 3
     ),
     Meal(
      name = "Chicken Alfredo Pasta",
      id = 102,
      minutes = 45,
      contributorId = 2002,
      submitted = Date(),
      tags = listOf("pasta", "chicken", "creamy"),
      nutritionFacts = NutritionFacts(
       calories = 800.0f,
       totalFat = 30.0f,
       sugar = 3.0f,
       sodium = 800.0f,
       protein = 25.0f,
       saturatedFat = 15.0f,
       carbohydrates = 60.0f
      ),
      steps = listOf("Cook pasta", "Prepare Alfredo sauce", "Cook chicken", "Combine everything"),
      description = "Creamy chicken Alfredo pasta, perfect for a hearty dinner.",
      ingredients = listOf("penne pasta", "chicken breast", "cream", "parmesan cheese"),
      numberOfIngredients = 4,
      numberOfSteps = 4
     )
    )
   }

  }
  val getSeaFoodMealsSortedByProteinContent = GetSeaFoodMealsSortedByProteinContent(fakeMealsRepository)

  //when
  val proteinMeals = getSeaFoodMealsSortedByProteinContent.getSeaFoodMealsSortedByProteinContent()

  //then
  assertThat(proteinMeals).isEmpty()
 }

 @Test
 fun `should ignore null meals in the repository list`() {
  //given
  val fakeMealsRepository = object : FakeMealsRepository() {
   override fun getAllMeals(): List<Meal?> {
    return listOf(
     null,
     Meal(
      name = "Vegan Tacos",
      id = 101,
      minutes = 30,
      contributorId = 2001,
      submitted = Date(),
      tags = listOf("vegan", "easy", "quick"),
      nutritionFacts = NutritionFacts(
       calories = 750.0f,
       totalFat = 5.0f,
       sugar = 2.0f,
       sodium = 350.0f,
       protein = 3.0f,
       saturatedFat = 0.5f,
       carbohydrates = 22.0f
      ),
      steps = listOf("Warm tortillas", "Cook vegetables", "Assemble tacos"),
      description = "Quick and easy vegan tacos with sautéed vegetables.",
      ingredients = listOf("corn tortillas", "bell peppers", "onion", "avocado"),
      numberOfIngredients = 4,
      numberOfSteps = 3
     ),
     Meal(
      name = "Chicken Alfredo Pasta",
      id = 102,
      minutes = 45,
      contributorId = 2002,
      submitted = Date(),
      tags = listOf("pasta", "chicken", "creamy"),
      nutritionFacts = NutritionFacts(
       calories = 800.0f,
       totalFat = 30.0f,
       sugar = 3.0f,
       sodium = 800.0f,
       protein = 25.0f,
       saturatedFat = 15.0f,
       carbohydrates = 60.0f
      ),
      steps = listOf("Cook pasta", "Prepare Alfredo sauce", "Cook chicken", "Combine everything"),
      description = "Creamy chicken Alfredo pasta, perfect for a hearty dinner.",
      ingredients = listOf("penne pasta", "chicken breast", "cream", "parmesan cheese"),
      numberOfIngredients = 4,
      numberOfSteps = 4
     )
    )
   }

  }
  val getSeaFoodMealsSortedByProteinContent = GetSeaFoodMealsSortedByProteinContent(fakeMealsRepository)

  //when
  val proteinMeals = getSeaFoodMealsSortedByProteinContent.getSeaFoodMealsSortedByProteinContent()

  //then
  assertThat(proteinMeals).doesNotContain(null)
 }

 @Test
 fun `should return empty list when meals list is empty`() {
  //given
  val fakeMealsRepository = object : FakeMealsRepository() {
   override fun getAllMeals(): List<Meal?> {
    return emptyList()
   }

  }
  val getSeaFoodMealsSortedByProteinContent = GetSeaFoodMealsSortedByProteinContent(fakeMealsRepository)

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
     @JvmStatic
     @BeforeAll
     fun setUp(): Unit {
      fakeMealsRepository = FakeMealsRepository()
      getSeaFoodMealsSortedByProteinContent = GetSeaFoodMealsSortedByProteinContent(fakeMealsRepository)
     }
 }
}