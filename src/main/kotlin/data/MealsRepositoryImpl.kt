package com.thechance.data

import com.thechance.logic.MealsRepository
import com.thechance.model.Meal
import java.util.logging.Logger


class MealsRepositoryImpl(
    private val parser: MealsFileParser,
    private val reader: MealsFileReader
) : MealsRepository {
    private val logger = Logger.getLogger(MealsRepositoryImpl::class.java.name)
    private var cachedMeals: List<Meal?> = emptyList()
    override fun getAllMeals(): List<Meal?> {

        return cachedMeals.ifEmpty {
            try {
                val meals = reader.readMealRecords().map { parser.parseRecord(it) }
                cachedMeals = meals
                cachedMeals
            } catch (mealsDataException: MealsDataException) {
                logger.warning(mealsDataException.message)
                listOf<Meal>()
            }
        }
    }
}