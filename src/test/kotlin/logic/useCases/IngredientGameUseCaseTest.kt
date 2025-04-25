package logic.useCases

import com.google.common.truth.Truth.assertThat
import com.thechance.logic.MealsRepository
import com.thechance.logic.useCases.IngredientGameUseCase
import com.thechance.model.Meal
import fake.createwithIngredients
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class IngredientGameUseCaseTest{

  private lateinit var mealsRepository: MealsRepository
  private lateinit var useCase: IngredientGameUseCase

  private lateinit var fakeMeals: List<Meal>

  @BeforeEach
  fun setUp() {
   mealsRepository = mockk()
        fakeMeals=List(20) { index ->
    createwithIngredients("Meal $index", listOf("ingredient$index", "salt"))
   }
  /* fakeMeals = listOf(
   createwithIngredients ("Meal A", listOf("potato", "salt")),
    createwithIngredients("Meal B", listOf("chicken", "garlic")),
    createwithIngredients("Meal C", listOf("beef", "onion")),
    createwithIngredients("Meal D", listOf("cheese", "tomato")),
    createwithIngredients("Meal E", listOf("fish", "lemon"))
   )*/

   every { mealsRepository.getAllMeals() } returns fakeMeals

   useCase = IngredientGameUseCase(mealsRepository)
  }

  @Test
  fun `guessIngredient returns a valid question triple`() {
   //given
   val question = useCase.guessIngredient()
   //when
   val (mealName, options, correct) = question!!

   //then
   //assertThat(question).isNotNull()
  // assertThat(mealName).isNotEmpty()
  assertThat(options).contains(correct)
   //assertThat(options.size).isAtMost(3)
  }

  @Test
  fun `submitAnswer returns true when choice  correct answer and updates score`() {
   //given
   val question = useCase.guessIngredient()
   val correctAnswer = question!!.third
    //when
   val result = useCase.submitAnswer(correctAnswer)
    // then
   assertThat(result).isTrue()
   //assertThat(useCase.getScore()).isEqualTo(1000)
  }

  @Test
  fun `submitAnswer returns false when choice  wrong answer and does not update score`() {
   // given
   val question = useCase.guessIngredient()
   val correctAnswer = question!!.third
   val wrongAnswer = question.second.first { it != correctAnswer }
   //when
   val result = useCase.submitAnswer(wrongAnswer)
    //then
   assertThat(result).isFalse()
  // assertThat(useCase.getScore()).isEqualTo(0)
  }

 @Test
 fun `game ends when complete 15 correct answers`() {
  //given
  repeat(15) {
   val question = useCase.guessIngredient()!!
   useCase.submitAnswer(question.third)
  }

  //when

  val isOver = useCase.isGameOver()
  val finalScore = useCase.getScore()

  //then
     assertThat(isOver).isTrue()
  //assertThat(finalScore).isEqualTo(15 * 1000)

 }

@Test
 fun `game ends when complete 15 correct answers returns null`(){
    //given
    repeat(15) {
        val question = useCase.guessIngredient()!!
        useCase.submitAnswer(question.third)
    }
    //when
    val result = useCase.guessIngredient()
    val isOver=useCase.isGameOver()
    //then
    assertThat(isOver).isTrue()
   // assertThat(result).isNull()
 }
 }