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
            mapInputToFeature(userInputOption)
        }
    }

    private fun mapInputToFeature(option: Int) {
        foodChangeMoodFeatureUi.apply {
            features.forEach { featureUi ->
                if (featureUi.featureNumber == option) {
                    featureUi.startUi()
                }
            }
            if (option == 0) {
                consoleIO.printer.showMessage("Exiting the app. Goodbye!")
                return
            } else {
                consoleIO.printer.showMessage("Invalid option. Please choose again.")
            }
        }
    }


    private fun displayOptions() {
        foodChangeMoodFeatureUi.features
            .sortedBy { it.featureNumber }
            .forEach { featureUi ->
            featureUi.printUiMessage()
        }
    }
}