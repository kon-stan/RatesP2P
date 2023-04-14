package com.konstan.ratesp2p.networking.models.currency

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class Currency @JvmOverloads constructor(
    @field:Element(name = "channel")
    @param:Element(name = "channel")
    var channel: Channel? = null
)
