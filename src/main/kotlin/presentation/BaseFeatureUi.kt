package com.thechance.presentation

interface BaseFeatureUi {
    val featureNumber : Int
    val featureName : String
    fun startUi()

    fun printUiMessage(){
        println("$featureNumber: $featureName")
    }
}