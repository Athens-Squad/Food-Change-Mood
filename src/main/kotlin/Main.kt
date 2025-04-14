package com.thechance

import com.thechance.di.appModule
import com.thechance.di.useCasesModule
import org.koin.core.context.startKoin

fun main() {

    startKoin {
        modules(appModule, useCasesModule)
    }

}