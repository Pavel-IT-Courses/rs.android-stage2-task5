package com.example.ted

import com.example.ted.model.data.ApiData
import com.example.ted.model.data.TedPresentation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface TedApi {

    @GET("/artem-bagritsevich/rs.android.task.6/master/data/data.json")
    suspend fun getListOfPresentations(): ApiData
}

object TedApiImpl {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://raw.githubusercontent.com")
        .build()

    private val presentationService = retrofit.create(TedApi::class.java)

    suspend fun getListOfPresentations(): List<TedPresentation> {
        return withContext(Dispatchers.IO) {
            presentationService.getListOfPresentations()
                .channel
                .itms
                .map { tedEntry ->
                    TedPresentation(
                        tedEntry.tedTitle,
                        tedEntry.tedImage.imageUrl,
                        tedEntry.tedDuration.durationValue
                    )
                }
        }
    }
}
