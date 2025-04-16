package com.thechance.data

import com.thechance.model.Meal
import com.thechance.model.MealIndexToField
import com.thechance.model.NutritionFacts
import java.text.SimpleDateFormat

class MealsFileParser(private val dateFormat: SimpleDateFormat) {

    fun parseLine(line: String): Meal? {
        val mealFields = splitCsvLine(line)
        if (mealFields.size != 12) throw MealsDataException.InvalidFieldsCountException(mealFields.size)
        return try {
            Meal(
                name = mealFields[MealIndexToField.name],
                id = mealFields[MealIndexToField.id].toIntOrThrow(),
                minutes = mealFields[MealIndexToField.minutes].toIntOrThrow(),
                contributorId = mealFields[MealIndexToField.contributorId].toIntOrThrow(),
                submitted = dateFormat.parse(mealFields[MealIndexToField.submitted]),
                tags = parseList(mealFields[MealIndexToField.tags]),
                nutritionFacts = parseNutritionFacts(mealFields[MealIndexToField.nutritionFacts]),
                numberOfSteps = mealFields[MealIndexToField.numberOfSteps].toIntOrThrow(),
                steps = parseList(mealFields[MealIndexToField.steps]),
                description = mealFields[MealIndexToField.description],
                ingredients = parseList(mealFields[MealIndexToField.ingredients]),
                numberOfIngredients = mealFields[MealIndexToField.numberOfIngredients].toIntOrThrow()
            )
        } catch (mealsDataException: MealsDataException) {
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
        } catch (mealsDataException: MealsDataException) {
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
        } catch (mealsDataException: MealsDataException) {
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
        } catch (mealsDataException: MealsDataException) {
            throw MealsDataException.InvalidMealRecordFormatException()
        }
        return fields.map { it.trim() }
    }

    private fun String.toIntOrThrow(): Int {
        return this.trim().toIntOrNull() ?: throw MealsDataException.InvalidNumericFormatException(this)
    }
}