package com.konstan.ratesp2p.networking.models.currency

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false )
data class Item @JvmOverloads constructor (
    @field:Element(name = "title")
    @param:Element(name = "title")
    var title: String = "",

    @field:Element(name = "description")
    @param:Element(name = "description")
    var description: Float = 0.0f,

    @field:Element(name = "pubDate", required = false)
    @param:Element(name = "pubDate", required = false)
    var pubDate: String? = null,

    @field:Element(name = "quant")
    @param:Element(name = "quant")
    var quant: String = "",

    @field:Element(name = "index", required = false)
    @param:Element(name = "index", required = false)
    var index: String? = null,

    @field:Element(name = "change")
    @param:Element(name = "change")
    var change: String = "",

    @field:Element(name = "link", required = false)
    @param:Element(name = "link", required = false)
    var link: String? = null,
)
