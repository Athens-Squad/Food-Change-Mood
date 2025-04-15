package com.thechance.di

import com.thechance.logic.useCases.SoThinUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single { SoThinUseCase(get()) }

}