package com.thechance.data

import com.thechance.logic.MealsRepository
import com.thechance.model.Meal

class MealsRepositoryImpl(
    private val parser: MealsFileParser,
    private val reader: MealsFileReader
): MealsRepository {
    override fun getAllMeals(): List<Meal?> {
        val lines = reader.readFileLines()
        val meals = mutableListOf<Meal?>()
        lines.forEach {
            if (parser.parseLine(it) != null) {
                meals.add(parser.parseLine(it))
            }

        }
        return meals
    }
}