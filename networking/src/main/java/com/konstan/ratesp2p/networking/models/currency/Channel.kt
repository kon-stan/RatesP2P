package com.konstan.ratesp2p.networking.models.currency

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
data class Channel @JvmOverloads constructor(
    @field:ElementList(inline = true, name="item", required = false)
    @param:ElementList(name="item", required = false)
    var item: List<Item>? = null,

    @field:Element(name="generator", required = false)
    @param:Element(name="generator", required = false)
    var generator: String? = null,

    @field:Element(name="title", required = false)
    @param:Element(name="title", required = false)
    var title: String? = null,

    @field:Element(name="link", required = false)
    @param:Element(name="link", required = false)
    var link: String? = null,

    @field:Element(name="description", required = false)
    @param:Element(name="description", required = false)
    var description: String? = null,

    @field:Element(name="language", required = false)
    @param:Element(name="language", required = false)
    var language: String? = null,

    @field:Element(name="copyright", required = false)
    @param:Element(name="copyright", required = false)
    var copyright: String? = null,
)
