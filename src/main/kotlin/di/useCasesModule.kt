package com.thechance.di

import com.thechance.logic.useCases.GetHealthyMealsUseCase
import com.thechance.logic.useCases.GetSeaFoodMealsSortedByProteinContent
import com.thechance.logic.useCases.SoThinUseCase
import com.thechance.logic.useCases.mealSearchUseCase.MealSearchUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single { SoThinUseCase(get()) }
    single { GetSeaFoodMealsSortedByProteinContent(get()) }
    single { GetHealthyMealsUseCase(get()) }
    single { MealSearchUseCase(get()) }

}