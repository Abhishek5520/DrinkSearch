package com.example.drinksearch.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object DrinkApi {
    private val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"


    //Todo 18: Initialize moshi and add to retrofit builder
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    //Todo 9: intialize retrofit
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    //Todo 10: initialize the service class
//    val retrofitService: DrinkService by lazy { retrofit.create(DrinkService::class.java) }

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
}