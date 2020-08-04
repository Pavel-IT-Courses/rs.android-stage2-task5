package com.example.ted_mvp.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface TedJsonApi {

    @GET("/artem-bagritsevich/rs.android.task.6/master/data/data.json")
    suspend fun getListOfPresentations(): ApiJsonData
}

object TedJsonApiImpl {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://raw.githubusercontent.com")
        .build()

    private val presentationService = retrofit.create(
        TedJsonApi::class.java)

    suspend fun getListOfPresentations(): List<TedVideoEntry> {
        return withContext(Dispatchers.IO) {
            presentationService.getListOfPresentations()
                .channel
                .itms
                .map { tedEntry ->
                    TedVideoEntry(
                        tedEntry.tedTitle,
                        tedEntry.tedImage.imageUrl,
                        tedEntry.tedDuration.durationValue
                    )
                }
        }
    }
}
