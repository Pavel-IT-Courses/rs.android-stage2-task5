package com.example.ted_mvp.model

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.toptas.rssconverter.RssConverterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET

interface TedRssApi {
    @GET("/themes/rss/id")
    suspend fun getListOfPresentations(): ApiRssData
}

object TedRssApiImpl {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .baseUrl("https://www.ted.com")
        .build()

    private val presentationService = TedRssApiImpl.retrofit.create(
        TedRssApi::class.java)

    suspend fun getListOfPresentations(): List<TedVideoEntry> {
        return withContext(Dispatchers.IO) {
            presentationService.getListOfPresentations()
                .channel
                .itms
                .map { tedEntryRss ->
                    TedVideoEntry(
                        tedEntryRss.tedTitle,
                        tedEntryRss.tedImage,
                        tedEntryRss.tedDuration
                    )
                }
        }
        Log.d("MYTAG", "YES")
    }
}
