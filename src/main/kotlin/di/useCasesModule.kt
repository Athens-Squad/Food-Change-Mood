package com.thechance.di

import com.thechance.logic.useCases.GetIraqiMealsUseCase
import com.thechance.logic.useCases.GetSeaFoodMealsSortedByProteinContent
import com.thechance.logic.useCases.SoThinUseCase
import com.thechance.logic.useCases.SuggestFoodUseCase
import org.koin.dsl.module
import kotlin.math.sin

val useCasesModule = module {
    single { SoThinUseCase(get()) }
    single { GetSeaFoodMealsSortedByProteinContent(get()) }
    single { GetIraqiMealsUseCase(get()) }
    single { SuggestFoodUseCase(get()) }
}