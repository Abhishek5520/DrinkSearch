package com.example.drinksearch

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.drinksearch.model.DrinkData
import com.example.drinksearch.network.DrinkApi
import com.example.drinksearch.network.DrinkService
import com.example.drinksearch.ui.theme.screen.DrinkDetailScreen
import com.example.drinksearch.ui.theme.screen.DrinkListScreen
import kotlinx.coroutines.CoroutineScope


@SuppressLint("UnrememberedMutableState")
@Composable
fun DrinkApp() {

    val drinks = mutableStateOf(listOf<DrinkData>())

    val drinkApi = DrinkApi.getInstance().create(DrinkService::class.java)

    val scaffoldState = rememberScaffoldState()
    val lazyScroll = rememberScrollState()
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()

    MainScreen(drinkApi,coroutineScope,scaffoldState,navController,lazyScroll,drinks)

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(drinkApi: DrinkService, scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavHostController, scrollState: ScrollState, drinks: MutableState<List<DrinkData>>) {

    Navigation(navController =navController,
        drinks = drinks,
        scaffoldState = scaffoldState,
        lazyScroll = scrollState,
        coroutineScope = scope,
        drinkApi = drinkApi
    )

}

@Composable
fun Navigation(navController: NavHostController,
               drinks: MutableState<List<DrinkData>>,
               scaffoldState: ScaffoldState,
               lazyScroll: ScrollState,
               coroutineScope: CoroutineScope,
               drinkApi: DrinkService
) {
    NavHost(navController = navController, startDestination = "DrinkListScreen",
        
    ) {
        composable("DrinkListScreen") {
            DrinkListScreen(
                scaffoldState = scaffoldState,
                lazyScroll = lazyScroll,
                navController = navController,
                coroutineScope = coroutineScope,
                drinkApi = drinkApi,
                drinks = drinks
            )
        }
        composable("Detail/{index}",
            arguments = listOf(
                navArgument("index") { type = NavType.StringType })
        )
        {
            navBackStackEntry->
            val index = navBackStackEntry.arguments?.getString("index")
            val drinkData = drinks.value.first { it.idDrink == index }

            DrinkDetailScreen(drinkData,navController = navController)
        }
    }

}