package com.thechance.di
import com.thechance.logic.useCases.GetHealthyMealsUseCase
import com.thechance.logic.useCases.*
import com.thechance.logic.useCases.GetIraqiMealsUseCase
import com.thechance.logic.useCases.GetRandomSweetWithNoEggsUseCase

import com.thechance.logic.useCases.GetItalianMealsForLargeGroupsUseCase
import com.thechance.logic.useCases.GetSeaFoodMealsSortedByProteinContent
import com.thechance.logic.useCases.SearchByCountryName
import com.thechance.logic.useCases.SoThinUseCase
import com.thechance.logic.useCases.mealSearchUseCase.MealSearchUseCase
import com.thechance.logic.useCases.GymHelperUseCase

import com.thechance.logic.useCases.SuggestEasyMealsUseCase
import com.thechance.logic.useCases.MealPrepTimeGuessGameUseCase
import com.thechance.logic.useCases.IngredientGameUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single { MealPrepTimeGuessGameUseCase(get()) }
    single { SoThinUseCase(get()) }
    single { GetSeaFoodMealsSortedByProteinContent(get()) }
    single { GetHealthyMealsUseCase(get()) }
    single { MealSearchUseCase(get()) }
    single { GetIraqiMealsUseCase(get()) }
    single { SuggestEasyMealsUseCase(get()) }
    single { SearchByCountryName(get()) }
    single { GetRandomSweetWithNoEggsUseCase(get()) }
    single { GymHelperUseCase(get())}
    single { KetoDietMealUseCase(get()) }
    single { IngredientGameUseCase(get())  }
    single { GetRandomTenMealIncludePotatoUseCase(get()) }
    single { GetItalianMealsForLargeGroupsUseCase(get()) }
    single { SearchFoodByAddDateUseCase(get()) }

}