package com.example.ted_mvp.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class ApiRssData @JvmOverloads constructor(
    @field:Element(name = "channel")
    var channel: TedRssChannel? = null
)

@Root(name = "channel", strict = false)
data class TedRssChannel @JvmOverloads constructor(
    @field:ElementList(entry = "item", inline = true, required = false)
    var list: List<TedRssItem>? = null

)

@Root(name = "item", strict = false)
data class TedRssItem @JvmOverloads constructor(
    @field:Element(name = "title") @param:Element(name = "title")
    var tedTitle: String = "",
    @field:Element(name = "image")
    var tedImage: TedRssUrl? = null,
    @field:Element(name = "duration")
    var tedDuration: String = ""
)

@Root(name = "image", strict = false)
data class TedRssUrl @JvmOverloads constructor(
    @field:Attribute(name = "url")
    var url: String = ""
)

