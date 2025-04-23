package com.thechance.data

import com.thechance.model.Meal
import com.thechance.model.MealIndexToField
import com.thechance.model.NutritionFacts
import com.thechance.model.NutritionFactsIndexToField
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class MealsFileParser(private val dateFormat: SimpleDateFormat) {

    fun parseRecord(line: String): Meal? {
        val mealFields = splitCsvLine(line)
        if (mealFields.size != 12) throw MealsDataException.InvalidFieldsCountException(mealFields.size)
        return try {
            Meal(
                name = mealFields[MealIndexToField.NAME],
                id = mealFields[MealIndexToField.ID].toIntOrThrow(),
                minutes = mealFields[MealIndexToField.MINUTES].toIntOrThrow(),
                contributorId = mealFields[MealIndexToField.CONTRIBUTOR_ID].toIntOrThrow(),
                submitted = parseDate(mealFields[MealIndexToField.SUBMITTED]),
                tags = parseList(mealFields[MealIndexToField.TAGS]),
                nutritionFacts = parseNutritionFacts(mealFields[MealIndexToField.NUTRITION_FACTS]),
                numberOfSteps = mealFields[MealIndexToField.NUMBER_OF_STEPS].toIntOrThrow(),
                steps = parseList(mealFields[MealIndexToField.STEPS]),
                description = mealFields[MealIndexToField.DESCRIPTION],
                ingredients = parseList(mealFields[MealIndexToField.INGREDIENTS]),
                numberOfIngredients = mealFields[MealIndexToField.NUMBER_OF_INGREDIENTS].toIntOrThrow()
            )
        } catch (inValidListFieldExceptionException: MealsDataException.InvalidListFieldException) {

            throw MealsDataException.InvalidListFieldException()

        }  catch (invalidNumericFormatException: MealsDataException.InvalidNumericFormatException) {

            throw MealsDataException.InvalidNumericFormatException(invalidNumericFormatException.str)

        } catch (mealsDataException: MealsDataException) {

            throw MealsDataException.InvalidMealRecordFormatException()
        }
    }

    private fun parseDate(stringDate: String): Date {
        return dateFormat.parse(stringDate)
    }

    private fun parseList(listOfStrings: String): List<String> { // takes the list as string and parse it to list of strings
        val list = listOfStrings
            .removePrefix("['")
            .removeSuffix("']")
            .trim()  // Remove surrounding whitespace
            .split("', '")
            .map { it.trim().removeSurrounding("'") }  // Remove any surrounding quotes around individual items
            .filter { it.isNotEmpty() }
        if (list.isEmpty()) throw MealsDataException.InvalidListFieldException()
        return list
    }

    private fun parseNutritionFacts(listOfNutritionFacts: String): NutritionFacts { //takes list of nutrition facts as a string and parse it to a NutritionFacts instance
        val list = listOfNutritionFacts
            .removePrefix("[")
            .removeSuffix("]")
            .split(", ").map { it.toFloatOrNull() ?: throw MealsDataException.InvalidNumericFormatException(it) }
        return NutritionFacts(
            calories = list[NutritionFactsIndexToField.CALORIES],
            totalFat = list[NutritionFactsIndexToField.TOTAL_FAT],
            sugar = list[NutritionFactsIndexToField.SUGAR],
            sodium = list[NutritionFactsIndexToField.SODIUM],
            protein = list[NutritionFactsIndexToField.PROTEIN],
            saturatedFat = list[NutritionFactsIndexToField.SATURATED_FACTS],
            carbohydrates = list[NutritionFactsIndexToField.CARBOHYDRATES]
        )
    }

    private fun splitCsvLine(line: String): List<String> {
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
        return fields.map { it.trim() }
    }

    private fun String.toIntOrThrow(): Int {
        return this.trim().toIntOrNull() ?: throw MealsDataException.InvalidNumericFormatException(this)
    }
}