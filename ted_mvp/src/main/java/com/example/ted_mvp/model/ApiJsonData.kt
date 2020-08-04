package com.example.ted_mvp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiJsonData(
    @Json(name = "channel")
    val channel: TedItem
)

@JsonClass(generateAdapter = true)
data class TedItem(
    @Json(name = "item")
    val itms: List<TedEntry>

)

@JsonClass(generateAdapter = true)
data class TedEntry(
    @Json(name = "title")
    val tedTitle: String,
    @Json(name = "image")
    val tedImage: TedImage,
    @Json(name = "duration")
    val tedDuration: TedDuration
)

@JsonClass(generateAdapter = true)
data class TedImage(
    @Json(name = "url") val imageUrl: String
)

@JsonClass(generateAdapter = true)
data class TedDuration(
    @Json(name = "text") val durationValue: String
)
