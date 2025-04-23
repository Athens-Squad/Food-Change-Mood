package logic.useCases

import com.google.common.truth.Truth.*
import com.thechance.logic.MealsRepository
import com.thechance.logic.useCases.SoThinUseCase
import fake.FakeMealsRepository
import fake.mealsLessThen700Calories
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

/*
1. If the meal selected with calories greater than 700.
2. If the meal selected is not null
3. If there is no meals with greater than 700 cal.
4. If meals that were previously "unliked" (added to unLikedMeals) are not selected.
*/

class SoThinUseCaseTest {

    @Test
    fun `should return meal with calories greater than 700`() {
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
        val mealsRepository = mockk<MealsRepository>()
        every { mealsRepository.getAllMeals() } returns mealsLessThen700Calories

        val soThinUseCase = SoThinUseCase(mealsRepository)

        //when
        val meal = soThinUseCase.getPlus700Meal()

        //then
        assertThat(meal).isNull()
    }

    @Test
    fun `should return different meal each time`() {
        //given
        val firstMeal = soThinUseCase.getPlus700Meal()

        //when
        val meal = soThinUseCase.getPlus700Meal()

        //then
        assertThat(meal).isNotEqualTo(firstMeal)
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