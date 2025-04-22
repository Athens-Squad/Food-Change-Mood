package com.thechance

import com.thechance.di.appModule
import com.thechance.di.useCasesModule
import com.thechance.logic.useCases.*
import com.thechance.logic.useCases.mealSearchUseCase.MealSearchUseCase
import com.thechance.presentation.FoodChangeMoodCLI
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.getKoin

fun main() {
    startKoin { modules(appModule, useCasesModule) }

    val foodChangeMoodUseCases = getKoin().get<FoodChangeMoodUseCases>()

    val cli = FoodChangeMoodCLI(
        getHealthyMealsUseCase = foodChangeMoodUseCases.getHealthyMealsUseCase,
        mealSearchUseCase = foodChangeMoodUseCases.mealSearchUseCase,
        getItalianMealsForLargeGroupsUseCase = foodChangeMoodUseCases.getItalianMealsForLargeGroupsUseCase,
        ketoDietMealUseCase = foodChangeMoodUseCases.ketoDietMealUseCase,
        searchFoodByAddDateUseCase = foodChangeMoodUseCases.searchFoodByAddDateUseCase,
        searchByCountryName = foodChangeMoodUseCases.searchByCountryName,
        getIraqiMealsUseCase = foodChangeMoodUseCases.getIraqiMealsUseCase,
        suggestFoodUseCase = foodChangeMoodUseCases.suggestFoodUseCase,
        guessGameUseCase = foodChangeMoodUseCases.guessGameUseCase,
        getRandomSweetWithNoEggs = foodChangeMoodUseCases.getRandomSweetWithNoEggs,
        gymHelperUseCase = foodChangeMoodUseCases.gymHelperUseCase,
        soThinUseCase = foodChangeMoodUseCases.soThinUseCase,
        getSeaFoodMealsSortedByProteinContent = foodChangeMoodUseCases.getSeaFoodMealsSortedByProteinContent,
        ingredientGameUseCase = foodChangeMoodUseCases.ingredientGameUseCase,
        getRandomTenMealIncludePotatoUseCase = foodChangeMoodUseCases.getRandomTenMealIncludePotatoUseCase
    )

    cli.start()
}
