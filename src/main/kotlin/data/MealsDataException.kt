package com.thechance.data

sealed class MealsDataException(message: String): Exception(message) {

    class InvalidFieldsCountException(fieldsCount: Int): MealsDataException("Invalid Meal fields count Exception: found $fieldsCount but 12 expected")
    class InvalidListFieldException: MealsDataException("Invalid list field Exception")
    class InvalidNutritionFactsException: MealsDataException("Invalid Nutrition Facts Exception")
    class InvalidMealRecordFormatException: MealsDataException("Invalid Meal record format Exception")
    class InvalidNumericFormatException(val str: String): MealsDataException("Invalid numeric format Exception: Cannot parse $str to Int")

    class MealsFileReadingException: MealsDataException("Couldn't read the meals from the .csv file")
}