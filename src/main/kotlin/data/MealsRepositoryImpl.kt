package com.thechance.data

import com.thechance.logic.MealsRepository
import com.thechance.model.Meal

class MealsRepositoryImpl(
    private val parser: MealsFileParser,
    private val reader: MealsFileReader
): MealsRepository {
    override fun getAllMeals(): List<Meal?> {
        val records = reader.readMealRecords()
        return records.map { parser.parseLine(it) }
    }
}