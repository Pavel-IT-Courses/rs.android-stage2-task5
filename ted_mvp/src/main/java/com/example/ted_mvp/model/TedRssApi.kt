package com.example.ted_mvp.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET

interface TedRssApi {
    @GET("/themes/rss/id")
    suspend fun getListOfPresentations(): ApiRssData
}

object TedRssApiImpl {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .baseUrl("https://www.ted.com/")
        .build()

    private val presentationService = retrofit.create(
        TedRssApi::class.java
    )


    suspend fun getListOfPresentations(): List<TedVideoEntry>? {
        return withContext(Dispatchers.IO) {
            presentationService.getListOfPresentations()
                ?.channel
                ?.list
                ?.map { tedItem ->
                    TedVideoEntry(
                        tedItem.tedTitle,
                        tedItem.tedImage?.url,
//                        "https://pi.tedcdn.com/r/pe.tedcdn.com/images/ted/e98a047229351dbda3f53fb5a70102f2daf48c4d_800x600.jpg?w=615&amp;h=461",
                        tedItem.tedDuration
//                        "Title | titlik",
//                        "https://pi.tedcdn.com/r/pe.tedcdn.com/images/ted/e98a047229351dbda3f53fb5a70102f2daf48c4d_800x600.jpg?w=615&amp;h=461",
//                        "haha"
                    )
                }
        }
    }
}
