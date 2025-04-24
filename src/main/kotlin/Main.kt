package com.thechance

import com.thechance.di.appModule
import com.thechance.di.presentationModule
import com.thechance.di.useCasesModule
import com.thechance.logic.useCases.*
import com.thechance.logic.useCases.mealSearchUseCase.MealSearchUseCase
import com.thechance.presentation.Feature
import com.thechance.presentation.FoodChangeMoodCLI
import com.thechance.presentation.IO.ConsoleIO
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.getKoin

fun main() {
    startKoin { modules(appModule, useCasesModule, presentationModule) }

    val foodChangeMoodUiFeature = getKoin().get<Feature>()
    val consoleIO = getKoin().get<ConsoleIO>()


    val cli = FoodChangeMoodCLI(
        consoleIO = consoleIO,
        foodChangeMoodFeatureUi = foodChangeMoodUiFeature
    )

    cli.start()
}
