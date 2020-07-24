package com.example.catapplication

import com.example.catapplication.entity.Cat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface CatApi {

    @GET("/v1/images/search?limit=12&page=1&order=desc?api-key=ca2392a5-1f95-407a-944a-195bc4a8b08d")
    suspend fun getListOfCats(): List<Cat>
}

object CatApiImpl {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://api.thecatapi.com/")
        .build()

    private val catService = retrofit.create(CatApi::class.java)

    suspend fun getListOfCats(): List<Cat> {
        return withContext(Dispatchers.IO) {
            catService.getListOfCats()
        }
    }
}
