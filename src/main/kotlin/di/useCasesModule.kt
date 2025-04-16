package com.thechance.di

import com.thechance.logic.useCases.*
import org.koin.dsl.module

val useCasesModule = module {
    single { MealPrepTimeGuessGameUseCase(get()) }
    single { SoThinUseCase(get()) }
    single { GetSeaFoodMealsSortedByProteinContent(get()) }
    single { SearchByCountryName(get()) }
    single { GetRandomSweetWithNoEggsUseCase(get()) }
    single { GymHelperUseCase(get())}

}