import com.thechance.di.appModule
import com.thechance.di.useCasesModule
import com.thechance.logic.useCases.GymHelperUseCase
import com.thechance.logic.useCases.SearchByCountryName
import com.thechance.logic.useCases.SuggestItalianMealsForLargeGroupsUseCase
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.getKoin

fun main() {
    startKoin {
        modules(appModule, useCasesModule)
    }

    val instance1: SuggestItalianMealsForLargeGroupsUseCase = getKoin().get()
    instance1.suggestItalianMealsForLargeGroups().forEachIndexed { index, meal ->
        println("${index + 1}. ${meal.name}")
    }

    val instance2: SearchByCountryName = getKoin().get()
    instance2.getMealsByCountry("united states").forEach {
        println(it)
    }

    val instance3: GymHelperUseCase = getKoin().get()
    val result = instance3.getMealsByCaloriesAndProtein(600, 30)
    println(result)
}