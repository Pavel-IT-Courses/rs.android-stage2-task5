package com.example.ted_mvp.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class ApiRssData (
    @field:Element(name = "channel")
    val channel: TedRssItem
)

@Root(name = "channel", strict = false)
data class TedRssItem(
    @field:ElementList(name = "item", inline = true, required = false)
    val itms: List<TedRssEntry>

)

@Root(name = "item", strict = false)
data class TedRssEntry(
    @field:Element(name = "title")
    val tedTitle: String,
    @field:Element(name = "image") @field:Attribute(name = "url")
    val tedImage: String,
    @field:Element(name = "duration")
    val tedDuration: String
)

