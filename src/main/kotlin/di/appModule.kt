package com.thechance.di

import com.thechance.data.MealsFileParser
import com.thechance.data.MealsFileReader
import com.thechance.data.MealsRepositoryImpl
import com.thechance.logic.IngredientGameUseCase
import com.thechance.logic.MealsRepository
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

}