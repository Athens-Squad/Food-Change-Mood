package com.thechance.data

import com.thechance.logic.MealsRepository
import com.thechance.model.Meal
import java.util.logging.Logger


class MealsRepositoryImpl(
    private val parser: MealsFileParser,
    private val reader: MealsFileReader
): MealsRepository {
    private val logger = Logger.getLogger(MealsRepositoryImpl::class.java.name)
    override fun getAllMeals(): List<Meal?> {
        return try {
            reader.readMealRecords().map { parser.parseLine(it) }
        } catch (e: MealsDataException) {
            logger.warning(e.message)
            listOf<Meal>()
        }
    }
}