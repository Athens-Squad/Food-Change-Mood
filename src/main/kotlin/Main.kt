package com.thechance

import com.thechance.di.appModule
import com.thechance.di.useCasesModule
import com.thechance.logic.useCases.SuggestItalianMealsForLargeGroupsUseCase
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.getKoin

fun main() {
    startKoin {
        modules(appModule, useCasesModule)
    }

    val instance: SuggestItalianMealsForLargeGroupsUseCase = getKoin().get()

    instance.suggestItalianMealsForLargeGroups().forEachIndexed { index, meal ->
        println("${index + 1}. ${meal.name}")
    }
}