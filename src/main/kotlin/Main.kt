package com.thechance

import com.thechance.di.appModule
import com.thechance.di.useCasesModule
import com.thechance.logic.MealsRepository
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.getKoin

fun main() {

    startKoin {
        modules(appModule, useCasesModule)
    }
    val repo: MealsRepository = getKoin().get()
    val numberOfMeals = repo.getAllMeals().size
    val numberOfNullMeals = repo.getAllMeals().filter { it == null }.size
    println(numberOfMeals)
    println("percentage of null meals is : ${(numberOfNullMeals.toDouble() / numberOfMeals) * 100} %")


}