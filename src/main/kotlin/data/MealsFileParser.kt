package com.thechance.data

import com.thechance.model.Meal
import com.thechance.model.MealIndexToField
import com.thechance.model.NutritionFacts
import com.thechance.model.NutritionFactsIndexToField
import java.text.SimpleDateFormat

class MealsFileParser(private val dateFormat: SimpleDateFormat) {

    fun parseLine(line: String): Meal? {
        val mealFields = splitCsvLine(line)
        if (mealFields.size != 12) throw MealsDataException.InvalidFieldsCountException(mealFields.size)
        return try {
            Meal(
                name = mealFields[MealIndexToField.NAME],
                id = mealFields[MealIndexToField.ID].toIntOrThrow(),
                minutes = mealFields[MealIndexToField.MINUTES].toIntOrThrow(),
                contributorId = mealFields[MealIndexToField.CONTRIBUTOR_ID].toIntOrThrow(),
                submitted = dateFormat.parse(mealFields[MealIndexToField.SUBMITTED]),
                tags = parseList(mealFields[MealIndexToField.TAGS]),
                nutritionFacts = parseNutritionFacts(mealFields[MealIndexToField.NUTRITION_FACTS]),
                numberOfSteps = mealFields[MealIndexToField.NUMBER_OF_STEPS].toIntOrThrow(),
                steps = parseList(mealFields[MealIndexToField.STEPS]),
                description = mealFields[MealIndexToField.DESCRIPTION],
                ingredients = parseList(mealFields[MealIndexToField.INGREDIENTS]),
                numberOfIngredients = mealFields[MealIndexToField.NUMBER_OF_INGREDIENTS].toIntOrThrow()
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
                calories = list[NutritionFactsIndexToField.CALORIES],
                totalFat = list[NutritionFactsIndexToField.TOTAL_FAT],
                sugar = list[NutritionFactsIndexToField.SUGAR],
                sodium = list[NutritionFactsIndexToField.SODIUM],
                protein = list[NutritionFactsIndexToField.PROTEIN],
                saturatedFat = list[NutritionFactsIndexToField.SATURATED_FACTS],
                carbohydrates = list[NutritionFactsIndexToField.CARBOHYDRATES],
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