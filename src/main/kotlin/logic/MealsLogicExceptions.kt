package com.thechance.logic

import java.util.*

class NoMealsWithGivenDateException(date: Date): Exception("No meals found at this date : $date")