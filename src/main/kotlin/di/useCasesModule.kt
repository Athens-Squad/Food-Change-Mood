package com.thechance.di

import com.thechance.logic.useCases.GetRandomSweetWithNoEggsUseCase
import com.thechance.logic.useCases.GetSeaFoodMealsSortedByProteinContent
import com.thechance.logic.useCases.SearchByCountryName
import com.thechance.logic.useCases.SoThinUseCase
import com.thechance.logic.useCases.MealPrepTimeGuessGameUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single { MealPrepTimeGuessGameUseCase(get()) }
    single { SoThinUseCase(get()) }
    single { GetSeaFoodMealsSortedByProteinContent(get()) }
    single { SearchByCountryName(get()) }
    single { GetRandomSweetWithNoEggsUseCase(get()) }

}