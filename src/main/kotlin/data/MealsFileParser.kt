package com.thechance.data

import com.thechance.model.Meal
import com.thechance.model.NutritionFacts
import java.text.SimpleDateFormat

class MealsFileParser(private val dateFormat: SimpleDateFormat) {

    fun parseLine(line: String): Meal? {
        val mealFields = splitCsvLine(line)
        if (mealFields.size != 12) throw MealsDataException.InvalidFieldsCountException(mealFields.size)
        return try {
            Meal(
                name = mealFields[0].trim(),
                id = mealFields[1].trim().toIntOrThrow(),
                minutes = mealFields[2].trim().toIntOrThrow(),
                contributorId = mealFields[3].trim().toIntOrThrow(),
                submitted = dateFormat.parse(mealFields[4].trim()),
                tags = parseList(mealFields[5]),
                nutritionFacts = parseNutritionFacts(mealFields[6]),
                numberOfSteps = mealFields[7].trim().toIntOrThrow(),
                steps = parseList(mealFields[8]),
                description = mealFields[9].trim(),
                ingredients = parseList(mealFields[10]),
                numberOfIngredients = mealFields[11].trim().toIntOrThrow()
            )
        } catch (e: MealsDataException) {
            throw MealsDataException.InvalidMealRecordFormatException()
        }
    }

    private fun parseList(listOfStrings: String): List<String> { // takes the list as string and parse it to list of strings
        return try {
            listOfStrings
                .removePrefix("['")
                .removeSuffix("']")
                .trim()  // Remove surrounding whitespace
                .split("', '")
                .map { it.trim().removeSurrounding("'") }  // Remove any surrounding quotes around individual items
                .filter { it.isNotEmpty() }
        } catch (e: MealsDataException) {
            throw MealsDataException.InvalidListFieldException()
        }
    }

    private fun parseNutritionFacts(listOfNutritionFacts: String): NutritionFacts { //takes list of nutrition facts as a string and parse it to a NutritionFacts instance
        val list = listOfNutritionFacts
            .removePrefix("[")
            .removeSuffix("]")
            .split(", ").map { it.toFloatOrNull() ?: throw MealsDataException.InvalidNumericFormatException(it) }
        return try {
            NutritionFacts(
                calories = list[0],
                totalFat = list[1],
                sugar = list[2],
                sodium = list[3],
                protein = list[4],
                saturatedFat = list[5],
                carbohydrates = list[6]
            )
        } catch (e: MealsDataException) {
            throw MealsDataException.InvalidNutritionFactsException()
        }
    }

    private fun splitCsvLine(line: String): List<String> {
        val fields = mutableListOf<String>()
        var current = ""
        var inDoubleQuotes = false


        var i = 0
        try {
            while (i < line.length) {
                val char = line[i]
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
        } catch (e: MealsDataException) {
            throw MealsDataException.InvalidMealRecordFormatException()
        }
        return fields
    }

    private fun String.toIntOrThrow(): Int {
        return this.toIntOrNull() ?: throw MealsDataException.InvalidNumericFormatException(this)
    }
}