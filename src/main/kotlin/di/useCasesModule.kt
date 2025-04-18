package com.thechance.di

import com.thechance.logic.useCases.GetItalianMealsForLargeGroupsUseCase
import com.thechance.logic.useCases.GetSeaFoodMealsSortedByProteinContent
import com.thechance.logic.useCases.SoThinUseCase
import com.thechance.logic.useCases.GymHelperUseCase
import com.thechance.logic.useCases.MealPreptimeGuessGameUseCase
import com.thechance.logic.useCases.GetIraqiMealsUseCase
import com.thechance.logic.useCases.SuggestFoodUseCase
import com.thechance.logic.useCases.SearchByCountryName
import com.thechance.logic.useCases.GetRandomSweetWithNoEggUseCase
import com.thechance.logic.useCases.IngredientGameUseCase
import com.thechance.logic.useCases.RandomTenMealIncludePotatoUseCase
import org.koin.dsl.module
import kotlin.math.sin


val useCasesModule = module {
    single { SoThinUseCase(get()) }
    single { GetSeaFoodMealsSortedByProteinContent(get()) }
    single { GymHelperUseCase(get()) }
    single { MealPreptimeGuessGameUseCase(get()) }
    single { GetIraqiMealsUseCase(get()) }
    single { SuggestFoodUseCase(get()) }
    single { SearchByCountryName(get()) }
    single { GetRandomSweetWithNoEggUseCase(get()) }
    single { IngredientGameUseCase(get()) }
    single { RandomTenMealIncludePotatoUseCase(get()) }

    single { GetItalianMealsForLargeGroupsUseCase(get()) }
}