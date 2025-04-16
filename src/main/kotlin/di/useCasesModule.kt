package com.thechance.di

import com.thechance.logic.useCases.*
import com.thechance.logic.useCases.GetIraqiMealsUseCase
import com.thechance.logic.useCases.GetRandomSweetWithNoEggsUseCase
import com.thechance.logic.useCases.GetSeaFoodMealsSortedByProteinContent
import com.thechance.logic.useCases.SearchByCountryName
import com.thechance.logic.useCases.SoThinUseCase
import com.thechance.logic.useCases.SuggestFoodUseCase
import com.thechance.logic.useCases.MealPrepTimeGuessGameUseCase
import org.koin.dsl.module
import kotlin.math.sin

val useCasesModule = module {
    single { MealPrepTimeGuessGameUseCase(get()) }
    single { SoThinUseCase(get()) }
    single { GetSeaFoodMealsSortedByProteinContent(get()) }
    single { GetIraqiMealsUseCase(get()) }
    single { SuggestFoodUseCase(get()) }
    single { SearchByCountryName(get()) }
    single { GetRandomSweetWithNoEggsUseCase(get()) }
    single { GymHelperUseCase(get())}

}