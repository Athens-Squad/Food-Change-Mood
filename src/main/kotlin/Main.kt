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

    val getHealthyMealsUseCase = getKoin().get<GetHealthyMealsUseCase>()
    val searchByCountryName = getKoin().get<SearchByCountryName>()
    val getIraqiMealsUseCase = getKoin().get<GetIraqiMealsUseCase>()
    val suggestFoodUseCase = getKoin().get<SuggestFoodUseCase>()
    val guessGameUseCase = getKoin().get<MealPrepTimeGuessGameUseCase>()
    val getRandomSweetWithNoEggs = getKoin().get<GetRandomSweetWithNoEggsUseCase>()
    val gymHelperUseCase = getKoin().get<GymHelperUseCase>()
    val soThinUseCase = getKoin().get<SoThinUseCase>()
    val getSeaFoodMealsSortedByProteinContent = getKoin().get<GetSeaFoodMealsSortedByProteinContent>()
    val ingredientGameUseCase = getKoin().get<IngredientGameUseCase>()
    val getRandomTenMealIncludePotatoUseCase = getKoin().get<GetRandomTenMealIncludePotatoUseCase>()
    val mealSearchUseCase = getKoin().get<MealSearchUseCase>()
    val getItalianMealsForLargeGroupsUseCase = getKoin().get<GetItalianMealsForLargeGroupsUseCase>()
    val ketoDietMealUseCase = getKoin().get<KetoDietMealUseCase>()
    val searchFoodByAddDateUseCase = getKoin().get<SearchFoodByAddDateUseCase>()

    val cli = FoodChangeMoodCLI(
        getHealthyMealsUseCase = getHealthyMealsUseCase,
        mealSearchUseCase = mealSearchUseCase,
        getItalianMealsForLargeGroupsUseCase = getItalianMealsForLargeGroupsUseCase,
        ketoDietMealUseCase = ketoDietMealUseCase,
        searchFoodByAddDateUseCase = searchFoodByAddDateUseCase,
        searchByCountryName = searchByCountryName,
        getIraqiMealsUseCase = getIraqiMealsUseCase,
        suggestFoodUseCase = suggestFoodUseCase,
        guessGameUseCase = guessGameUseCase,
        getRandomSweetWithNoEggs = getRandomSweetWithNoEggs,
        gymHelperUseCase = gymHelperUseCase,
        soThinUseCase = soThinUseCase,
        getSeaFoodMealsSortedByProteinContent = getSeaFoodMealsSortedByProteinContent,
        ingredientGameUseCase = ingredientGameUseCase,
        getRandomTenMealIncludePotatoUseCase = getRandomTenMealIncludePotatoUseCase
    )

    cli.start()
}
