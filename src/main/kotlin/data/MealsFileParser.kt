package com.thechance.data

import com.thechance.model.Meal
import com.thechance.model.NutritionFacts
import java.text.SimpleDateFormat

class MealsFileParser(private val dateFormat: SimpleDateFormat) {

    fun parseLine(line: String): Meal? {
        val mealFields = parseCsvLineToMealFields(line)
        return try {
            Meal(
                id = mealFields[1].toLong(),
                name = mealFields[0],
                minutes = mealFields[2].toInt(),
                contributorId = mealFields[3].toLong(),
                submitted = dateFormat.parse(mealFields[4]),
                tags = parseList(mealFields[5]),
                nutritionFacts = parseNutritionFacts(mealFields[6]),
                stepsNumber = mealFields[7].toInt(),
                steps = parseList(mealFields[8]),
                description = mealFields[9],
                ingredients = parseList(mealFields[10]),
                ingredientsNumber = mealFields[11].toInt()
            )
        } catch (e: Exception) {
            println(e.message)
            null
        }
    }

    private fun parseList(listOfStrings: String): List<String> { // takes the list as string and parse it to list of strings
        return listOfStrings
            .removePrefix("['")
            .removeSuffix("']")
            .split("', '")
    }

    private fun parseNutritionFacts(listOfNutritionFacts: String): NutritionFacts { //takes list of nutrition facts as a string and parse it to a NutritionFacts instance
        val list = listOfNutritionFacts
            .removePrefix("[")
            .removeSuffix("]")
            .split(", ").map { it.toFloat() }
        return NutritionFacts(
            calories = list[0],
            totalFat = list[1],
            sugar = list[2],
            sodium = list[3],
            protein = list[4],
            saturatedFat = list[5],
            carbohydrates = list[6]
        )
    }

    private fun parseCsvLineToMealFields(line: String): List<String> {
        val fields = mutableListOf<String>()
        var current = ""
        var inDoubleQuotes = false

        var i = 0
        while (i < line.length) {
            val char = line[i]
            when {
                char == '"' -> {
                    inDoubleQuotes = !inDoubleQuotes
                }

                char == ',' && !inDoubleQuotes -> {
                    fields.add(current)
                    current = ""
                }

                else -> {
                    current += char
                }
            }
            i++
        }
        fields.add(current)
        return fields
    }
}