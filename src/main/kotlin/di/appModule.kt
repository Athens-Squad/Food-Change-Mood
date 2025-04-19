package com.thechance.di

import com.thechance.data.MealsFileParser
import com.thechance.data.MealsFileReader
import com.thechance.data.MealsRepositoryImpl
import com.thechance.logic.MealsRepository
import com.thechance.logic.useCases.mealSearchUseCase.KmpSearchStrategy
import com.thechance.logic.useCases.mealSearchUseCase.MealSearchStrategy
import com.thechance.presentation.FoodChangeMoodCLI
import org.koin.dsl.module
import java.io.File
import java.text.SimpleDateFormat

val appModule = module {
    single { File("food.csv") }
    single { MealsFileReader(get()) }

    single { SimpleDateFormat("yyyy-MM-dd") }
    single { MealsFileParser(get()) }

    single<MealsRepository> { MealsRepositoryImpl(get(), get()) }

    single<MealSearchStrategy> { KmpSearchStrategy(get()) }

    single {
        FoodChangeMoodCLI(
            getHealthyMealsUseCase = get(),
            searchByCountryName = get(),
            getIraqiMealsUseCase = get(),
            suggestFoodUseCase = get(),
            guessGameUseCase = get(),
            getRandomSweetWithNoEggs = get(),
            gymHelperUseCase = get(),
            soThinUseCase = get(),
            getSeaFoodMealsSortedByProteinContent = get(),
            ingredientGameUseCase = get(),
            getRandomTenMealIncludePotatoUseCase = get(),
            mealSearchUseCase = get(),
            getItalianMealsForLargeGroupsUseCase = get(),
            ketoDietMealUseCase = get(),
            searchFoodByAddDateUseCase = get()
        )
    }
}