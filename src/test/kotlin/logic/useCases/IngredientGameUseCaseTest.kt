package logic.useCases

import com.google.common.truth.Truth.assertThat
import com.thechance.logic.MealsRepository
import com.thechance.logic.useCases.IngredientGameUseCase
import com.thechance.model.Meal
import helper.createMeal
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class IngredientGameUseCaseTest {

    private lateinit var mealsRepository: MealsRepository
    private lateinit var useCase: IngredientGameUseCase

    private lateinit var fakeMeals: List<Meal>

    @BeforeEach
    fun setUp() {
        mealsRepository = mockk()
        fakeMeals = List(20) { index ->
            createMeal("Meal $index", ingredients = listOf("ingredient$index", "salt"))
        }

        every { mealsRepository.getAllMeals() } returns fakeMeals

        useCase = IngredientGameUseCase(mealsRepository)
    }

    @Test
    fun `guessIngredient returns a valid question triple`() {
        //given
        val question = useCase.guessIngredient()

        //when
        val (_, options, correct) = question!!

        //then
        assertThat(options).contains(correct)
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

        //then
        assertThat(isOver).isTrue()
    }

    @Test
    fun `game ends when complete 15 correct answers returns null`() {
        //given
        repeat(15) {
            val question = useCase.guessIngredient()!!
            useCase.submitAnswer(question.third)
        }

        //when
        val result = useCase.guessIngredient()
        val isOver = useCase.isGameOver()

        //then
        assertThat(isOver).isTrue()
    }
}