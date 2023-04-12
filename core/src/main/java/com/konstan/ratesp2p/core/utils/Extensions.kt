package com.konstan.ratesp2p.core.utils

import android.content.Context

@Suppress("UNCHECKED_CAST", "EXTENSION_SHADOWED_BY_MEMBER")
fun <T> Context.getSystemService(id: String): T {
    return getSystemService(id) as T
}