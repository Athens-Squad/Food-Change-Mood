package com.thechance.data

import com.thechance.model.Meal
import com.thechance.model.NutritionFacts
import java.text.SimpleDateFormat

class MealsFileParser(private val dateFormat: SimpleDateFormat) {

    fun parseLine(line: String): Meal? {
        val mealFields = splitCsvLine(line)
        return try {
            Meal(
                name = mealFields[0].trim(),
                id = mealFields[1].trim().toInt(),
                minutes = mealFields[2].trim().toInt(),
                contributorId = mealFields[3].trim().toInt(),
                submitted = mealFields[4].trim(),
                tags = parseList(mealFields[5]),
                nutritionFacts = parseNutritionFacts(mealFields[6]),
                numberOfSteps = mealFields[7].trim().toInt(),
                steps = parseList(mealFields[8]),
                description = mealFields[9].trim(),
                ingredients = parseList(mealFields[10]),
                numberOfIngredients = mealFields[11].trim().toInt()
            )
        } catch (e: Exception) {
            //println(e.message)
            null
        }
    }

    private fun parseList(listOfStrings: String): List<String> { // takes the list as string and parse it to list of strings
        return listOfStrings
            .removePrefix("['")
            .removeSuffix("']")
            .trim()  // Remove surrounding whitespace
            .split("', '")
            .map { it.trim().removeSurrounding("'") }  // Remove any surrounding quotes around individual items
            .filter { it.isNotEmpty() }
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

    private fun splitCsvLine(line: String): List<String> {
        val fields = mutableListOf<String>()
        var current = ""
        var inDoubleQuotes = false


        var i = 0
        while (i < line.length) {
            val char = line[i]
            val prev = line[if (i != 0) i-1 else 0]
            when {
                char == '"' -> {
                    inDoubleQuotes = !inDoubleQuotes
                }

                char == ',' && !inDoubleQuotes -> {
                    fields.add(current.trim())
                    current = ""
                }

                else -> {
                    current += char
                }
            }
            i++
        }
        fields.add(current.trim())
        return fields
    }
}