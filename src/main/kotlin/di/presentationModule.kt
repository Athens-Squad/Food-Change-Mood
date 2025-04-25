package com.thechance.di

import com.thechance.presentation.Feature
import com.thechance.presentation.io.ConsoleIO
import com.thechance.presentation.io.Printer
import com.thechance.presentation.io.Reader
import com.thechance.presentation.ui_holder.*
import org.koin.core.module.dsl.singleOf

import org.koin.dsl.module

val presentationModule = module {
    single { Printer() }
    single { Reader() }
    single { ConsoleIO(get(),get()) }


    single { GetItalianMealsForLargeGroupsUi(get(),get()) }
    single { GetRandomPotatoMealsUi(get(),get()) }
    single { GetRandomSweetsWithNoEggsUi(get(),get()) }
    single { GetSeaFoodMealsSortedByProteinUi(get(),get()) }
    single { GymHelperUi(get(),get()) }
    single { HealthyFastFoodMealsUi(get(),get()) }
    single { IdentifyIraqiMealsUi(get(),get()) }
    single { IngredientGameUi(get(),get()) }
    single { KetoDietMealUi(get(),get()) }
    single { MealPrepTimeGuessGameUi(get(),get()) }
    single { SearchFoodByAddDateUi(get(),get()) }
    single { SearchMealByCountryUi(get(),get()) }
    single { SearchMealByNameUi(get(),get()) }
    single { SoThinUi(get(),get()) }
    single { SuggestEasyMealsUi(get(),get()) }

//    singleOf(::Feature)

    single { Feature(get(),get(),get(),get(),get(),get(),get(),get(),get(),get(),get(),get(),get(),get(),get()) }

}