package com.thechance.presentation

import com.thechance.presentation.ui_holder.*

class Feature(
    private val getItalianMealsForLargeGroupsUi: GetItalianMealsForLargeGroupsUi,
    private val getRandomPotatoMealsUi: GetRandomPotatoMealsUi,
    private val getRandomSweetsWithNoEggsUi: GetRandomSweetsWithNoEggsUi,
    private val getSeaFoodMealsSortedByProteinUi: GetSeaFoodMealsSortedByProteinUi,
    private val gymHelperUi: GymHelperUi,
    private val healthyFastFoodMealsUi: HealthyFastFoodMealsUi,
    private val identifyIraqiMealsUi: IdentifyIraqiMealsUi,
    private val ingredientGameUi: IngredientGameUi,
    private val ketoDietMealUi: KetoDietMealUi,
    private val mealPrepTimeGuessGameUi: MealPrepTimeGuessGameUi,
    private val searchFoodByAddDateUi: SearchFoodByAddDateUi,
    private val searchMealByCountryUi: SearchMealByCountryUi,
    private val searchMealByNameUi: SearchMealByNameUi,
    private val soThinUi: SoThinUi,
    private val suggestEasyMealsUi: SuggestEasyMealsUi
){
    val features = listOf(
        getItalianMealsForLargeGroupsUi,
        getRandomPotatoMealsUi,
        getRandomSweetsWithNoEggsUi,
        getSeaFoodMealsSortedByProteinUi,
        gymHelperUi,
        healthyFastFoodMealsUi,
        identifyIraqiMealsUi,
        ingredientGameUi,
        mealPrepTimeGuessGameUi,
        searchFoodByAddDateUi,
        ketoDietMealUi,
        searchMealByCountryUi,
        searchMealByNameUi,
        soThinUi,
        suggestEasyMealsUi
    )
}
