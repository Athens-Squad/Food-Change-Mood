package com.thechance.logic.useCases

import com.thechance.logic.useCases.mealSearchUseCase.MealSearchUseCase

data class FoodChangeMoodUseCases(
    val getHealthyMealsUseCase: GetHealthyMealsUseCase,
    val mealSearchUseCase: MealSearchUseCase,
    val getItalianMealsForLargeGroupsUseCase: GetItalianMealsForLargeGroupsUseCase,
    val ketoDietMealUseCase: KetoDietMealUseCase,
    val searchFoodByAddDateUseCase: SearchFoodByAddDateUseCase,
    val searchByCountryName: SearchByCountryName,
    val getIraqiMealsUseCase: GetIraqiMealsUseCase,
    val suggestFoodUseCase: SuggestFoodUseCase,
    val guessGameUseCase: MealPrepTimeGuessGameUseCase,
    val getRandomSweetWithNoEggs: GetRandomSweetWithNoEggsUseCase,
    val gymHelperUseCase: GymHelperUseCase,
    val soThinUseCase: SoThinUseCase,
    val getSeaFoodMealsSortedByProteinContent: GetSeaFoodMealsSortedByProteinContent,
    val ingredientGameUseCase: IngredientGameUseCase,
    val getRandomTenMealIncludePotatoUseCase: GetRandomTenMealIncludePotatoUseCase
)
