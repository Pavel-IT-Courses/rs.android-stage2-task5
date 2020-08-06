package com.example.ted_mvp.model

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TedCallback(): Callback<ApiRssData> {
    override fun onFailure(call: Call<ApiRssData>, t: Throwable) {
        Log.e("MYTAG", "Response Failure")
    }

    override fun onResponse(call: Call<ApiRssData>, response: Response<ApiRssData>) {
//        response.body()?.channel?.list
//            ?.map { tedItem ->
//                TedVideoEntry(
//                    tedItem.tedTitle,
////                        tedItem.tedImage,
////                        tedItem.tedDuration
//                    "https://pi.tedcdn.com/r/pe.tedcdn.com/images/ted/e98a047229351dbda3f53fb5a70102f2daf48c4d_800x600.jpg?w=615&amp;h=461",
//                    "haha"
//                )
//            }
        println(response.body().toString())
    }

}
