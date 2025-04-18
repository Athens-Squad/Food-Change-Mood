package com.thechance.di

import com.thechance.logic.useCases.GetItalianMealsForLargeGroupsUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single { GetItalianMealsForLargeGroupsUseCase(get()) }
}