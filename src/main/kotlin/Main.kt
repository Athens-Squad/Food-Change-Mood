package com.thechance

import com.thechance.di.appModule
import com.thechance.di.useCasesModule
import com.thechance.logic.MealsRepository
import com.thechance.logic.useCases.GetSeaFoodMealsSortedByProteinContent
import com.thechance.logic.useCases.SearchByCountryName
import com.thechance.logic.useCases.SoThinUseCase
import com.thechance.presentation.FoodChangeMoodCLI
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.getKoin

fun main() {
    // ✅ Start Koin first
    startKoin {
        modules(appModule, useCasesModule)
    }

    // ✅ Now it's safe to get instances
    val getHealthyMealsUseCase = getKoin().get<com.thechance.logic.useCases.GetHealthyMealsUseCase>()
    val searchByCountryName = getKoin().get<SearchByCountryName>()
    val getIraqiMealsUseCase = getKoin().get<com.thechance.logic.useCases.GetIraqiMealsUseCase>()
    val suggestFoodUseCase = getKoin().get<com.thechance.logic.useCases.SuggestFoodUseCase>()
    val guessGameUseCase = getKoin().get<com.thechance.logic.useCases.MealPrepTimeGuessGameUseCase>()
    val getRandomSweetWithNoEggs = getKoin().get<com.thechance.logic.useCases.GetRandomSweetWithNoEggsUseCase>()
    val gymHelperUseCase = getKoin().get<com.thechance.logic.useCases.GymHelperUseCase>()
    val soThinUseCase = getKoin().get<SoThinUseCase>()
    val getSeaFoodMealsSortedByProteinContent = getKoin().get<GetSeaFoodMealsSortedByProteinContent>()

    // Run CLI
    val cli = FoodChangeMoodCLI(
        getHealthyMealsUseCase,
        searchByCountryName,
        getIraqiMealsUseCase,
        suggestFoodUseCase,
        guessGameUseCase,
        getRandomSweetWithNoEggs,
        gymHelperUseCase,
        soThinUseCase,
        getSeaFoodMealsSortedByProteinContent
    )

    cli.start()
}
