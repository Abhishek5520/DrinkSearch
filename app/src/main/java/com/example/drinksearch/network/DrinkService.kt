package com.example.drinksearch.network


import com.example.drinksearch.model.DrinkDataList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinkService {
    @GET("search.php")
    suspend fun getDrinksByName(@Query("s") drinkName: String): Response<DrinkDataList>
}