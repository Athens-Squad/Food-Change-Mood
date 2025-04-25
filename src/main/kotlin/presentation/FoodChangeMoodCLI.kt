package com.thechance.presentation

import com.thechance.presentation.io.ConsoleIO

class FoodChangeMoodCLI(
    private val consoleIO: ConsoleIO,
    private val foodChangeMoodFeatureUi: Feature
) {
    fun start() {
        while (true) {
            displayOptions()
            val userInputOption = consoleIO.reader.readNumberFromUser()
            if (!mapInputToFeature(userInputOption)) {
                break
            }
        }
    }

    private fun mapInputToFeature(option: Int): Boolean {
        foodChangeMoodFeatureUi.apply {
            features.forEach { featureUi ->
                if (featureUi.featureNumber == option) {
                    featureUi.startUi()
                }
            }
            if (option == 0) {
                consoleIO.printer.showMessage("Exiting the app. Goodbye!")
                return false
            } else {
                consoleIO.printer.showMessage("Invalid option. Please choose again.")
            }
        }
        return true
    }


    private fun displayOptions() {
        foodChangeMoodFeatureUi.features
            .sortedBy { it.featureNumber }
            .forEach { featureUi ->
            featureUi.printUiMessage()
        }.also {
            consoleIO.printer.showMessage("0 : Exit the App")
            }
    }
}